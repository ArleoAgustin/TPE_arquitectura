package app.dto.user.response;


import app.model.AuthUser;
import lombok.Data;

@Data
public class UserResponseDTO {

    private final Long id;
    private final String name;
    private final String lastName;
    private final String email;

    public UserResponseDTO( AuthUser authUser){
        this.id = authUser.getId();
        this.name = authUser.getName();
        this.lastName = authUser.getLastName();
        this.email = authUser.getEmail();
    }

}
