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
import ua.lviv.iot.DTO.ModelDTO;
import ua.lviv.iot.model.ModelEntity;
import ua.lviv.iot.service.ModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ModelController {
    @Autowired
    ModelService modelService;

    @GetMapping(value = "/model/{id}")
    public ResponseEntity<ModelDTO> getModel(@PathVariable Integer id) throws NoSuchElementException {
        ModelEntity model = modelService.getModel(id);
        Link link = linkTo(methodOn(ModelController.class).getModel(id)).withSelfRel();

        ModelDTO modelDTO = new ModelDTO(model, link);

        return new ResponseEntity<>(modelDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/model")
    public ResponseEntity<List<ModelDTO>> getAllCountries() throws NoSuchElementException {
        List<ModelEntity> modelList = modelService.getAllModels();
        Link link = linkTo(methodOn(ModelController.class).getAllCountries()).withSelfRel();

        List<ModelDTO> modelDTOS = new ArrayList<>();
        for (ModelEntity entity : modelList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ModelDTO dto = new ModelDTO(entity, selfLink);
            modelDTOS.add(dto);
        }

        return new ResponseEntity<>(modelDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/model")
    public ResponseEntity<ModelDTO> addModel(@RequestBody ModelEntity newModel) throws NoSuchElementException {
        modelService.createModel(newModel);
        Link link = linkTo(methodOn(ModelController.class).getModel(newModel.getId())).withSelfRel();

        ModelDTO modelDTO = new ModelDTO(newModel, link);

        return new ResponseEntity<>(modelDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/model/{id}")
    public ResponseEntity<ModelDTO> updateModel(@RequestBody ModelEntity uModel,
                                                  @PathVariable Integer id) throws NoSuchElementException {
        modelService.updateModel(uModel, id);
        ModelEntity model = modelService.getModel(id);
        Link link = linkTo(methodOn(ModelController.class).getModel(id)).withSelfRel();

        ModelDTO modelDTO = new ModelDTO(model, link);

        return new ResponseEntity<>(modelDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/model/{id}")
    public ResponseEntity deleteModel(@PathVariable Integer id) throws NoSuchElementException {
        modelService.deleteModel(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
