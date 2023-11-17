package app.model;

import app.DTO.UserRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
public class User {

    @Id
    private Long dni;


    @Setter
    @Column(nullable = false)
    private String name;


    @Setter
    @Column(nullable=false)
    private String lastName;


    @Setter
    @Column(nullable=false)
    private Long numberPhone;


    @Setter
    @Column(nullable=false)
    private String email;


    @Setter
    @Column
    private String startDate;




    @Column(nullable = false)
    private Character state;


    @Setter
    @Column
    private String rol;

    @Setter
    @Column
    private String password;

    public User() {

    }


    public User(Long dni, String name, String lastName, Long numberPhone, String email, String startDate, Character state, String rol, String password) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.startDate = startDate;
        this.state = state;
        this.rol = rol;
        this.password = password;
    }

    public User(User user) {
        this.dni = user.getDni();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.numberPhone = user.getNumberPhone();
        this.email = user.getEmail();
        this.startDate = user.getStartDate();
        this.state = user.getState();
        this.rol = user.getRol();

    }


    public User(UserRequestDTO request) {
        this.dni = request.getDni();
        this.name = request.getName();
        this.lastName = request.getLastName();
        this.email = request.getEmail();
        this.state = request.getState();
        this.numberPhone = request.getNumberPhone();
        this.startDate = request.getStartDate();
    }



}