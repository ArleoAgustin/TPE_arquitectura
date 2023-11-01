package app.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Scooter {

        public static final Character AVALIABLE = 'A';
        public static final Character IN_USE = 'I';
        public static final Character DISABLED = 'D';
        public static final Character IN_MANTENIENCE = 'M';

        @Getter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Getter
        @Column(nullable = false)
        private Character state;

        @Getter
        @Column(nullable = false)
        private String ubication;

         @Getter
         @Column(nullable = false)
         private Double km;

         @Getter
         @Column(nullable = false)
         private Integer countTravel;

         @Column
         private Double timeInPause;

     public Scooter(Scooter scooter) {
     }

     public Scooter(Character state, String ubication, Double km, Integer countTravel) {
         this.state = state;
         this.km = km;
         this.countTravel = countTravel;
         this.ubication = ubication;
     }

     public Scooter() {}

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
