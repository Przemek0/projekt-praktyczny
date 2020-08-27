package pl.sdacademy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration cfg = new Configuration();
            cfg.configure();
            cfg.getProperties().setProperty("connection.username", "root");
            cfg.getProperties().setProperty("connection.password", "asdf654321");
            sessionFactory = cfg.buildSessionFactory();
        }

        return sessionFactory;
    }
}
