package app.service;

import app.model.classes.Scooter;
import app.model.classes.Tariff;
import app.model.classes.User;
import app.model.entities.Travel;
import app.repository.TravelRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final RestTemplate restTemplate ;

    @Transactional(readOnly = true)
    public List<Travel> findAll() {
        return this.travelRepository.findAll();
    }

    public ResponseEntity<?> delete(Long id) throws Exception {

        try {
            Optional<Travel> travel = travelRepository.findById(id);
            if (travel.isPresent()) {
                travelRepository.delete(travel.get());
                return ResponseEntity.status(HttpStatus.OK).body("Viaje eliminado correctamente");
            }
            else return ResponseEntity.status(HttpStatus.OK).body("El viaje no existe");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Travel getById(Long id) {
        return this.travelRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<?> addTravel(Long userDNI, Long scooterId) {

        String userURL = "http://localhost:8081/user/"+ userDNI;
        String scooterURL = "http://localhost:8083/scooter/"+scooterId;
        String tariffURL = "http://localhost:8082/tariff/currentPrice";

        try {
            User user = restTemplate.getForObject(userURL, User.class);
            Scooter scooter = restTemplate.getForObject(scooterURL, Scooter.class);
            Tariff tariff = restTemplate.getForObject(tariffURL,Tariff.class);

            if(user.getState().equals(User.AVALIABLE) && scooter.getState().equals(Scooter.AVALIABLE)) {

                Travel travel = new Travel(tariff.getPrice(), userDNI, scooter.getId());
                this.travelRepository.save(travel);
                return ResponseEntity.status(HttpStatus.OK).body("Viaje agregado correctamente");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario o el monopatin no esta disponible");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<Scooter> getScootersWithMoreThanTravelsInYear(Integer travels, Integer year) throws Exception {
        try {
            List<Long> scootersId = this.travelRepository.findScootersWithMoreThanXTravelsInYear(travels, year);

            if (!scootersId.isEmpty()) {
                String url = "http://localhost:8083/scooter/getAllByIds";
                URI targetUrl = UriComponentsBuilder.fromUriString(url)
                        .queryParam("ids", scootersId)
                        .build()
                        .toUri();

                ResponseEntity<List<Scooter>> response = restTemplate.exchange(
                        targetUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Scooter>>() {}
                );

                if (response.getStatusCode().is2xxSuccessful()) {
                    return response.getBody();
                } else {
                    return null;
                }
            }else
                return new ArrayList<>();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Double getBillingBetweenIn(Integer year, Integer month1, Integer month2) {
        LocalDateTime startDate = LocalDateTime.of(year, month1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, month2, LocalDateTime.MAX.getDayOfMonth(), 0, 0);
        return this.travelRepository.getTotalBillingBetween(startDate, endDate);
    }
}
