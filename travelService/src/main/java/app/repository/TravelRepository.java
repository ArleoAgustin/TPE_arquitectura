package app.repository;

import app.model.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel,Long> {

}
