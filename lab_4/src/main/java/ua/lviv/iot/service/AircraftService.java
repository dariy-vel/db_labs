package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.AircraftDaoImpl;
import ua.lviv.iot.model.AircraftEntity;

import java.sql.SQLException;
import java.util.List;

public class AircraftService {
    public List<AircraftEntity> findAll() throws SQLException {
        return new AircraftDaoImpl().findAll();
    }

    public AircraftEntity findById(Integer id) throws SQLException {
        return new AircraftDaoImpl().findById(id);
    }

    public int create(AircraftEntity entity) throws SQLException {
        return new AircraftDaoImpl().create(entity);
    }

    public int update(AircraftEntity entity) throws SQLException {
        return new AircraftDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new AircraftDaoImpl().delete(id);
    }
}

