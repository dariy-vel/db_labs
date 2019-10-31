package ua.lviv.iot.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.DAO.AirportDAO;
import ua.lviv.iot.model.AirportEntity;
import ua.lviv.iot.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDaoImpl implements AirportDAO {
    @Override
    public List<AirportEntity> findAll() throws SQLException {
        List<AirportEntity> airports = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            airports = (List<AirportEntity>) session.createQuery("FROM AirportEntity").list();
        }
        return airports;
    }

    @Override
    public AirportEntity findById(final Integer id) throws SQLException {
        AirportEntity airportEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            airportEntity = session.get(AirportEntity.class, id);
        }
        return airportEntity;
    }

    @Override
    public int create(final AirportEntity entity) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(final AirportEntity entity) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(final Integer id) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            AirportEntity entity;
            entity = (AirportEntity) session.load(AirportEntity.class, id);
            session.delete(entity);
            tx1.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
