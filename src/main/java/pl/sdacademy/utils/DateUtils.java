package pl.sdacademy.utils;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateUtils {
    //Dodaj do klasy DateUtils metodę, która przyjmie dwa parametry - dwie daty (obiekty typu LocalDate), a która zwróci liczbę dni dzielących te daty.
    public int numberOfDaysBetween(LocalDate fromDate, LocalDate toDate){
        int noOfDaysBetween = (int) DAYS.between(fromDate, toDate);
        return noOfDaysBetween;
    }
}
