package app.DTO;

import app.model.User;
import lombok.Data;

@Data
public class UserResponseDTO {

    private final long id;
    private final String nombre;
    private final String apellido;
    private final String email;

    public UserResponseDTO( User user ){
        this.id = user.getDni();
        this.nombre = user.getName();
        this.apellido = user.getLastName();
        this.email = user.getEmail();
    }

}
