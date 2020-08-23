package pl.sdacademy.javafxui;

import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DataChartController {
    private CovidDao covidDao;

    public void initialize() {
        CovidDao covidDao = new CovidDao() {
            @Override
            public List<Country> getCountries() throws SQLException {
                return null;
            }

            @Override
            public StoreData getDataByCountryAndDateRange(int id, LocalDate from, LocalDate to) {
                return null;
            }

            @Override
            public StoreData getCurrentDataByCountry(int id) {
                return null;
            }

            @Override
            public StoreData getCurrentWorldData() {
                return null;
            }

            @Override
            public void storeData(List<Country> countryList) {

            }
        };
    }
}
