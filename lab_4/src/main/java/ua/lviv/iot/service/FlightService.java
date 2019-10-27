package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.FlightDaoImpl;
import ua.lviv.iot.model.FlightEntity;

import java.sql.SQLException;
import java.util.List;

public class FlightService {
    public List<FlightEntity> findAll() throws SQLException {
        return new FlightDaoImpl().findAll();
    }

    public FlightEntity findById(Integer id) throws SQLException {
        return new FlightDaoImpl().findById(id);
    }

    public int create(FlightEntity entity) throws SQLException {
        return new FlightDaoImpl().create(entity);
    }

    public int update(FlightEntity entity) throws SQLException {
        return new FlightDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new FlightDaoImpl().delete(id);
    }
}
