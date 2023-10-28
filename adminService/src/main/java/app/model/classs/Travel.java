package app.model.classs;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Travel implements Serializable {

    private LocalDateTime date;
    private LocalDateTime start;
    private LocalDateTime end;
    private Double kmTraveled;
    private Double tariff;
    private Long userDNI;
    private Long scooterID;

    public Travel(Travel t) {
        this.date = t.getDate();
        this.start = t.getStart();
        this.end = t.getEnd();
        this.kmTraveled = t.getKmTraveled();
        this.tariff = t.getTariff();
        this.userDNI = t.getUserDNI();
        this.scooterID = t.getScooterID();
    }
}
