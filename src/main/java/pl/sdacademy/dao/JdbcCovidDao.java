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

public class JdbcCovidDao implements CovidDao {
    private final PreparedStatement getCountries;
    private final PreparedStatement getDataByCountryAndDateRange;
    private final PreparedStatement getCurrentDataByCountry;
    private final PreparedStatement getCurrentWorldData;
    private final PreparedStatement clearCountryStoreData;
    private final PreparedStatement clearStoreData;
    private final PreparedStatement clearCountry;
    private final PreparedStatement saveCountry;
    private final PreparedStatement saveStoreData;
    private final PreparedStatement saveCountryStoreData;
    private Connection connection;

    public JdbcCovidDao() throws SQLException {
        setConnection();
        getCountries = connection.prepareStatement(
                "SELECT * FROM practical_project.country"
        );

        getCurrentDataByCountry = connection.prepareStatement(
                "SELECT * " +
                        "FROM practical_project.storedata " +
                        "JOIN practical_project.country_storedata cs on storedata.id = cs.storeData_id " +
                        "WHERE cs.Country_id = ?"
        );

        getDataByCountryAndDateRange = connection.prepareStatement(
                "SELECT practical_project.storedata.* FROM practical_project.storedata " +
                        "JOIN practical_project.country_storedata countrysd on storedata.id = countrysd.storeData_id " +
                        "JOIN practical_project.country on country.id = countrysd.Country_id " +
                        "WHERE country_id = ? AND (date > ? AND date < ?)"
        );

        getCurrentWorldData = connection.prepareStatement(
                "SELECT SUM(storedata.activeCases)," +
                        "SUM(storedata.infections)," +
                        "SUM(storedata.recoveries)," +
                        "SUM(storedata.deaths)," +
                        "SUM(storedata.totalDeaths)," +
                        "date " +
                        "FROM practical_project.storedata"
        );

        clearCountryStoreData = connection.prepareStatement(
                "DELETE FROM practical_project.country_storedata"
        );

        clearStoreData = connection.prepareStatement(
                "DELETE FROM practical_project.storedata"
        );

        clearCountry = connection.prepareStatement(
                "DELETE FROM practical_project.country"
        );

        saveCountry = connection.prepareStatement(
                "INSERT INTO practical_project.country (codeName, name, numberResident) " +
                        "VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        saveStoreData = connection.prepareStatement(
                "INSERT INTO practical_project.storedata (activeCases, date, deaths, infections, recoveries, totalDeaths) " +
                        "VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        saveCountryStoreData = connection.prepareStatement(
                "INSERT INTO practical_project.country_storedata (Country_id, storeData_id) " +
                        "VALUES (?, ?)"
        );

    }

    @Override
    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        try {
            ResultSet resultSet = getCountries.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String codeName = resultSet.getString("codeName");
                String slug = resultSet.getString("numberResident");
                Country country = new Country(id, name, codeName, slug);
                countries.add(country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countries;
    }

    @Override
    public Set<StoreData> getDataByCountryAndDateRange(int id, LocalDateTime from, LocalDateTime to) {
        Set<StoreData> storeDataSet = new HashSet<>();
        try {
            getDataByCountryAndDateRange.setInt(1, id);
            getDataByCountryAndDateRange.setDate(2, Date.valueOf(String.valueOf(from)));
            getDataByCountryAndDateRange.setDate(3, Date.valueOf(String.valueOf(to)));
            ResultSet resultSet = getDataByCountryAndDateRange.executeQuery();
            while (resultSet.next()) {
                StoreData storeData = getStoredData(resultSet);
                storeDataSet.add(storeData);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return storeDataSet;
    }

    @Override
    public StoreData getCurrentDataByCountry(int id)  {
        StoreData storeData = new StoreData();
        try {
            getCurrentDataByCountry.setInt(1, id);
            ResultSet resultSet = getCurrentDataByCountry.executeQuery();
            if (resultSet.next()) {
                storeData = getStoredData(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return storeData;
    }

    @Override
    public StoreData getCurrentWorldData() {
        StoreData storeData = new StoreData();
        try {
            ResultSet resultSet = getCurrentWorldData.executeQuery();
            if (resultSet.next()) {
                storeData = getStoredData(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return storeData;
    }

    @Override
    public void storeData(List<Country> countryList) {
        try {
            clearCountryStoreData.executeUpdate();
            clearStoreData.executeUpdate();
            clearCountry.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        countryList.forEach(country -> {
            try {
                saveCountry(country);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void saveCountry(Country country) throws SQLException {
        saveCountry.setString(1, country.getCodeName());
        saveCountry.setString(2, country.getName());
        saveCountry.setString(3, country.getSlug());
        saveCountry.execute();
        ResultSet countryGeneratedKeys = saveCountry.getGeneratedKeys();
        countryGeneratedKeys.next();
        int countryId = countryGeneratedKeys.getInt(1);
        country.setId(countryId);
        country.getStoreData().forEach(storeData -> {
            try {
                saveStoreData(countryId, storeData);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void saveStoreData(int countryId, StoreData storeData) throws SQLException {
        saveStoreData.setInt(1, storeData.getActiveCases());
        saveStoreData.setDate(2, Date.valueOf(storeData.getDate().toLocalDate()));
        saveStoreData.setInt(3, storeData.getDeaths());
        saveStoreData.setInt(4, storeData.getInfections());
        saveStoreData.setInt(5, storeData.getRecoveries());
        saveStoreData.execute();
        ResultSet storeDataGeneratedKeys = saveStoreData.getGeneratedKeys();
        storeDataGeneratedKeys.next();
        int storeDataId = storeDataGeneratedKeys.getInt(1);
        storeData.setId(storeDataId);
        saveCountryStoreData.setInt(1, countryId);
        saveCountryStoreData.setInt(2, storeDataId);
        saveCountryStoreData.execute();
    }

    private StoreData getStoredData(ResultSet resultSet) throws SQLException {
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
                activeCases
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
            dataSource.setUrl("jdbc:mysql://localhost:3306/lotto?serverTimezone=UTC");
            //dataSource.setServerTimezone("UTC");
            dataSource.setUser("root");
            dataSource.setPassword("dr1");
            dataSource.setDatabaseName("practical_project");

            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
