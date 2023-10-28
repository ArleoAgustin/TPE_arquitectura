package app.model.classes;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
public class Scooter implements Serializable {

    public static final Character AVALIABLE = 'A';
    public static final Character IN_USE = 'I';
    public static final Character DISABLED = 'D';
    public static final Character IN_MANTENIENCE = 'M';

    private Long id;
    private Character state;
    private String ubication;
    private Double km;
    private Integer countTravel;

    public Scooter(Scooter scooter) {
    }

    public Scooter() {}

    public Long getId() {
        return id;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Integer getCountTravel() {
        return countTravel;
    }

    public void setCountTravel(Integer countTravel) {
        this.countTravel = countTravel;
    }
}
