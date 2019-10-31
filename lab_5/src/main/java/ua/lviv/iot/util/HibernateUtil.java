package ua.lviv.iot.util;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import ua.lviv.iot.model.AirCompanyEntity;
import ua.lviv.iot.model.AircraftEntity;
import ua.lviv.iot.model.AirportEntity;
import ua.lviv.iot.model.CountryEntity;
import ua.lviv.iot.model.FlightEntity;
import ua.lviv.iot.model.ModelEntity;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(CountryEntity.class);
                configuration.addAnnotatedClass(AirportEntity.class);
                configuration.addAnnotatedClass(ModelEntity.class);
                configuration.addAnnotatedClass(AirCompanyEntity.class);
                configuration.addAnnotatedClass(FlightEntity.class);
                configuration.addAnnotatedClass(AircraftEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}