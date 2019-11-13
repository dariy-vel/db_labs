package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ua.lviv.iot.controller.CountryController;
import ua.lviv.iot.model.AirCompanyEntity;

import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AirCompanyDTO extends RepresentationModel {
    AirCompanyEntity airCompanyEntity;

    public AirCompanyDTO(AirCompanyEntity airCompanyEntity, Link selfLink) throws NoSuchElementException {
        this.airCompanyEntity = airCompanyEntity;
        add(selfLink);
        add(linkTo(methodOn(CountryController.class).getCountryByAirCompanyId(airCompanyEntity.getId())).withRel(
                "country"));
    }

    public Integer getAirCompanyId(){
        return airCompanyEntity.getId();
    }

    public String getCountryName() {
        return airCompanyEntity.getName();
    }
    public Integer getCountryId() {
        return airCompanyEntity.getCountryEntity().getId();
    }
}
