package app.model;


import jakarta.persistence.*;
import lombok.Data;
 @Entity
 @Data
public class Scooter {//TODO: getters and seters

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



     public Scooter() {

     }
 }
