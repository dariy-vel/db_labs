package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.AirCompanyDAO;
import ua.lviv.iot.model.AirCompanyEntity;
import ua.lviv.iot.persitant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirCompanyDaoImpl implements AirCompanyDAO {
    private static final String FIND_ALL = "SELECT * FROM air_company";
    private static final String DELETE = "DELETE FROM air_company WHERE id=?";
    private static final String CREATE = "INSERT air_company (id, name, country_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE air_company SET name=?, country_id=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM air_company WHERE id=?";

    @Override
    public List<AirCompanyEntity> findAll() throws SQLException {
        List<AirCompanyEntity> airCompanies = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    airCompanies.add((AirCompanyEntity) new Transformer(AirCompanyEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return airCompanies;
    }

    @Override
    public AirCompanyEntity findById(final Integer id) throws SQLException {
        AirCompanyEntity airCompanyEntity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    airCompanyEntity = (AirCompanyEntity) new Transformer(AirCompanyEntity.class).fromResultSetToEntity(resultSet);
                    break;
                }
            }
        }
        return airCompanyEntity;
    }

    @Override
    public int create(final AirCompanyEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setInt(3, entity.getCountryId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(final AirCompanyEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCountryId());
            ps.setInt(3, entity.getId());
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
