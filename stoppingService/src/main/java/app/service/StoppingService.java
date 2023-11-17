package app.service;

import app.model.Stopping;
import app.model.classes.Scooter;
import app.repository.StoppingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public boolean save(Stopping stopping) throws Exception {
        try {
            stoppingRepository.save(stopping);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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

    public boolean addScooter(Long idStopping, Scooter scooter) {   //hacer


        return true;

    }


}
