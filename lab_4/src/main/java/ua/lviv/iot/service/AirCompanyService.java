package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.AirCompanyDaoImpl;
import ua.lviv.iot.model.AirCompanyEntity;

import java.sql.SQLException;
import java.util.List;

public class AirCompanyService {
    public List<AirCompanyEntity> findAll() throws SQLException {
        return new AirCompanyDaoImpl().findAll();
    }

    public AirCompanyEntity findById(final Integer id) throws SQLException {
        return new AirCompanyDaoImpl().findById(id);
    }

    public int create(final AirCompanyEntity entity) throws SQLException {
        return new AirCompanyDaoImpl().create(entity);
    }

    public int update(final AirCompanyEntity entity) throws SQLException {
        return new AirCompanyDaoImpl().update(entity);
    }

    public int delete(final Integer id) throws SQLException {
        return new AirCompanyDaoImpl().delete(id);
    }
}
