package app.repository;

import app.DTOs.ScooterReportByKm;
import app.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long>{

    List<Scooter> findAllByStateIs(Character m);
    List<Scooter> findScootersByCountTravelIsGreaterThan(Integer i);

    List<Scooter> findAllByUbicationEquals(String ubication);

    @Query("SELECT s FROM Scooter s WHERE s.km >= :kms ORDER BY s.km DESC")
    List<Scooter> getAllOrderByCantKm(@Param("kms") double kms);

    List<Scooter> findAllByIdIn(List<Long> scooterIds);
}