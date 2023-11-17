package app.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true )
public class UserRequestDTO {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Set<String> authorities;
}
