package pl.sdacademy.jsonClassEntity.covid19;

import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;
import pl.sdacademy.jsonClassEntity.covid19.countries.CountryApi;
import pl.sdacademy.jsonClassEntity.covid19.dataaccess.DataApi;
import pl.sdacademy.jsonClassEntity.covid19.dataaccess.DataByCountry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ApiObjectToEntityMapper {
    public static List<Country> map(List<CountryApi> countries) {
        DataByCountry dataProvider = new DataByCountry();
        List<Country> entityCountries = new ArrayList<>();
                countries.forEach(c -> {
                    dataProvider.getData(c.getSlug());
                    Set<DataApi> dataSet = dataProvider.getDataSet();
                    Country country = new Country(c.getName(), c.getCodeName(), c.getSlug());
                    dataSet.forEach(data -> {
                        LocalDateTime ldt = stringDateToLocalDateTime(data.getDate());
                        StoreData storeData = new StoreData();
                        storeData.setDate(ldt);
                        storeData.setDeaths(data.getDeaths());
                        storeData.setActiveCases(data.getActive());
                        storeData.setInfections(data.getConfirmed());
                        storeData.setRecoveries(data.getRecovered());
                        country.getStoreData().add(storeData);
                    });
                    entityCountries.add(country);
                });
        return entityCountries;
    }

    private static LocalDateTime stringDateToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDateTime.parse(date, formatter);
    }
}
