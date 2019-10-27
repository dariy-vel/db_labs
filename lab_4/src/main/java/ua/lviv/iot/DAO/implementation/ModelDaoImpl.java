package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.ModelDAO;
import ua.lviv.iot.model.ModelEntity;
import ua.lviv.iot.persitant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelDaoImpl implements ModelDAO {
    private static final String FIND_ALL = "SELECT * FROM model";
    private static final String DELETE = "DELETE FROM model WHERE id=?";
    private static final String CREATE = "INSERT model (id, name, manufacturer) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE model SET name=?, manufacturer=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM model WHERE id=?";

    @Override
    public List<ModelEntity> findAll() throws SQLException {
        List<ModelEntity> models = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    models.add((ModelEntity) new Transformer(ModelEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return models;
    }

    @Override
    public ModelEntity findById(final Integer id) throws SQLException {
        ModelEntity modelEntity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    modelEntity = (ModelEntity) new Transformer(ModelEntity.class).fromResultSetToEntity(resultSet);
                }
            }
        }
        return modelEntity;
    }

    @Override
    public int create(final ModelEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getManufacturer());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(final ModelEntity entity) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getManufacturer());
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

