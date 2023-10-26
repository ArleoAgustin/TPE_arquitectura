package app.repository;

import app.model.entities.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
