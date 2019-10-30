package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.CountryDaoImpl;
import ua.lviv.iot.model.CountryEntity;

import java.sql.SQLException;
import java.util.List;

public class CountryService {
    public List<CountryEntity> findAll() throws SQLException {
        return new CountryDaoImpl().findAll();
    }

    public CountryEntity findById(final Integer id) throws SQLException {
        return new CountryDaoImpl().findById(id);
    }

    public int create(final CountryEntity entity) throws SQLException {
        return new CountryDaoImpl().create(entity);
    }

    public int update(final CountryEntity entity) throws SQLException {
        return new CountryDaoImpl().update(entity);
    }

    public int delete(final Integer id) throws SQLException {
        return new CountryDaoImpl().delete(id);
    }
}
