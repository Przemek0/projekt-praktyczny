package pl.sdacademy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory () {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        return sessionFactory;
    }
}