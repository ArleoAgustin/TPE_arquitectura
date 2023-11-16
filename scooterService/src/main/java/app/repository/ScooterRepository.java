package app.repository;

import app.model.Scooter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends MongoRepository<Scooter, String> {

    List<Scooter> findAllByStateIs(Character m);
    List<Scooter> findScootersByCountTravelIsGreaterThan(Integer i);
    List<Scooter> findAllByUbicationEquals(String ubication);

    @Query("{ 'km' : { $gte : ?0 } }")
    List<Scooter> getAllOrderByCantKm(double kms);

    List<Scooter> findAllByIdIn(List<String> scooterIds); // Cambiado Long a String dependiendo del tipo de id en Scooter
}
