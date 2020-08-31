package pl.sdacademy.jsonClassEntity.covid19.countries;

import pl.sdacademy.jsonClassEntity.Covid19;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Covid19 covid19 = new Countries();
        System.out.println(covid19.getString().toPrettyString());
        System.out.println(Arrays.stream(covid19.getCountries()).count());
        System.out.println(covid19.getTimeLastRefresh());
        Arrays.stream(covid19.getCountries()).forEach(e-> System.out.println(e.toString()));
    }
}
