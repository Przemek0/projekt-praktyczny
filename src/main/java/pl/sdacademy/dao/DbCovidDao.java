package pl.sdacademy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.sdacademy.SessionFactoryProvider;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DbCovidDao implements CovidDao {
    SessionFactory sessionFactory;

    public DbCovidDao() {
        sessionFactory = new SessionFactoryProvider().getSessionFactory();
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

    @Override
    public StoreData getCurrentWorldData() {
        Session session = sessionFactory.openSession();
        Query<StoreData> query = session.createQuery(
                "SELECT " +
                        "SUM(sd.activeCases), " +
                        "SUM(sd.infections), " +
                        "SUM(sd.recoveries), " +
                        "SUM(sd.deaths), " +
                        "SUM(sd.totalDeaths) " +
                        "FROM Country c " +
                        "JOIN c.storeData sd " +
                        "WHERE sd.date = :today", StoreData.class);
        LocalDate today = LocalDate.now();
        query.setParameter("today", today);
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
