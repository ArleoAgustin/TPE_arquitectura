package app.service;

import app.model.entities.Travel;
import app.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;


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

    public Travel addTravel(Travel travel) {
        return this.travelRepository.save(travel);
    }

}
  /*  @Transactional(readOnly = true)
    public List<AdminDTO> findAll(){
        return this.adminRepository.findAll().stream().map(AdminDTO::new).toList();
    }*/