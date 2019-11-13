package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.AirCompanyEntity;

@Repository
public interface AirCompanyRepository  extends JpaRepository<AirCompanyEntity, Integer> {
}
