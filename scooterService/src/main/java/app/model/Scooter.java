package app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Scooter {

    public static final Character AVALIABLE = 'A';
    public static final Character IN_USE = 'I';
    public static final Character DISABLED = 'D';
    public static final Character IN_MANTENIENCE = 'M';

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Character state;
    private String ubication;
    private Double km;
    private Integer countTravel;
    private Double timeInPause;

     public Scooter(Scooter scooter) {
     }

     public Scooter(Character state, String ubication, Double km, Integer countTravel) {
         this.state = state;
         this.km = km;
         this.countTravel = countTravel;
         this.ubication = ubication;
     }

    public void setState(Character state) {
         this.state = state;
     }

    public void setUbication(String ubication) {
         this.ubication = ubication;
     }

    public void setKm(Double km) {
         this.km = km;
     }

    public void setCountTravel(Integer countTravel) {
         this.countTravel = countTravel;
     }
 }

