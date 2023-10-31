package app.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

public class ScooterReportByKm {

    private final Long scooterId;


    private Double kms;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Double timeInPause;

    public ScooterReportByKm(Long scooterId, Double kms) {
        this.scooterId = scooterId;
        this.kms = kms;
        this.timeInPause = null;
    }

    public ScooterReportByKm(Long scooterId, Double kms, Double timeInPause) {
        this.scooterId = scooterId;
        this.kms = kms;
        this.timeInPause = timeInPause;
    }

    public Double getTimeInPause() {
        return timeInPause;
    }

    public void setTimeInPause(Double timeInPause) {
        this.timeInPause = timeInPause;
    }

    public Double getKms() {
        return kms;
    }

    public void setKms(Double kms) {
        this.kms = kms;
    }
}
