package pl.sdacademy.jsonClassEntity.covid19.countries;

import pl.sdacademy.jsonClassEntity.Covid19;
import pl.sdacademy.jsonClassEntity.covid19.countryDayOne.CountryDayOnes;

public class Main {
    public static void main(String[] args) {
        Covid19 covid19 = new CountryApi();
        System.out.println(covid19.getList());
        Covid19 covid191 = new CountryDayOnes("united-states");
        System.out.println(covid191.getList());
    }
}
