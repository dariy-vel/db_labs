package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.AirportEntity;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Integer> {
}
