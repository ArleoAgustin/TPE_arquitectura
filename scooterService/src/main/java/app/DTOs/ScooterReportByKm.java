package app.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

public class ScooterReportByKm {

    private final String idScooter;


    private Double kms;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Double timeInPause;

    public ScooterReportByKm(String idScooter, Double kms) {
        this.idScooter = idScooter;
        this.kms = kms;
        this.timeInPause = null;
    }

    public ScooterReportByKm(String idScooter, Double kms, Double timeInPause) {
        this.idScooter = idScooter;
        this.kms = kms;
        this.timeInPause = timeInPause;
    }

    public String getIdScooter() {
        return idScooter;
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
