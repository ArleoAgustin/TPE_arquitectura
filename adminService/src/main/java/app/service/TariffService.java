package app.service;

import app.DTO.TariffDTO;
import app.model.entities.Admin;
import app.model.entities.Tariff;
import app.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TariffService {

    private final TariffRepository tariffRepository;
    private final RestTemplate restTemplate;

    public List<Tariff> findAll(){

        return tariffRepository.findAll();
    }


    public ResponseEntity<?> save(Tariff tariff) throws Exception {
        try {

            if (!tariffRepository.existsByNameTariff(tariff.getNameTariff())) {
                tariffRepository.save(tariff);
                return ResponseEntity.ok("Tarifa agregada");
            }
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una tarifa con ese nombre");
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public  ResponseEntity<?> delete(Long id_tariff){

        if (tariffRepository.existsById(id_tariff)){
            tariffRepository.deleteById(id_tariff);
            return ResponseEntity.status(HttpStatus.OK).body("Tarifa eliminada correctamente");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La tarifa no existe");
    }


    public Tariff getTariffActive(){
        return tariffRepository.getTariffCurrent();
    }

    public boolean adjustmentPrice(Tariff newTariff, Long idTariff) {

        try {
            Optional<Tariff> existsTariff = tariffRepository.findById(idTariff);
            if (existsTariff.isPresent()) {

                Tariff t = existsTariff.get();
                t.setPrice(newTariff.getPrice());
                t.setPricePauseExt(newTariff.getPricePauseExt());
                t.setNameTariff(newTariff.getNameTariff());
                t.setEffectiveDate(t.getEffectiveDate());
                tariffRepository.save(t);
                return true;
            }
            throw new Exception("Tarifa no encontrada");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }
