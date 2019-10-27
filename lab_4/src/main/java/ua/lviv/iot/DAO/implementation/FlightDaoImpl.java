package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.FlightDAO;
import ua.lviv.iot.model.FlightEntity;
import ua.lviv.iot.persitant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements FlightDAO {
    private static final String FIND_ALL = "SELECT * FROM flight";
    private static final String DELETE = "DELETE FROM flight WHERE id=?";
    private static final String CREATE = "INSERT flight (id, aircraft_id, from_airport_id, to_airport_id, "
            + "scheduled_departure_time, scheduled_arrival_time, actual_departure_time, est_arrival_time) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE flight SET id=?, aircraft_id=?, from_airport_id=?, to_airport_id=?,"
            + "scheduled_departure_time=?, scheduled_arrival_time=?, actual_departure_time=?, est_arrival_time=? "
            + "WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM flight WHERE id=?";

    @Override
    public List<FlightEntity> findAll() throws SQLException {
        List<FlightEntity> flights = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    flights.add((FlightEntity) new Transformer(FlightEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return flights;
    }

    @Override
    public FlightEntity findById(final Integer id) throws SQLException {
        FlightEntity flightEntity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    flightEntity =
                            (FlightEntity) new Transformer(FlightEntity.class).fromResultSetToEntity(resultSet);
                    break;
                }
            }
        }
        return flightEntity;
    }

    @Override
    public int create(final FlightEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getAircraft_id());
            ps.setInt(3, entity.getFromAirportId());
            ps.setInt(4, entity.getToAirportId());
            ps.setTimestamp(5, entity.getScheduledDepartureTime());
            ps.setTimestamp(6, entity.getScheduledArrivalTime());
            ps.setTimestamp(7, entity.getActualDepartureTime());
            ps.setTimestamp(8, entity.getEstArrivalTime());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(final FlightEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setInt(1, entity.getAircraft_id());
            ps.setInt(2, entity.getFromAirportId());
            ps.setInt(3, entity.getToAirportId());
            ps.setTimestamp(4, entity.getScheduledDepartureTime());
            ps.setTimestamp(5, entity.getScheduledArrivalTime());
            ps.setTimestamp(6, entity.getActualDepartureTime());
            ps.setTimestamp(7, entity.getEstArrivalTime());
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
