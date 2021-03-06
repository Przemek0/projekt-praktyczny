package pl.sdacademy.dao;

//Utwórz interfejs CovidDao. Oferuje metody (abstrakcyjne - bez implementacji):
//        - getCountries, która zwróci listę wszystkich krajów (bez danych dot. wirusa)
//        - getDataByCountryAndDateRange, której argumentami są identyfikator kraju oraz dwie daty - zakres od i do
//        - getCurrentDataByCountry, której argumentem jest identyfikator kraju - zwróci dane z dnia dzisiejszego
//        - getCurrentWorldData, która zwróci zagregowane, dzisiejsze dane z całego świata
//        - storeData, która usunie wszystkie dane, a następnie utrwali listę krajów, którą otrzyma jako parametr.

import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CovidDao{
    public List<Country> getCountries();

    public Set<StoreData> getDataByCountryAndDateRange(int id, LocalDateTime from, LocalDateTime to);

    public StoreData getCurrentDataByCountry(int id);

    public StoreData getCurrentWorldData();

    public void storeData(List<Country> countryList);
}
