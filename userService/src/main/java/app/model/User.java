package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class User {

    @Id
    private Long dni;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(nullable=false)
    private String lastName;

    @Getter
    @Setter
    @Column(nullable=false)
    private Long numberPhone;

    @Getter
    @Setter
    @Column(nullable=false)
    private String email;

    @Getter
    @Setter
    @Column
    private String startDate;


    @Getter
    @Setter
    @Column(nullable = false)
    private Character state;

    public User() {

    }

    public User(String name, String lastName, Long numberPhone, String email, String startDate, Character state) {
        this.name = name;
        this.lastName = lastName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.startDate = startDate;
        this.state = state;
    }

    public User(User user) {}



}
