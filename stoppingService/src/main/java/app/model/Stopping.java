package app.model;

import app.model.classes.Scooter;
import jakarta.persistence.*;
import lombok.Data;

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
        this.ubication = ubication;
        this.scooters = new ArrayList<>(); // Inicializa la lista en el constructor
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
        if (this.scooters == null) {
            this.scooters = new ArrayList<>();
        }
        this.scooters.add(scooter);
    }
}
