package app.model.entities;

import app.model.classes.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

@Data
@Entity
public class Travel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime date;
    @Column
    private LocalDateTime start;
    @Column
    private LocalDateTime end;
    @Column
    private Double kmTraveled;
    @Column
    private Double tariff;
    @Column
    private Long userDNI;
    @Column
    private Long scooterID;

    public Travel(Double tariff, Long userDNI, Long scooterID) {
        this.start = LocalDateTime.now();
        this.kmTraveled = 0.0;
        this.tariff = tariff;
        this.userDNI = userDNI;
        this.scooterID = scooterID;
    }

    public Travel(){
        this.start = LocalDateTime.now();
        this.kmTraveled = 0.0;
    }

    public Travel(Travel travel) {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Double getKmTraveled() {
        return kmTraveled;
    }

    public void setKmTraveled(Double kmTraveled) {
        this.kmTraveled = kmTraveled;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    public Long getUserDNI() {
        return userDNI;
    }

    public Long getScooterID() {
        return scooterID;
    }

}
