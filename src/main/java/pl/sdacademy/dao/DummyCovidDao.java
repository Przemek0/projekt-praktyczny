package pl.sdacademy.dao;

import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// Utwórz implementacją interfejsu CovidDao (DB3) o nazwie DummyCovidDao.
// Niech implementacja ta zwraca na sztywno przygotowane dane. Metoda zapisująca dane niczego nie wykona.
public class DummyCovidDao implements CovidDao {
    @Override
    public List<Country> getCountries() {
        Country poland = new Country("Poland", "PL", 1234566);
        Country germany = new Country("Germany", "DE", 1234566);
        List<Country> countries = new ArrayList<>();
        countries.add(poland);
        countries.add(germany);
        return countries;
    }

    @Override
    public Set<StoreData> getDataByCountryAndDateRange(int id, LocalDate from, LocalDate to) {
        Country country = getCountries().get(0);
        StoreData storeData = new StoreData(
                1,
                LocalDateTime.now(),
                123,
                1234,
                12345,
                123456,
                11,
                country
        );
        Set<StoreData> storeDataSet = new HashSet<>();
        storeDataSet.add(storeData);
        return storeDataSet;
    }

    @Override
    public StoreData getCurrentDataByCountry(int id) {
        Country country = getCountries().get(0);
        return new StoreData(
                1,
                LocalDateTime.now(),
                11,
                1234,
                12345,
                123456,
                11,
                country
        );
    }

    @Override
    public StoreData getCurrentWorldData() {
        Country country = getCountries().get(0);
        return new StoreData(
                1,
                LocalDateTime.now(),
                213,
                42,
                12345,
                123456,
                11,
                country
        );
    }

    @Override
    public void storeData(List<Country> countryList) {
    }
}
