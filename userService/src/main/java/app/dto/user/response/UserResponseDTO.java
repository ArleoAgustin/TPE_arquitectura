package app.dto.user.response;


import app.model.User;
import lombok.Data;

@Data
public class UserResponseDTO {

    private final long dni;
    private final String name;
    private final String lastName;
    private final String email;

    public UserResponseDTO( User user ){
        this.dni = user.getDni();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }

}
