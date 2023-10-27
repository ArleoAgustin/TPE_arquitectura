package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    private Long dni;
    @Column(nullable = false)
    private String name;
    @Column(nullable=false)
    private String lastName;
    @Column(nullable=false)
    private Long numberPhone;
    @Column(nullable=false)
    private String email;
    @Column
    private String startDate;

    @Column(nullable = false)
    private Character state;

    public User() {

    }

    public User(Long dni, String name, String lastName, Long numberPhone, String email, String startDate,Character state) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.startDate = startDate;
        this.state=state;
    }


    public User(User user) {

    }



}
