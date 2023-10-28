package app.model;


import jakarta.persistence.*;
import lombok.Data;
 @Entity
 @Data
public class Scooter {

        public static final Character AVALIABLE = 'A';
        public static final Character IN_USE = 'I';
        public static final Character DISABLED = 'D';
        public static final Character IN_MANTENIENCE = 'M';

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private Character state;

        @Column(nullable = false)
        private String ubication;

         @Column(nullable = false)
         private Double km;

         @Column(nullable = false)
         private Integer countTravel;


     public Scooter(Scooter scooter) {
     }

     public Scooter(Character state, String ubication, Double km, Integer countTravel) {
         this.state = state;
         this.km = km;
         this.countTravel = countTravel;
         this.ubication = ubication;
     }

     public Scooter() {}

     public Long getId() {
         return id;
     }

     public Character getState() {
         return state;
     }

     public void setState(Character state) {
         this.state = state;
     }

     public String getUbication() {
         return ubication;
     }

     public void setUbication(String ubication) {
         this.ubication = ubication;
     }

     public Double getKm() {
         return km;
     }

     public void setKm(Double km) {
         this.km = km;
     }

     public Integer getCountTravel() {
         return countTravel;
     }

     public void setCountTravel(Integer countTravel) {
         this.countTravel = countTravel;
     }
 }
