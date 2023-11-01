package app.DTO;

import app.model.entities.Admin;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminDTO implements Serializable {

    private Long DNI;
    private String name;
    private String lastName;
    private String rol;


    public AdminDTO(Admin a) {

        this.DNI = a.getDni();
        this.name = a.getName();
        this.lastName = a.getLastName();
       this.rol = a.getRole();
    }


}
