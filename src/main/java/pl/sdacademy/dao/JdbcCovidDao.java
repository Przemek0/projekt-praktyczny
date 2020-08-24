package pl.sdacademy.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JdbcCovidDao implements CovidDao {
    private PreparedStatement getCountries;
    private PreparedStatement getDataByCountryAndDateRange;
    private PreparedStatement getCurrentDataByCountry;
    private PreparedStatement getCurrentWorldData;
    private Connection connection;

    public JdbcCovidDao() throws SQLException {
        setConnection();
        getCountries = connection.prepareStatement(
                "SELECT * FROM practical_project.country"
        );

        getCurrentDataByCountry = connection.prepareStatement(
                "SELECT * FROM practical_project.storedata WHERE country_id = ?"
        );

        getDataByCountryAndDateRange = connection.prepareStatement(
                "SELECT practical_project.storedata.* FROM practical_project.storedata " +
                        "WHERE country_id = ? AND (date > ? AND date < ?)"
        );

        getCurrentWorldData = connection.prepareStatement(
                "SELECT practical_project.storedata.* " +
                        "FROM practical_project.storedata " +
                        "JOIN practical_project.country on country.id = storedata.country_id " +
                        "WHERE country.name = 'Global'"
        );
    }

    @Override
    public List<Country> getCountries() throws SQLException {
        ResultSet resultSet = getCountries.executeQuery();
        List<Country> countries = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String codeName = resultSet.getString("codeName");
            int numberResident = resultSet.getInt("numberResident");
            Country country = new Country(id, name, codeName, numberResident);
            countries.add(country);
        }
        return countries;
    }

    @Override
    public Set<StoreData> getDataByCountryAndDateRange(int id, LocalDate from, LocalDate to) throws SQLException {
        getDataByCountryAndDateRange.setInt(1, id);
        getDataByCountryAndDateRange.setDate(2, Date.valueOf(from));
        getDataByCountryAndDateRange.setDate(3, Date.valueOf(to));
        ResultSet resultSet = getDataByCountryAndDateRange.executeQuery();
        Set<StoreData> storeDataSet = new HashSet<>();
        while (resultSet.next()) {
            StoreData storeData = getStoreData(resultSet);
            storeDataSet.add(storeData);
        }
        return storeDataSet;
    }

    @Override
    public StoreData getCurrentDataByCountry(int id) throws SQLException {
        getCurrentDataByCountry.setInt(1, id);
        ResultSet resultSet = getCurrentDataByCountry.executeQuery();
        StoreData storeData = new StoreData();
        if (resultSet.next()) {
            storeData = getStoreData(resultSet);
        }
        return storeData;
    }

    @Override
    public StoreData getCurrentWorldData() throws SQLException {
        ResultSet resultSet = getCurrentWorldData.executeQuery();
        StoreData storeData = new StoreData();
        if (resultSet.next()) {
            storeData = getStoreData(resultSet);
        }
        return storeData;
    }

    @Override
    public void storeData(List<Country> countryList) {
    }

    private StoreData getStoreData(ResultSet resultSet) throws SQLException {
        int resultId = resultSet.getInt("id");
        int activeCases = resultSet.getInt("activeCases");
        Date date = resultSet.getDate("date");
        LocalDateTime dateTime = LocalDateTime.of(
                date.toLocalDate(),
                LocalTime.of(0, 0, 0, 0)
        );
        int deaths = resultSet.getInt("deaths");
        int infections = resultSet.getInt("infections");
        int recoveries = resultSet.getInt("recoveries");
        int totalDeaths = resultSet.getInt("totalDeaths");
        int country_id = resultSet.getInt("country_id");
        Country country = getCountry(country_id);
        return new StoreData(
                resultId,
                dateTime,
                deaths,
                infections,
                recoveries,
                activeCases,
                totalDeaths,
                country
        );
    }

    private Country getCountry(int country_id) throws SQLException {
        return getCountries().stream()
                .filter(c -> c.getId() == country_id)
                .collect(Collectors.toList()).get(0);
    }

    private void setConnection() {
        if (connection == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("asdf654321");
            dataSource.setDatabaseName("practical_project");

            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
