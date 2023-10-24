package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Administrador {

    @Id
    private Long dni;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Rol rol;


    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getDni() {
        return dni;
    }
}
