package app.repository;

import app.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long>{



}