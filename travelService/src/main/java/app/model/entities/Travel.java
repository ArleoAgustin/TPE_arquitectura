package app.model.entities;

import app.model.classes.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

@Data
@Entity
public class Travel implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Column
    private LocalDateTime date;
    @Column
    private LocalDateTime start;
    @Column
    private LocalDateTime end;
    @Column
    private Double kmTraveled;
    @Getter
    @Column
    private Double tariff;
    @Column
    private Long userDNI;
    @Column
    private Long scooterID;
    @Column
    private Double finalPrice;

    public Travel(Double tariff, Long userDNI, Long scooterID) {
        this.date = LocalDateTime.now();
        this.start = LocalDateTime.now();
        this.kmTraveled = Math.random() * 50;
        this.tariff = tariff;
        this.userDNI = userDNI;
        this.scooterID = scooterID;
        this.finalPrice = kmTraveled * getTariff();
    }

    public Travel(){
        this.date = LocalDateTime.now();
        this.start = LocalDateTime.now();
        this.kmTraveled = Math.random() * 50;

    }


    public Travel(Travel nT) {
        this.id = nT.getId();
        this.date = nT.getDate();
        this.start = nT.getStart();
        this.end = nT.getEnd();
        this.kmTraveled = Math.random() * 50;
        this.tariff = nT.getTariff();
        this.userDNI = nT.getUserDNI();
        this.scooterID = nT.getScooterID();
        this.finalPrice = kmTraveled * getTariff();
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

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    public Long getUserDNI() {
        return userDNI;
    }

    public Long getScooterID() {
        return scooterID;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
