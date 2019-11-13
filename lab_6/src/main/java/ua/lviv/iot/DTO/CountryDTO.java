package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ua.lviv.iot.model.CountryEntity;

import java.util.NoSuchElementException;

public class CountryDTO extends RepresentationModel {
    CountryEntity country;

    public CountryDTO(CountryEntity country, Link selfLink) throws NoSuchElementException {
        this.country = country;
        add(selfLink);

    }

    public Integer getCountryId() {
        return country.getId();
    }

    public String getCountryName() {
        return country.getName();
    }
}
