package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ua.lviv.iot.model.ModelEntity;

import java.util.NoSuchElementException;

public class ModelDTO extends RepresentationModel {
    ModelEntity modelEntity;

    public ModelDTO(ModelEntity modelEntity, Link selfLink) throws NoSuchElementException {
        this.modelEntity = modelEntity;
        add(selfLink);

    }

    public Integer getId() {
        return modelEntity.getId();
    }

    public String getName() {
        return modelEntity.getName();
    }

    public String getManufacturer() {
        return modelEntity.getManufacturer();
    }
}
