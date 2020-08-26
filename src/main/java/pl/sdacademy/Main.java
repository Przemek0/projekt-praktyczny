package pl.sdacademy;

import org.hibernate.SessionFactory;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;
import pl.sdacademy.jsonClasses.ApiDataProvider;
import pl.sdacademy.jsonClasses.ApiObjectToEntityMapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //inicjalizacja bazy danych
        SessionFactoryProvider sessionFactoryProvider = new SessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        sessionFactory.close();

        List<Country> co = ApiObjectToEntityMapper.map(ApiDataProvider.apiDataProvider());
        co.forEach(System.out::println);
    }

}
