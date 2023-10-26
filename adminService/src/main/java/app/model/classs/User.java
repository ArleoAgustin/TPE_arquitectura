package app.model.classs;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String nombre;
    private String apellido;
    private boolean avaibleAccount;

    public User(User u) {
        this.nombre = u.getNombre();
        this.apellido = u.getApellido();
        this.avaibleAccount = u.isAvaibleAccount();
    }

    public User() {
    }
}
