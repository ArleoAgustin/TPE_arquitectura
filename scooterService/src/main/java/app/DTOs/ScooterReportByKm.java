package app.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ScooterReportByKm {

    private final Long scooterId;

    @Setter
    private Double kms;


    public ScooterReportByKm(Long scooterId, Double kms) {
        this.scooterId = scooterId;
        this.kms = kms;
    }

}
