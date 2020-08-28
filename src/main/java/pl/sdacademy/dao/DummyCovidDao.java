package pl.sdacademy.dao;

import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// Utwórz implementacją interfejsu CovidDao (DB3) o nazwie DummyCovidDao.
// Niech implementacja ta zwraca na sztywno przygotowane dane. Metoda zapisująca dane niczego nie wykona.
public class DummyCovidDao implements CovidDao {
    private final List<Country> countries = new ArrayList<>();

    public DummyCovidDao() {
        Country poland = new Country("Poland", "PL", 1234566);
        StoreData polandStoreData = new StoreData(0, LocalDateTime.now(), 123, 1234,123123,123123,123123);
        poland.getStoreData().add(polandStoreData);
        Country germany = new Country("Germany", "DE", 1234566);
        StoreData germanyStoreData = new StoreData(1, LocalDateTime.now(), 4325, 325,235,4526,234);
        germany.getStoreData().add(germanyStoreData);
    }

    @Override
    public List<Country> getCountries() {
        return countries;
    }

    @Override
    public Set<StoreData> getDataByCountryAndDateRange(int id, LocalDate from, LocalDate to) {
        return countries.get(id).getStoreData();
    }

    @Override
    public StoreData getCurrentDataByCountry(int id) {
        Optional<StoreData> first = countries.get(id).getStoreData().stream().findFirst();
        return first.orElse(null);
    }

    @Override
    public StoreData getCurrentWorldData() {
        return new StoreData(
                1,
                LocalDateTime.now(),
                213,
                42,
                12345,
                123456,
                11
        );
    }

    @Override
    public void storeData(List<Country> countryList) {
    }
}
