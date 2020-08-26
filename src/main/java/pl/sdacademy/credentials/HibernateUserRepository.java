package pl.sdacademy.credentials;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUserRepository implements UserRepository{
    private SessionFactory sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();

    @Override
    public void readById(int id) {
    }

    @Override
    public List<User> readAll() {
        Session session = sessionFactory.openSession();
        Query<User> readAllUsers = session.createQuery("SELECT u FROM User u", User.class);
        return readAllUsers.getResultList();
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
