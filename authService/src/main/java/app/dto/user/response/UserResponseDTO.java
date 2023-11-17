package app.dto.user.response;


import app.model.AuthUser;
import lombok.Data;

@Data
public class UserResponseDTO {

    private final long dni;
    private final String name;
    private final String lastName;
    private final String email;

    public UserResponseDTO( AuthUser authUser){
        this.dni = authUser.getDni();
        this.name = authUser.getName();
        this.lastName = authUser.getLastName();
        this.email = authUser.getEmail();
    }

}
