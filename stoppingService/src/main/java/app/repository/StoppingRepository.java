package app.repository;

import app.model.Stopping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoppingRepository extends JpaRepository<Stopping, Long> {
}
