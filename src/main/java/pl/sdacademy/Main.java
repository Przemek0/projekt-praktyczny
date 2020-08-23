package pl.sdacademy;

import pl.sdacademy.jsonClasses.GetSummary;
import pl.sdacademy.jsonClasses.Summary;

public class Main {
    public static void main(String[] args) {
        //inicjalizacja bazy danych
//        SessionFactoryProvider sessionFactoryProvider = new SessionFactoryProvider();
//        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
//        sessionFactory.close();

        Summary summary = GetSummary.getSummary();
        System.out.println(summary);

    }
}
