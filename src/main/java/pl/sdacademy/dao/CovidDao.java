package pl.sdacademy.dao;

//Utwórz interfejs CovidDao. Oferuje metody (abstrakcyjne - bez implementacji):
//        - getCountries, która zwróci listę wszystkich krajów (bez danych dot. wirusa)
//        - getDataByCountryAndDateRange, której argumentami są identyfikator kraju oraz dwie daty - zakres od i do
//        - getCurrentDataByCountry, której argumentem jest identyfikator kraju - zwróci dane z dnia dzisiejszego
//        - getCurrentWorldData, która zwróci zagregowane, dzisiejsze dane z całego świata
//        - storeData, która usunie wszystkie dane, a następnie utrwali listę krajów, którą otrzyma jako parametr.

import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface CovidDao {
    public abstract List<Country> getCountries() throws SQLException;

    public StoreData getDataByCountryAndDateRange(int id, LocalDate from, LocalDate to);

    public StoreData getCurrentDataByCountry(int id);

    public StoreData getCurrentWorldData();

    public void storeData(List<Country> countryList);
}
