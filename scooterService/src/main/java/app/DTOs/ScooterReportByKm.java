package app.DTOs;

import lombok.Getter;
import lombok.Setter;

public class ScooterReportByKm {

    @Getter
    private final Long scooterId;

    @Getter
    @Setter
    private Double kms;


    public ScooterReportByKm(Long scooterId, Double kms) {
        this.scooterId = scooterId;
        this.kms = kms;
    }

}
