package app.service;

import app.DTO.AdminDTO;
import app.DTO.TariffDTO;
import app.model.classs.Maintenance;
import app.model.classs.Scooter;
import app.model.classs.User;
import app.model.entities.Admin;
import app.model.entities.Tariff;
import app.repository.AdminRepository;
import app.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final RestTemplate restTemplate;

//obtiene todos los admin

    @Transactional(readOnly = true)
    public List<AdminDTO> findAll(){
        return this.adminRepository.findAll().stream().map(AdminDTO::new).toList();
    }

//crea un admin nuevo

    public AdminDTO save(Admin admin) throws Exception {
        try {
            Admin a = adminRepository.save(admin);
            return new AdminDTO(a);
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

//actualiza los datos de un administrador

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

//elimina un administrador

    @Transactional
    public boolean delete(Long id){
        if (adminRepository.existsById(id)){
            adminRepository.deleteById(id);
        return true;
        }
        return false;
    }

//agrega un monopatin

    public ResponseEntity addScooter(Scooter scooter){

        HttpHeaders headers = new HttpHeaders();
        Scooter newScooter = new Scooter(scooter);
        HttpEntity<Scooter> requestEntity = new HttpEntity<>(newScooter, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8083/scooter",
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        return response;
    }
/*
    private ResponseEntity addScooterToMaintenance(Long id_scooter){    //le pega a scooter para actualizar el estado

        HttpHeaders headers = new HttpHeaders();
        Maintenance newMaintenace = new Maintenance();
        newMaintenace.setScooterId(id_scooter);
        newMaintenace.setTimeStartMaintenance(LocalDate.now());
        newMaintenace.setTimeEndMaintenance(null);
        newMaintenace.setRepaired(false);

        HttpEntity<Maintenance> requestEntity = new HttpEntity<>(newMaintenace, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8085/scooter/addMaintenance/" + id_scooter,
                HttpMethod.PUT,
                requestEntity,
                String.class
        );
        return response;
    }
*/

//elimina un monopatin

    public ResponseEntity deleteScooter(Long id_scooter){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8083/scooter/"+ id_scooter,
                HttpMethod.DELETE,
                requestEntity,
                String.class
        );
        if (response != null){
            return ResponseEntity.ok("Monopatin eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el monopatin");
    }

//obtiene todos los monopatines en manteminiento

    public ResponseEntity getScooterInMaintenance(){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Scooter>> response = restTemplate.exchange(
                "http://localhost:8083/scooter/inMaintenance",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Scooter>>() {}
        );
        headers.setContentType(MediaType.APPLICATION_JSON);
        return response;
    }

//agrega un monopatin a mantenimiento

    public ResponseEntity addScooterToMaintenance(Long id_scooter){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Scooter> response = restTemplate.exchange(
                "http://localhost:8083/scooter/addScooterMaintenance/"+ id_scooter,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<Scooter>() {}
        );
        return response;

    }

//saca un monopatin de mantenimiento

    public ResponseEntity removeScooterOfMaintenance(Long id_scooter){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Scooter> response = restTemplate.exchange(
                "http://localhost:8083/scooter/removeScooterMaintenance/"+ id_scooter,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<Scooter>() {}
        );
        return response;
    }

//modifica la disponibilidad de la cuenta de un determinado usuario

    public ResponseEntity avaibleUserAccount(Long id_user, boolean avaible){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(
                "http://localhost:8080/user/enableAccount/"+ id_user, //cambie a enable en url
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<User>() {}
        );
        return  response;   //TODO esto tiene que estar en implementar en user con este endpoint "http://localhost:8080/user/enableAccount"+ id_user
    }

    public ResponseEntity disableUserAccount(Long id_user, boolean disable){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(
                "http://localhost:8080/user/disableAccount/"+ id_user,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<User>() {}
        );
        return  response;   //TODO esto tiene que estar en implementar en user con este endpoint "http://localhost:8080/user/disableAccount"+ id_user
    }

    public ResponseEntity getScootersByTravelsInYear(int numTravels, String year){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Scooter>> response = restTemplate.exchange(
                "http://localhost:8084/travel/getByTravelsInYear/"+ numTravels + "/" + year,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Scooter>>() {}
        );
        headers.setContentType(MediaType.APPLICATION_JSON);
        return response;
    }


}
