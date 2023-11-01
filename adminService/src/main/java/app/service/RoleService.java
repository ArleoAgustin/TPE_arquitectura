package app.service;

import app.model.entities.Role;
import app.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public boolean add(String r) {

        Role newr = new Role(r);
        roleRepository.save(newr);
        return true;

}

    public List getAllRol(){

        return roleRepository.findAll();
    }

    public boolean existsRoleByName(String nameRol){

        return roleRepository.existsRoleByNameRole(nameRol);

    }

    public boolean delete(Long id_role){

        if (roleRepository.existsById(id_role)){

            roleRepository.deleteById(id_role);
            return true;
        }
        return false;
    }


}
