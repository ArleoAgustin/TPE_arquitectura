package app.model;

import jakarta.persistence.*;
import lombok.Data;
import app.model.classes.Scooter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Stopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ubication;

    @Column
    private List<Scooter> scooters;

    public Stopping(String ubication) {
        this.scooters = new ArrayList<>();
        this.ubication = ubication;
    }

    public Stopping() {}

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public List<Scooter> getScooters() {
        return scooters;
    }

    public void addScooter(Scooter scooter) {
        this.scooters.add(scooter);
    }
}
