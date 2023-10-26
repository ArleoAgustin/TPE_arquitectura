package app.repository;

import app.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long>{

    List<Scooter> findAllByStateIs(Character m);

}