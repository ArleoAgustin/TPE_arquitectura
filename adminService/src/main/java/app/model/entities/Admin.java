package app.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    private Long dni;

    @Column
    private String lastName;

    @Column
    private String name;

    @Column
    private String role;

    public Admin() {

    }

    public Admin(Long dni, String lastName, String name, String role) {
        this.dni = dni;
        this.lastName = lastName;
        this.name = name;
        this.role = role;
    }

    public Long getDni() {
        return dni;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String  role) {
        this.role = role;
    }
}
