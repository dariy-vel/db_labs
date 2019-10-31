package ua.lviv.iot.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lviv.iot.DAO.ModelDAO;
import ua.lviv.iot.model.ModelEntity;
import ua.lviv.iot.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelDaoImpl implements ModelDAO {
    @Override
    public List<ModelEntity> findAll() throws SQLException {
        List<ModelEntity> models = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            models = (List<ModelEntity>) session.createQuery("FROM ModelEntity").list();
        }
        return models;
    }

    @Override
    public ModelEntity findById(final Integer id) throws SQLException {
        ModelEntity modelEntity = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            modelEntity = session.get(ModelEntity.class, id);
        }
        return modelEntity;
    }

    @Override
    public int create(final ModelEntity entity) throws SQLException {
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
    public int update(final ModelEntity entity) throws SQLException {
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
            ModelEntity entity;
            entity = (ModelEntity) session.load(ModelEntity.class, id);
            session.delete(entity);
            tx1.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}

