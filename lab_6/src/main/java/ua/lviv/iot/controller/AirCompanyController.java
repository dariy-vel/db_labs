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
import ua.lviv.iot.DTO.AirCompanyDTO;
import ua.lviv.iot.model.AirCompanyEntity;
import ua.lviv.iot.service.AirCompanyService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AirCompanyController {
    @Autowired
    AirCompanyService airCompanyService;

    @GetMapping(value = "/airCompany/{id}")
    public ResponseEntity<AirCompanyDTO> getAirCompany(@PathVariable Integer id) throws NoSuchElementException {
        AirCompanyEntity airCompany = airCompanyService.getAirCompany(id);
        Link link = linkTo(methodOn(AirCompanyController.class).getAirCompany(id)).withSelfRel();

        AirCompanyDTO airCompanyDTO = new AirCompanyDTO(airCompany, link);

        return new ResponseEntity<>(airCompanyDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/airCompany")
    public ResponseEntity<List<AirCompanyDTO>> getAllCountries() throws NoSuchElementException {
        List<AirCompanyEntity> airCompanyList = airCompanyService.getAllAirCompanies();
        Link link = linkTo(methodOn(AirCompanyController.class).getAllCountries()).withSelfRel();

        List<AirCompanyDTO> airCompanyDTOS = new ArrayList<>();
        for (AirCompanyEntity entity : airCompanyList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            AirCompanyDTO dto = new AirCompanyDTO(entity, selfLink);
            airCompanyDTOS.add(dto);
        }

        return new ResponseEntity<>(airCompanyDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/airCompany/{country_id}")
    public ResponseEntity<AirCompanyDTO> addAirCompany(@RequestBody AirCompanyEntity newAirCompany,
                                                       @PathVariable Integer country_id) throws NoSuchElementException {
        airCompanyService.createAirCompany(newAirCompany, country_id);
        Link link = linkTo(methodOn(AirCompanyController.class).getAirCompany(newAirCompany.getId())).withSelfRel();

        AirCompanyDTO airCompanyDTO = new AirCompanyDTO(newAirCompany, link);

        return new ResponseEntity<>(airCompanyDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/airCompany/{id}/{country_id}")
    public ResponseEntity<AirCompanyDTO> updateAirCompany(@RequestBody AirCompanyEntity uAirCompany,
                                                      @PathVariable Integer id, @PathVariable Integer country_id) throws NoSuchElementException {
        airCompanyService.updateAirCompany(uAirCompany, id, country_id);
        AirCompanyEntity airCompany = airCompanyService.getAirCompany(id);
        Link link = linkTo(methodOn(AirCompanyController.class).getAirCompany(id)).withSelfRel();

        AirCompanyDTO airCompanyDTO = new AirCompanyDTO(airCompany, link);

        return new ResponseEntity<>(airCompanyDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/airCompany/{id}")
    public ResponseEntity deleteAirCompany(@PathVariable Integer id) throws NoSuchElementException {
        airCompanyService.deleteAirCompany(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
