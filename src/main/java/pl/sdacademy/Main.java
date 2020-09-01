package pl.sdacademy;

import org.hibernate.SessionFactory;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.dao.DbCovidDao;
import pl.sdacademy.jsonClasses.ApiEntityDataProvider;

// new link: https://api.covid19api.com/total/dayone/country/united-states
public class Main {
    public static void main(String[] args) {
        //inicjalizacja bazy danych
        SessionFactoryProvider sessionFactoryProvider = new SessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        sessionFactory.close();

        ApiEntityDataProvider apiEntityDataProvider = new ApiEntityDataProvider();
        CovidDao covidDao = new DbCovidDao();
        covidDao.storeData(apiEntityDataProvider.load());
        //List<Country> co = apiEntityDataProvider.load();
        //co.forEach(System.out::println);
    }

}
