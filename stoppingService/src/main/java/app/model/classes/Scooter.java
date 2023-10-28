package app.model.classes;

import lombok.Data;

import java.io.Serializable;

@Data
public class Scooter implements Serializable {

    private char status;
    private String ubication;
    private double kmsUsed;

    public Scooter(Scooter s) {
        this.status = s.getStatus();
        this.ubication = s.getUbication();
        this.kmsUsed = s.getKmsUsed();
    }

}
