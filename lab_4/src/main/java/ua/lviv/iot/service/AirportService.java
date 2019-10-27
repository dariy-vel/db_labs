package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.AirportDaoImpl;
import ua.lviv.iot.model.AirportEntity;

import java.sql.SQLException;
import java.util.List;

public class AirportService {
    public List<AirportEntity> findAll() throws SQLException {
        return new AirportDaoImpl().findAll();
    }

    public AirportEntity findById(final Integer id) throws SQLException {
        return new AirportDaoImpl().findById(id);
    }

    public int create(final AirportEntity entity) throws SQLException {
        return new AirportDaoImpl().create(entity);
    }

    public int update(final AirportEntity entity) throws SQLException {
        return new AirportDaoImpl().update(entity);
    }

    public int delete(final Integer id) throws SQLException {
        return new AirportDaoImpl().delete(id);
    }
}
