package app.service;

import app.model.Scooter;
import app.repository.ScooterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScooterService {

    private final ScooterRepository scooterRepository;
    private final RestTemplate restTemplate;

    @Transactional(readOnly = true)
    public List<Scooter> findAll(){
        return this.scooterRepository.findAll().stream().map(Scooter::new).toList();
    }

    public Scooter save(Scooter scooter) throws Exception {
        try {
            Scooter s = scooterRepository.save(scooter);
            return new Scooter(s);
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    public List<Scooter> getScooterInMaintenance(){
        return scooterRepository.findAllByStateIs('M');
    }

    @Transactional
    public boolean delete(Long id){
        if (scooterRepository.existsById(id)){
            scooterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Object addScooterToMaintenance(Long idScooter) {
        Optional<Scooter> scooter = scooterRepository.findById(idScooter);
        if(scooter.isPresent()){
            scooter.get().setState('M');
            scooterRepository.save(scooter.get());
            return scooter.get();
        }
        return null;
    }

    @Transactional
    public Object removeScooterOfMaintenance(Long idScooter) {
        Optional<Scooter> scooter = scooterRepository.findById(idScooter);
        if(scooter.isPresent()){
            scooter.get().setState('D');
            scooterRepository.save(scooter.get());
            return scooter.get();
        }
        return null;
    }

    public Scooter getById(Long id) {
        Optional<Scooter> result = this.scooterRepository.findById(id);
        return result.orElse(null);
    }

    /*public List<Scooter> getScootersWithMoreThanTravelsInYear(Integer numTravels, Integer year) {
        List<Scooter> scooterList = new ArrayList<>();
        ///restTemplate.exchange(le pego a la url de facturacion que me devuelve los scooters que viajaron mas de numTravels en el year)
        return scooterList;
    }*/

}
