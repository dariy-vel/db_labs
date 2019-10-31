package ua.lviv.iot.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.DAO.FlightDAO;
import ua.lviv.iot.model.FlightEntity;
import ua.lviv.iot.util.HibernateUtil;

import java.sql.SQLException;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            flights = (List<FlightEntity>) session.createQuery("FROM FlightEntity").list();
        }
        return flights;
    }

    @Override
    public FlightEntity findById(final Integer id) throws SQLException {
        FlightEntity flightEntity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            flightEntity = session.get(FlightEntity.class, id);
        }
        return flightEntity;
    }

    @Override
    public int create(final FlightEntity entity) throws SQLException {
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
    public int update(final FlightEntity entity) throws SQLException {
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
            FlightEntity entity;
            entity = (FlightEntity) session.load(FlightEntity.class, id);
            session.delete(entity);
            tx1.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
