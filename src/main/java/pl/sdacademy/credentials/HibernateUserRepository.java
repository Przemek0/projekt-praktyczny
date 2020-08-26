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
    public User readById(int id) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.id = id", User.class);
        User singleResult = query.getSingleResult();
        session.close();
        sessionFactory.close();
        return singleResult;
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
    public void create() {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
