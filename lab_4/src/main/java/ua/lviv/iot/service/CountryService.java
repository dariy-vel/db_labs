package ua.lviv.iot.service;

import ua.lviv.iot.DAO.implementation.CountryDaoImpl;
import ua.lviv.iot.model.CountryEntity;

import java.sql.SQLException;
import java.util.List;

public class CountryService {
    public List<CountryEntity> findAll() throws SQLException {
        return new CountryDaoImpl().findAll();
    }

    public CountryEntity findById(Integer id) throws SQLException {
        return new CountryDaoImpl().findById(id);
    }

    public int create(CountryEntity entity) throws SQLException {
        return new CountryDaoImpl().create(entity);
    }

    public int update(CountryEntity entity) throws SQLException {
        return new CountryDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new CountryDaoImpl().delete(id);
    }
}
