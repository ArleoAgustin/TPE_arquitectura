package app.service;

import app.DTOs.ScooterReportByKm;
import app.DTOs.ScooterReportByKmAndTimePaused;
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

    public List<Scooter> getScooterByStatus(Character s) {
        return scooterRepository.findAllByStateIs(s);
    }

    public List<ScooterReportByKm> getReportForKm(Boolean include, double kms) {
        List<ScooterReportByKm> report = new ArrayList<>();

        if(!include){
            this.scooterRepository.getAllOrderByCantKm(kms).forEach(scooter ->
                    report.add(new ScooterReportByKm(scooter.getId(), scooter.getKm())));
            return report;
        }else
            this.scooterRepository.getAllOrderByCantKm(kms).forEach(scooter ->
                    report.add(new ScooterReportByKm(scooter.getId(), scooter.getKm(), scooter.getTimeInPause())));
        return report;
    }

    public List<Scooter> getNearby(String ubication) {
        return this.scooterRepository.findAllByUbicationEquals(ubication);
    }
}
