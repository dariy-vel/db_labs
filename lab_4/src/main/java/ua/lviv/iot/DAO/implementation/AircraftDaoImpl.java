package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.AircraftDAO;
import ua.lviv.iot.model.AircraftEntity;
import ua.lviv.iot.persitant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AircraftDaoImpl implements AircraftDAO {
    private static final String FIND_ALL = "SELECT * FROM aircraft";
    private static final String DELETE = "DELETE FROM aircraft WHERE id=?";
    private static final String CREATE = "INSERT aircraft (id, board_number, registration_number, "
            + "registration_country_id, model_id, air_company_id, longitude, latitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE aircraft SET board_number=?, registration_number=?, "
            + "registration_country_id=?, model_id=?, air_company_id=?, longitude=?, latitude=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM aircraft WHERE id=?";

    @Override
    public List<AircraftEntity> findAll() throws SQLException {
        List<AircraftEntity> aircrafts = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    aircrafts.add((AircraftEntity) new Transformer(AircraftEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return aircrafts;
    }

    @Override
    public AircraftEntity findById(final Integer id) throws SQLException {
        AircraftEntity aircraftEntity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    aircraftEntity =
                            (AircraftEntity) new Transformer(AircraftEntity.class).fromResultSetToEntity(resultSet);
                    break;
                }
            }
        }
        return aircraftEntity;
    }

    @Override
    public int create(final AircraftEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getBoardNumber());
            ps.setString(3, entity.getRegistrationNumber());
            ps.setInt(4, entity.getRegistrationCountryId());
            ps.setInt(5, entity.getModelId());
            ps.setInt(6, entity.getAirCompanyId());
            ps.setDouble(7, entity.getLongitude());
            ps.setDouble(8, entity.getLatitude());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(final AircraftEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, entity.getBoardNumber());
            ps.setString(2, entity.getRegistrationNumber());
            ps.setInt(3, entity.getRegistrationCountryId());
            ps.setInt(4, entity.getModelId());
            ps.setInt(5, entity.getAirCompanyId());
            ps.setDouble(6, entity.getLongitude());
            ps.setDouble(7, entity.getLatitude());
            ps.setInt(8, entity.getId());
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
