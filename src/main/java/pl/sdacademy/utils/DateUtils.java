package pl.sdacademy.utils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.TreeSet;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateUtils {


    //Dodaj do klasy DateUtils metodę, która przyjmie dwa parametry
    //- dwie daty (obiekty typu LocalDate), a która zwróci liczbę dni dzielących te daty.
    public static int numberOfDaysBetween(LocalDate fromDate, LocalDate toDate) {
        int noOfDaysBetween = (int) DAYS.between(fromDate, toDate);
        return noOfDaysBetween;
    }

    //D3. Dodaj do klasy DateUtils metodę, która przyjmie parametr
    // - zbiór dat, a która zwróci odpowiedź, czy między datami ze zbioru jest jakakolwiek luka.
    public static boolean isContinuity(HashSet<LocalDate> set) {
        TreeSet<LocalDate> treeSet = new TreeSet<>();
      for(LocalDate c : set){
          treeSet.add(c);
      }
        LocalDate first = treeSet.first();
        LocalDate last = treeSet.last();
        //System.out.println("size " + set.size());
        int daysBetweenFirstAndLast = (int) DAYS.between(first, last);
        //System.out.println("days between " + (daysBetweenFirstAndLast + 1));
        if (daysBetweenFirstAndLast + 1 == set.size())
            return true;
        else return false;
    }

    //Dodaj do klasy DateUtils metodę, która przyjmie parametr
    // - listę dat, a która zwróci liczbę dni, między dwoma najodleglejszymi datami.
    public static int noOfDaysBetweenMinAndMax(TreeSet<LocalDate> set){
        LocalDate first = set.first();
        LocalDate last = set.last();
        int daysBetweenMinAndMax = (int) DAYS.between(first, last);
        return daysBetweenMinAndMax;
    }
}
