package pl.sdacademy;

import org.hibernate.SessionFactory;
import pl.sdacademy.jsonClasses.ApiDataProvider;
import pl.sdacademy.jsonClasses.Summary;

public class Main {
    public static void main(String[] args) {
        //inicjalizacja bazy danych
        SessionFactoryProvider sessionFactoryProvider = new SessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        sessionFactory.close();

        Summary summary = ApiDataProvider.apiDataProvider();
        System.out.println(summary);
    }

}
