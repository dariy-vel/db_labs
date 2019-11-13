package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ua.lviv.iot.model.AirportEntity;

import java.util.NoSuchElementException;

public class AirportDTO extends RepresentationModel {
    AirportEntity airportEntity;

    public AirportDTO(AirportEntity airportEntity, Link selfLink) throws NoSuchElementException {
        this.airportEntity = airportEntity;
        add(selfLink);

    }

    public Integer getId() {
        return airportEntity.getId();
    }

    public String getName() {
        return airportEntity.getName();
    }
}
