package ua.lviv.iot.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.DAO.CountryDAO;
import ua.lviv.iot.model.CountryEntity;
import ua.lviv.iot.persitant.ConnectionManager;
import ua.lviv.iot.transformer.Transformer;
import ua.lviv.iot.util.HibernateUtil;

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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            countries = (List<CountryEntity>) session.createQuery("FROM Country").list();
        }
        return countries;
    }

    @Override
    public CountryEntity findById(final Integer id) throws SQLException {
        CountryEntity entity = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            entity = session.get(CountryEntity.class, id);
        }
        return entity;
    }

    @Override
    public int create(final CountryEntity entity) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
        return 1;
    }

    @Override
    public int update(final CountryEntity entity) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
        return 1;
    }

    @Override
    public int delete(final Integer id) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        }
        return 1;
    }
}
