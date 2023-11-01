package app.service;

import app.DTO.AdminDTO;

import app.model.entities.Admin;
import app.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final RoleService roleService;
    private final RestTemplate restTemplate;

//obtiene todos los admin

    @Transactional(readOnly = true)
    public List<AdminDTO> findAll(){
        List<Admin> admins = adminRepository.findAll();
        return  this.converToAdminDTO(admins);
    }

//crea un admin nuevo

    @Transactional
    public ResponseEntity<?> save(Admin admin) throws Exception {
        try {
            if (!adminRepository.existsById(admin.getDni())) {
                if (roleService.existsRoleByName(admin.getRole())) {
                    adminRepository.save(admin);
                    return ResponseEntity.status(HttpStatus.OK).body("Administrador agregado correctamente");
                }
                else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol ingresado es invalido");
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el usuario ya existe");
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

//agrega un rol

    @Transactional
    public ResponseEntity<?> addRol(String nameRol) throws Exception {
        try {
           return roleService.add(nameRol);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

//obtiene todos los roles

    @Transactional(readOnly = true)
    public List getAllRol(){return roleService.getAllRol();}

//actualiza los datos de un administrador

    @Transactional
    public ResponseEntity<?> update(Long idAdmin, Admin updateAdmin)throws Exception{
        try {
            Optional<Admin> existsAdmin = adminRepository.findById(idAdmin);
            if (existsAdmin.isPresent()) {
                if (roleService.existsRoleByName(updateAdmin.getRole())) {
                    Admin admin = existsAdmin.get();
                    admin.setName(updateAdmin.getName());
                    admin.setLastName(updateAdmin.getLastName());
                    admin.setRole(updateAdmin.getRole());
                    adminRepository.save(admin);
                    return ResponseEntity.status(HttpStatus.OK).body("Datos actualizados correctamente");
                } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol ingresado no existe");
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El administrador no existe");
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

//elimina un administrador

    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {

        try {
            if (adminRepository.existsById(id)) {
                adminRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Administrador eliminado correctamente");
            } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El administrador no existe");
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

//elimina un rol

    @Transactional
    public ResponseEntity<?> deleteRol(Long idRol) throws Exception {
        return roleService.delete(idRol);
    }


//convierte una lista de admin a adminDTO

    private List<AdminDTO> converToAdminDTO(List<Admin> admins){

        List<AdminDTO> result = new ArrayList<>();
        admins.forEach(a -> {
            AdminDTO adminDTO = new AdminDTO(a);
            result.add(adminDTO);
        });
        return result;
    }

}
