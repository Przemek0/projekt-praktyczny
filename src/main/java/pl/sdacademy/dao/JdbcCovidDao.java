package pl.sdacademy.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcCovidDao implements CovidDao {
    private PreparedStatement preparedStatement;

    @Override
    public List<Country> getCountries() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("asdf654321");
        dataSource.setDatabaseName("practical_project");
        Connection connection = dataSource.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM practical_project.country"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
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
}
