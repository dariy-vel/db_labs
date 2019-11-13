package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
}
