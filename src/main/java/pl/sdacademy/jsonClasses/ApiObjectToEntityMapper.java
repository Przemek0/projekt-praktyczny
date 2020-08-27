package pl.sdacademy.jsonClasses;

import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;
import java.util.ArrayList;
import java.util.List;

public class ApiObjectToEntityMapper {

    public static List<Country> map(Summary summary) {
        List<Country> countries = new ArrayList<>();

        summary.getCountries()
                .forEach(getCountry -> {
                    Country country = new Country(
                            getCountry.getCountry(),
                            getCountry.getCountryCode(),
                            0
                    );

                    StoreData storeData = new StoreData(
                            getCountry.getDate(),
                            getCountry.getTotalDeaths(),
                            getCountry.getTotalConfirmed(),
                            getCountry.getTotalRecovered(),
                            getCountry.getTotalConfirmed() - getCountry.getTotalDeaths()-getCountry.getTotalRecovered(),
                            getCountry.getTotalDeaths()
                    );

                    country.getStoreData().add(storeData);

                    countries.add(country);
                });

        return countries;
    }

}
