package pl.sdacademy.jsonClassEntity.covid19;

import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;
import pl.sdacademy.jsonClassEntity.covid19.countries.CountryApi;
import pl.sdacademy.jsonClassEntity.covid19.dataaccess.DataApi;
import pl.sdacademy.jsonClassEntity.covid19.dataaccess.DataByCountryProvider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ApiObjectToEntityMapper {
    public static List<Country> map(List<CountryApi> countries) {
        List<Country> entityCountries = new ArrayList<>();
                countries.forEach(c -> {
                    Set<DataApi> data = new DataByCountryProvider().getData(c.getSlug());
                    Country country = new Country(c.getName(), c.getCodeName(), c.getSlug());
                    data.forEach(d -> {
                        LocalDateTime ldt = stringDateToLocalDateTime(d.getDate());
                        StoreData storeData = new StoreData();
                        storeData.setDate(ldt);
                        storeData.setDeaths(d.getDeaths());
                        storeData.setActiveCases(d.getActive());
                        storeData.setInfections(d.getConfirmed());
                        storeData.setRecoveries(d.getRecovered());
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
