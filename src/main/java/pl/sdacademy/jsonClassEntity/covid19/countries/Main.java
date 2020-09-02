package pl.sdacademy.jsonClassEntity.covid19.countries;

import pl.sdacademy.jsonClassEntity.Covid19;
import pl.sdacademy.jsonClassEntity.covid19.dataaccess.DataByCountry;

public class Main {
    public static void main(String[] args) {
        Covid19 covid19 = new CountryDataProvider();
        System.out.println(covid19.getList());
        DataByCountry dataByCountry = new DataByCountry();
        dataByCountry.getData("united-states");
        System.out.println(dataByCountry.getDataSet());
    }
}
