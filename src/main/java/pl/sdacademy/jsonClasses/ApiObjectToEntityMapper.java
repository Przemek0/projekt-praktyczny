package pl.sdacademy.jsonClasses;

import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;
import java.util.ArrayList;
import java.util.List;

public class ApiObjectToEntityMapper {

    public static List<Country> map(Summary summary) {
        List<Country> countries = new ArrayList<>();

        summary.getCountries()
                .forEach(e -> {
                    Country country = new Country(
                            e.getCountry(),
                            e.getCountryCode(),
                            0
                    );

                    StoreData storeData = new StoreData(
                            e.getDate(),
                            e.getTotalDeaths(),
                            e.getTotalConfirmed(),
                            e.getTotalRecovered(),
                            e.getTotalConfirmed() - e.getTotalDeaths()-e.getTotalRecovered(),
                            e.getTotalDeaths()
                    );

                    country.getStoreData().add(storeData);

                    countries.add(country);
                });

        return countries;
    }

}
