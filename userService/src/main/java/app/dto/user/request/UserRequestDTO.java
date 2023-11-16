package app.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true )
public class UserRequestDTO {

    private Long dni;
    private String name;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Character state;
    private Long numberPhone;
    private String startDate;
    private Set<Long> accounts;
    private Set<String> authorities;

}
