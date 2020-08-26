package pl.sdacademy.credentials;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUserRepository implements UserRepository{
    private final SessionFactory sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();

    @Override
    public User readById(int id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> readAll() {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("SELECT u FROM User u", User.class);
        List<User> resultList = query.getResultList();
        session.close();
        sessionFactory.close();
        return resultList;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
