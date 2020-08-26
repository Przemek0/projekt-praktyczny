package pl.sdacademy.utils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateUtils {

    //Dodaj do klasy DateUtils metodę, która przyjmie dwa parametry
    //- dwie daty (obiekty typu LocalDate), a która zwróci liczbę dni dzielących te daty.
    public static int numberOfDaysBetween(LocalDate fromDate, LocalDate toDate) {
        return ((int) DAYS.between(fromDate, toDate));
    }

    //D3. Dodaj do klasy DateUtils metodę, która przyjmie parametr
    // - zbiór dat, a która zwróci odpowiedź, czy między datami ze zbioru jest jakakolwiek luka.
    public static boolean isContinuity(HashSet<LocalDate> set) {
        TreeSet<LocalDate> treeSet = new TreeSet<>(set);
        if (set.size() <= 1) return false;
        LocalDate first = treeSet.first();
        LocalDate last = treeSet.last();
        //System.out.println("size " + set.size());
        int daysBetweenFirstAndLast = (int) DAYS.between(first, last);
        //System.out.println("days between " + (daysBetweenFirstAndLast + 1));
        return daysBetweenFirstAndLast != 0;
    }

    //D4. Dodaj do klasy DateUtils metodę, która przyjmie parametr
    // - listę dat, a która zwróci liczbę dni, między dwoma najodleglejszymi datami.
    public static int noOfDaysBetweenMinAndMax(List<LocalDate> list) {
        if (list.size() <= 1) return 0;
        list.sort(Comparator.naturalOrder());
        LocalDate first = list.get(0);
        LocalDate last = list.get(list.size() - 1);
        return ((int) DAYS.between(first, last));
    }
}
