package app.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameRole;

    public Role(String nameRole) {
        this.nameRole = nameRole;
    }

    public Role() {
    }
}
