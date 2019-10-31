package ua.lviv.iot.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.DAO.AirCompanyDAO;
import ua.lviv.iot.model.AirCompanyEntity;
import ua.lviv.iot.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirCompanyDaoImpl implements AirCompanyDAO {
    @Override
    public List<AirCompanyEntity> findAll() throws SQLException {
        List<AirCompanyEntity> airCompanies = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            airCompanies = (List<AirCompanyEntity>) session.createQuery("FROM AirCompanyEntity").list();
        }
        return airCompanies;
    }

    @Override
    public AirCompanyEntity findById(final Integer id) throws SQLException {
        AirCompanyEntity airCompanyEntity = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            airCompanyEntity = session.get(AirCompanyEntity.class, id);
        }
        return airCompanyEntity;
    }

    @Override
    public int create(final AirCompanyEntity entity) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
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
    public int update(final AirCompanyEntity entity) throws SQLException {
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
    public int delete(final Integer id) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            AirCompanyEntity entity;
            entity = (AirCompanyEntity) session.load(AirCompanyEntity.class, id);
            session.delete(entity);
            tx1.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
