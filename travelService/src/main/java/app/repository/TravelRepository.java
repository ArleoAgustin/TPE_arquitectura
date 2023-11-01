package app.repository;

import app.model.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TravelRepository extends JpaRepository<Travel,Long> {

    @Query("SELECT t.scooterID, COUNT(t) " +
            "FROM Travel t " +
            "WHERE FUNCTION('YEAR', t.date) = :year " +
            "GROUP BY t.scooterID " +
            "HAVING COUNT(t) > :x")
    List<Long> findScootersWithMoreThanXTravelsInYear(@Param("x") Integer x, @Param("year") Integer year);

    @Query("SELECT SUM(t.finalPrice) FROM Travel t WHERE t.date >= :start AND t.date <= :end")
    Double getTotalBillingBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );


}
