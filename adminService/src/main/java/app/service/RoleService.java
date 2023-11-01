package app.service;

import app.model.entities.Role;
import app.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public ResponseEntity<?> add(String r) {

        if (!existsRoleByName(r)) {
            Role newr = new Role(r);
            roleRepository.save(newr);
            return ResponseEntity.status(HttpStatus.OK).body("Rol agregado correctamente");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el rol ya existe");
    }

    public List getAllRol(){

        return roleRepository.findAll();
    }

    public boolean existsRoleByName(String nameRol){

        return roleRepository.existsRoleByNameRole(nameRol);

    }

    public ResponseEntity<?> delete(Long id_role) throws Exception {
        try {
            if (roleRepository.existsById(id_role)) {
                roleRepository.deleteById(id_role);
                return ResponseEntity.status(HttpStatus.OK).body("Rol eliminado correctamente");
            } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol no existe");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
