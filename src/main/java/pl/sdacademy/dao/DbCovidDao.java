package pl.sdacademy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sdacademy.SessionFactoryProvider;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DbCovidDao implements CovidDao {
    SessionFactory sessionFactory;

    public DbCovidDao() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    public static DbCovidDao getInstance() {
        return new DbCovidDao();
    }

    @Override
    public List<Country> getCountries() {
        Session session = sessionFactory.openSession();
        Query<Country> query = session.createQuery("SELECT c FROM Country c", Country.class);
        List<Country> countries = query.getResultList();
        session.close();
        return countries;
    }

    @Override
    public Set<StoreData> getDataByCountryAndDateRange(int id, LocalDate from, LocalDate to) {
        Session session = sessionFactory.openSession();
        Query<StoreData> query = session.createQuery(
                "SELECT sd " +
                        "FROM Country c " +
                        "JOIN c.storeData sd " +
                        "WHERE c.id = :id AND (sd.date >= :from AND sd.date <= :to)", StoreData.class
        );
        query.setParameter("id", id);
        query.setParameter("from", from);
        query.setParameter("to", to);
        Set<StoreData> storeDataSet = query.getResultStream().collect(Collectors.toSet());
        session.close();
        return storeDataSet;
    }

    @Override
    public StoreData getCurrentDataByCountry(int id) {
        Session session = sessionFactory.openSession();
        Query<StoreData> query = session.createQuery(
                "SELECT sd " +
                        "FROM Country c " +
                        "JOIN c.storeData sd " +
                        "WHERE c.id = :id AND sd.date = :today", StoreData.class
        );
        query.setParameter("id", id);
        LocalDate today = LocalDate.now();
        query.setParameter("today", today);
        StoreData storeData = query.getSingleResult();
        session.close();
        return storeData;
    }

//    @Override
//    public StoreData getCurrentWorldData() {
//        Session session = sessionFactory.openSession();
//        Query<StoreData> query = session.createQuery(
//                "SELECT " +
//                        "sd " +
//                        "FROM Country c " +
//                        "JOIN c.storeData sd " +
//                        "WHERE c.name = 'Global'", StoreData.class);
//        StoreData storeData = query.getSingleResult();
//        session.close();
//        return storeData;
//    }
    @Override
    public StoreData getCurrentWorldData() {
        Session session = sessionFactory.openSession();
        Query<StoreData> query = session.createQuery(
                "SELECT new StoreData(" +
                        "MAX(sd.date)," +
                        "CAST(SUM(sd.deaths) as integer ), " +
                        "CAST(SUM(sd.infections) as integer ), " +
                        "CAST(SUM(sd.recoveries) as integer ), " +
                        "CAST(SUM(sd.activeCases) as integer ) " +
                        ")" +
                        "FROM Country c " +
                        "JOIN c.storeData sd " +
                        "WHERE sd.date = (SELECT MAX(sd2.date) FROM Country c2 JOIN c2.storeData sd2)", StoreData.class);
//        LocalDateTime from = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0,0,0));
//        LocalDateTime to = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59,0));
//        query.setParameter("from", from);
//        query.setParameter("to", to);
        StoreData storeData = query.getSingleResult();
        session.close();
        return storeData;
    }

    @Override
    public void storeData(List<Country> countryList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        getCountries().forEach(session::delete);
        countryList.forEach(session::persist);
        transaction.commit();
        session.close();
    }
}
