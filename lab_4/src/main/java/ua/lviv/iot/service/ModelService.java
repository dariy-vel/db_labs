package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.ModelDaoImpl;
import ua.lviv.iot.model.ModelEntity;

import java.sql.SQLException;
import java.util.List;

public class ModelService {
    public List<ModelEntity> findAll() throws SQLException {
        return new ModelDaoImpl().findAll();
    }

    public ModelEntity findById(Integer id) throws SQLException {
        return new ModelDaoImpl().findById(id);
    }

    public int create(ModelEntity entity) throws SQLException {
        return new ModelDaoImpl().create(entity);
    }

    public int update(ModelEntity entity) throws SQLException {
        return new ModelDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new ModelDaoImpl().delete(id);
    }
}
