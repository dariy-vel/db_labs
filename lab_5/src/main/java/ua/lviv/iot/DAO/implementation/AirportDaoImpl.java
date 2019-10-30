package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.AirportDAO;
import ua.lviv.iot.model.AirportEntity;
import ua.lviv.iot.persitant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirportDaoImpl implements AirportDAO {
    private static final String FIND_ALL = "SELECT * FROM airport";
    private static final String DELETE = "DELETE FROM airport WHERE id=?";
    private static final String CREATE = "INSERT airport (id, name) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE airport SET name=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM airport WHERE id=?";

    @Override
    public List<AirportEntity> findAll() throws SQLException {
        List<AirportEntity> airports = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    airports.add((AirportEntity) new Transformer(AirportEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return airports;
    }

    @Override
    public AirportEntity findById(final Integer id) throws SQLException {
        AirportEntity airportEntity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    airportEntity = (AirportEntity) new Transformer(AirportEntity.class).fromResultSetToEntity(resultSet);
                }
            }
        }
        return airportEntity;
    }

    @Override
    public int create(final AirportEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(final AirportEntity entity) throws SQLException {
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
