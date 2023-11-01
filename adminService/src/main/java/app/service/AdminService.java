package app.service;

import app.DTO.AdminDTO;

import app.model.entities.Admin;
import app.model.entities.Role;
import app.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
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

    public boolean save(Admin admin) throws Exception {
        try {
            if ((!adminRepository.existsById(admin.getDni())) && (roleService.existsRoleByName(admin.getRole()))){
                adminRepository.save(admin);
            return true;
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return false;
    }

    public boolean addRol(String nameRol) throws Exception {
        try {
           return roleService.add(nameRol);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    public List getAllRol(){
        return roleService.getAllRol();
    }

//actualiza los datos de un administrador

    @Transactional
    public AdminDTO update(Long idAdmin, Admin updateAdmin)throws Exception{
        try {
            Optional<Admin> existsAdmin = adminRepository.findById(idAdmin);
            if (existsAdmin.isPresent()) {
                Admin admin = existsAdmin.get();
                admin.setName(updateAdmin.getName());
                admin.setLastName(updateAdmin.getLastName());
             //   admin.setRole(updateAdmin.getRole());
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


    public boolean deleteRol(Long idRol){

        return roleService.delete(idRol);
    }


    private List<AdminDTO> converToAdminDTO(List<Admin> admins){

        List<AdminDTO> result = new ArrayList<>();
        admins.forEach(a -> {
            AdminDTO adminDTO = new AdminDTO(a);
            result.add(adminDTO);
        });
        return result;
    }

}
