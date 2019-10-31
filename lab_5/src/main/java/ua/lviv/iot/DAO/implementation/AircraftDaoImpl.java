package ua.lviv.iot.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.DAO.AircraftDAO;
import ua.lviv.iot.model.AircraftEntity;
import ua.lviv.iot.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AircraftDaoImpl implements AircraftDAO {
    @Override
    public List<AircraftEntity> findAll() throws SQLException {
        List<AircraftEntity> aircrafts = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            aircrafts = (List<AircraftEntity>) session.createQuery("FROM AircraftEntity").list();
        }
        return aircrafts;
    }

    @Override
    public AircraftEntity findById(final Integer id) throws SQLException {
        AircraftEntity aircraftEntity = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            aircraftEntity = session.get(AircraftEntity.class, id);
        }
        return aircraftEntity;
    }

    @Override
    public int create(final AircraftEntity entity) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
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
    public int update(final AircraftEntity entity) throws SQLException {
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
            AircraftEntity entity;
            entity = (AircraftEntity) session.load(AircraftEntity.class, id);
            session.delete(entity);
            tx1.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
