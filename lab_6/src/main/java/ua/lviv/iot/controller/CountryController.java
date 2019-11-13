package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.DTO.CountryDTO;
import ua.lviv.iot.model.CountryEntity;
import ua.lviv.iot.service.CountryService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping(value = "/country/{id}")
    public ResponseEntity<CountryDTO> getCountry(@PathVariable Integer id) throws NoSuchElementException {
        CountryEntity country = countryService.getCountry(id);
        Link link = linkTo(methodOn(CountryController.class).getCountry(id)).withSelfRel();

        CountryDTO countryDTO = new CountryDTO(country, link);

        return new ResponseEntity<>(countryDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/country/airCompany/{id}")
    public ResponseEntity<CountryDTO> getCountryByAirCompanyId(@PathVariable Integer id) throws NoSuchElementException {
        CountryEntity country = countryService.getCountryByAirCompanyId(id);
        Link link = linkTo(methodOn(CountryController.class).getCountry(id)).withSelfRel();

        CountryDTO countryDTO = new CountryDTO(country, link);

        return new ResponseEntity(countryDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/country")
    public ResponseEntity<List<CountryDTO>> getAllCountries() throws NoSuchElementException {
        List<CountryEntity> countryList = countryService.getAllCountries();
        Link link = linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel();

        List<CountryDTO> countryDTOS = new ArrayList<>();
        for (CountryEntity entity : countryList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CountryDTO dto = new CountryDTO(entity, selfLink);
            countryDTOS.add(dto);
        }

        return new ResponseEntity<>(countryDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/country")
    public ResponseEntity<CountryDTO> addCountry(@RequestBody CountryEntity newCountry) throws NoSuchElementException {
        countryService.createCountry(newCountry);
        Link link = linkTo(methodOn(CountryController.class).getCountry(newCountry.getId())).withSelfRel();

        CountryDTO countryDTO = new CountryDTO(newCountry, link);

        return new ResponseEntity<>(countryDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/country/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryEntity uCountry, @PathVariable Integer id) throws NoSuchElementException {
        countryService.updateCountry(uCountry, id);
        CountryEntity country = countryService.getCountry(id);
        Link link = linkTo(methodOn(CountryController.class).getCountry(id)).withSelfRel();

        CountryDTO countryDTO = new CountryDTO(country, link);

        return new ResponseEntity<>(countryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/country/{id}")
    public ResponseEntity deleteCountry(@PathVariable Integer id) throws NoSuchElementException {
        countryService.deleteCountry(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
