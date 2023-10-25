package app.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dni;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "id_role")
    private Role rol;

    public Admin() {

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getDni() {
        return dni;
    }
}
