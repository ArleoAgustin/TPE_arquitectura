package app.service;

import app.DTO.TariffDTO;
import app.model.entities.Tariff;
import app.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TariffService {

    private final TariffRepository tariffRepository;
    private final RestTemplate restTemplate;

    public ResponseEntity<?> save(Tariff tariff) throws Exception {    //verificar
        try {
            tariffRepository.save(tariff);
            return ResponseEntity.ok("Tarifa agregada");
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}
