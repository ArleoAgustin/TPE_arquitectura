package app.service;

import app.model.classes.Scooter;
import app.model.classes.User;
import app.model.entities.Travel;
import app.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final RestTemplate restTemplate;

    @Transactional(readOnly = true)
    public List<Travel> findAll() {
        return this.travelRepository.findAll();
    }

    public Travel delete(Long id) {
        Optional<Travel> travel = travelRepository.findById(id);
        if (travel.isPresent()){
            travelRepository.delete(travel.get());
            return travel.get();
        }
        else return null;
    }

    public Travel getById(Long id) {
        Optional<Travel> travel = this.travelRepository.findById(id);
        return travel.orElse(null);
    }

    @Transactional
    public Travel addTravel(Long userDNI, Long scooterId) {

        String userURL = "https://puerto y endpoint"+ userDNI;
        String scooterURL = "https://scooter/"+scooterId;
        String tariffURL = "https://getTariff";
        //todo manejar los timeOut de las request
        User user = restTemplate.getForObject(userURL, User.class);
        Scooter scooter = restTemplate.getForObject(scooterURL, Scooter.class);
        Double tariff = restTemplate.getForObject(tariffURL,Double.class);

        assert user != null;
        assert scooter != null;
        if(user.getState().equals('D') && scooter.getState().equals('D')) {
            Travel travel = new Travel(tariff, userDNI, scooter.getId());
            return this.travelRepository.save(travel);
        }
        return null;
    }

}
