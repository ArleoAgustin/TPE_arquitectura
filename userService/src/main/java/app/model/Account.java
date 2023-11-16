package app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private Double saldo;

    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "accounts" )
    private Set<User> usuarios;

}
