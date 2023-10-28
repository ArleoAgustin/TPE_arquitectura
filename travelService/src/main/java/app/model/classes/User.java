package app.model.classes;

import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    public static final Character AVALIABLE = 'A';
    public static final Character DISABLED = 'D';

    private Long dni;
    private String name;
    private String lastName;
    private Long numberPhone;
    private String email;
    private String startDate;
    private Character state;

    public User(Long dni, String name, String lastName, Long numberPhone, String email, String startDate,Character state) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.startDate = startDate;
        this.state=state;
    }

    public User(User user) {}

    public Long getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Long numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Character getState() {
        return state;
    }

}
