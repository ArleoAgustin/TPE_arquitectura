package app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private Long dni;

    private String name;
    private String lastName;
    private Long numberPhone;
    private String email;
    private String startDate;
}
