package app.service;

import app.model.Stopping;
import app.model.classes.Scooter;
import app.repository.StoppingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoppingService {

    private final StoppingRepository stoppingRepository;

    @Autowired
    @Qualifier("registrarTemplateStopping")
    private final RestTemplate restTemplate;

//obtiene todas las paradas

    public List<Stopping> findAll() {
        List<Stopping> scooters = stoppingRepository.findAll();
        return scooters;
    }

//agrega una parada

    public ResponseEntity<?> save(String ubication) {

            Stopping newStopping = new Stopping();
            newStopping.setUbication(ubication);
            stoppingRepository.save(newStopping);
            return ResponseEntity.status(HttpStatus.OK).body("Parada agregada correctamente");

    }

//elimina una parada

    public boolean delete(Long idStopping) throws Exception {

        if (stoppingRepository.existsById(idStopping)){
            stoppingRepository.deleteById(idStopping);
            return true;
        }
        throw new Exception("No se pudo eliminar ya que la parada no existe");
    }

//actualiza una parada

    public boolean update(Long idStopping, Stopping updateStopping) throws Exception {

        try {
            Optional<Stopping> existStopping = stoppingRepository.findById(idStopping);
            if (existStopping.isPresent()){
                Stopping stopping = existStopping.get();
                stopping.setUbication(updateStopping.getUbication());
                return true;
            }
        }catch (Exception e){
            throw new Exception("No se pudo actualizar, la parada no existe");
        }
        return false;
    }

//agrega un monopatin a una parada

    public ResponseEntity<?> addScooter(Long idStopping, Scooter scooter) {

        Optional<Stopping> stoppingOptional = stoppingRepository.findById(idStopping);

        if (stoppingOptional.isPresent()) {
            Stopping stopping = stoppingOptional.get();
            scooter.setUbication(stopping.getUbication());
            stopping.addScooter(scooter);
            stoppingRepository.save(stopping);
            return ResponseEntity.status(HttpStatus.OK).body("Monopatin agregado");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo agregar el monopatin");
    }


}
