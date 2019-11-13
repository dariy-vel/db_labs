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
import ua.lviv.iot.DTO.AirportDTO;
import ua.lviv.iot.model.AirportEntity;
import ua.lviv.iot.service.AirportService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AirportController {
    @Autowired
    AirportService airportService;

    @GetMapping(value = "/airport/{id}")
    public ResponseEntity<AirportDTO> getAirport(@PathVariable Integer id) throws NoSuchElementException {
        AirportEntity airport = airportService.getAirport(id);
        Link link = linkTo(methodOn(AirportController.class).getAirport(id)).withSelfRel();

        AirportDTO airportDTO = new AirportDTO(airport, link);

        return new ResponseEntity<>(airportDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/airport")
    public ResponseEntity<List<AirportDTO>> getAllCountries() throws NoSuchElementException {
        List<AirportEntity> airportList = airportService.getAllAirports();
        Link link = linkTo(methodOn(AirportController.class).getAllCountries()).withSelfRel();

        List<AirportDTO> airportDTOS = new ArrayList<>();
        for (AirportEntity entity : airportList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            AirportDTO dto = new AirportDTO(entity, selfLink);
            airportDTOS.add(dto);
        }

        return new ResponseEntity<>(airportDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/airport")
    public ResponseEntity<AirportDTO> addAirport(@RequestBody AirportEntity newAirport) throws NoSuchElementException {
        airportService.createAirport(newAirport);
        Link link = linkTo(methodOn(AirportController.class).getAirport(newAirport.getId())).withSelfRel();

        AirportDTO airportDTO = new AirportDTO(newAirport, link);

        return new ResponseEntity<>(airportDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/airport/{id}")
    public ResponseEntity<AirportDTO> updateAirport(@RequestBody AirportEntity uAirport,
                                                  @PathVariable Integer id) throws NoSuchElementException {
        airportService.updateAirport(uAirport, id);
        AirportEntity airport = airportService.getAirport(id);
        Link link = linkTo(methodOn(AirportController.class).getAirport(id)).withSelfRel();

        AirportDTO airportDTO = new AirportDTO(airport, link);

        return new ResponseEntity<>(airportDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/airport/{id}")
    public ResponseEntity deleteAirport(@PathVariable Integer id) throws NoSuchElementException {
        airportService.deleteAirport(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
