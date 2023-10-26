package app.service;

import app.DTO.TariffDTO;
import app.model.entities.Tariff;
import app.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TariffService {

    private final TariffRepository tariffRepository;
    private final RestTemplate restTemplate;

    public TariffDTO save(Tariff tariff) throws Exception {
        try {
            Tariff t = tariffRepository.save(tariff);
            return new TariffDTO(t);
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}
