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
    public List<AdminDTO> findAll(){
        return this.adminRepository.findAll().stream().map(AdminDTO::new).toList();
    }

    public AdminDTO save(Admin admin) throws Exception {
        try {
            Admin a = adminRepository.save(admin);
            return new AdminDTO(a);
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Transactional
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
    }

    @Transactional
    public boolean delete(Long id){
        if (adminRepository.existsById(id)){
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ResponseEntity addScooter(Scooter scooter){

        HttpHeaders headers = new HttpHeaders();
        Scooter newScooter = new Scooter(scooter);
        HttpEntity<Scooter> requestEntity = new HttpEntity<>(newScooter, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8083/scooters/add",
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        return response;
    }
}
