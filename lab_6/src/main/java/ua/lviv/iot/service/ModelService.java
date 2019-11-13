package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.model.ModelEntity;
import ua.lviv.iot.repository.ModelRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ModelService {
    @Autowired
    ModelRepository modelRepository;

    public ModelEntity getModel(Integer id) throws NoSuchElementException {
        ModelEntity modelEntity = modelRepository.findById(id).get();
        if (modelEntity == null) throw new NoSuchElementException();
        return modelEntity;
    }

    public List<ModelEntity> getAllModels() {
        return modelRepository.findAll();
    }

    @Transactional
    public void createModel(ModelEntity modelEntity) {
        modelRepository.save(modelEntity);
    }

    @Transactional
    public void updateModel(ModelEntity uModelEntity, Integer id) throws NoSuchElementException {
        ModelEntity modelEntity = modelRepository.findById(id).get();
        if (modelEntity == null) throw new NoSuchElementException();

        modelEntity.setName(uModelEntity.getName());
    }

    @Transactional
    public void deleteModel(Integer id) throws NoSuchElementException {
        ModelEntity modelEntity = modelRepository.findById(id).get();

        if (modelEntity == null) throw new NoSuchElementException();
        modelRepository.delete(modelEntity);
    }
}

