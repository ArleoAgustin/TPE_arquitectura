package app.service;

import app.DTO.AdminDTO;

import app.model.entities.Admin;
import app.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
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


}
