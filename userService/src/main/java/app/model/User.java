package app.model;

import app.dto.user.request.UserRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Getter
public class User implements Serializable {

    @Id
    private Long dni;


    @Setter
    @Column(nullable = false)
    private String name;


    @Setter
    @Column(nullable=false)
    private String lastName;

    @Setter
    @Column
    private String userName;


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


    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "rel_user__account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Account> accounts;

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "rel_user__authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;


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
        this.userName = user.getUserName();

    }


    public User(UserRequestDTO request) {
        this.dni = request.getDni();
        this.name = request.getName();
        this.lastName = request.getLastName();
        this.email = request.getEmail();
        this.state = request.getState();
        this.numberPhone = request.getNumberPhone();
        this.startDate = request.getStartDate();
        this.userName = request.getUserName();
    }

    public void setAccount(List accounts ){
        this.accounts = new HashSet<>( accounts );
    }

    public void setAuthorities(List authorities ){
        this.authorities = new HashSet<>( authorities );
    }



}
