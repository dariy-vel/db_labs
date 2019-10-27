package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.CountryDAO;
import ua.lviv.iot.model.CountryEntity;
import ua.lviv.iot.persitant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDAO {
    private static final String FIND_ALL = "SELECT * FROM country";
    private static final String DELETE = "DELETE FROM country WHERE id=?";
    private static final String CREATE = "INSERT country (id, name) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE country SET name=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM country WHERE id=?";

    @Override
    public List<CountryEntity> findAll() throws SQLException {
        List<CountryEntity> countries = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    countries.add((CountryEntity) new Transformer(CountryEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return countries;
    }

    @Override
    public CountryEntity findById(Integer id) throws SQLException {
        CountryEntity entity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity = (CountryEntity) new Transformer(CountryEntity.class).fromResultSetToEntity(resultSet);
                    break;
                }
            }
        }
        return entity;
    }

    @Override
    public int create(final CountryEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(final CountryEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(final Integer id) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}
