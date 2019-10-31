package ua.lviv.iot.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.DAO.CountryDAO;
import ua.lviv.iot.model.CountryEntity;
import ua.lviv.iot.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CountryDaoImpl implements CountryDAO {
    @Override
    public List<CountryEntity> findAll() throws SQLException {
        List<CountryEntity> countries = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            countries = (List<CountryEntity>) session.createQuery("FROM CountryEntity").list();
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
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(final CountryEntity entity) throws SQLException {
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
            CountryEntity entity;
            entity = (CountryEntity) session.load(CountryEntity.class, id);
            session.delete(entity);
            tx1.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
