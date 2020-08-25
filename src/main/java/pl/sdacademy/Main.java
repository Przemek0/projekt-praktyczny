package pl.sdacademy;

import org.hibernate.SessionFactory;
import pl.sdacademy.credentials.User;
import pl.sdacademy.jsonClasses.GetSummary;
import pl.sdacademy.jsonClasses.Summary;

public class Main {
    public static void main(String[] args) {
        //inicjalizacja bazy danych
        SessionFactoryProvider sessionFactoryProvider = new SessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        sessionFactory.close();

        GetSummary getSummary = new GetSummary();
        Summary summary = getSummary.getSummary();
        System.out.println(summary);
    }

}
