package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.ModelDaoImpl;
import ua.lviv.iot.model.ModelEntity;

import java.sql.SQLException;
import java.util.List;

public class ModelService {
    public List<ModelEntity> findAll() throws SQLException {
        return new ModelDaoImpl().findAll();
    }

    public ModelEntity findById(final Integer id) throws SQLException {
        return new ModelDaoImpl().findById(id);
    }

    public int create(final ModelEntity entity) throws SQLException {
        return new ModelDaoImpl().create(entity);
    }

    public int update(final ModelEntity entity) throws SQLException {
        return new ModelDaoImpl().update(entity);
    }

    public int delete(final Integer id) throws SQLException {
        return new ModelDaoImpl().delete(id);
    }
}
