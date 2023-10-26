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

    /*@Transactional
    public AdminDTO update(Long idAdmin, Admin updateAdmin)throws Exception{
        try {
            Optional<Admin> existsAdmin = adminRepository.findById(idAdmin);
            if (existsAdmin.isPresent()) {
                Admin admin = existsAdmin.get();
                admin.setNombre(updateAdmin.getNombre());
                admin.setApellido(updateAdmin.getApellido());
                admin.setRol(updateAdmin.getRol());
                Admin updatAdmin = adminRepository.save(admin);
                AdminDTO adminDTO = new AdminDTO(updatAdmin);
                return adminDTO;
            }
            throw  new Exception("Administrador no encontrado");
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }*/

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

}
