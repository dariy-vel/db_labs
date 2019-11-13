package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.model.AirportEntity;
import ua.lviv.iot.model.CountryEntity;
import ua.lviv.iot.repository.AirportRepository;
import ua.lviv.iot.repository.CountryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;

    public AirportEntity getAirport(Integer id) throws NoSuchElementException {
        AirportEntity airportEntity = airportRepository.findById(id).get();
        if (airportEntity == null) throw new NoSuchElementException();
        return airportEntity;
    }

    public List<AirportEntity> getAllAirports() {
        return airportRepository.findAll();
    }

    @Transactional
    public void createAirport(AirportEntity airportEntity) {
        airportRepository.save(airportEntity);
    }

    @Transactional
    public void updateAirport(AirportEntity uAirportEntity, Integer id) throws NoSuchElementException {
        AirportEntity airportEntity = airportRepository.findById(id).get();
        if (airportEntity == null) throw new NoSuchElementException();

        airportEntity.setName(uAirportEntity.getName());
    }

    @Transactional
    public void deleteAirport(Integer id) throws NoSuchElementException {
        AirportEntity airportEntity = airportRepository.findById(id).get();

        if (airportEntity == null) throw new NoSuchElementException();
        airportRepository.delete(airportEntity);
    }
}

