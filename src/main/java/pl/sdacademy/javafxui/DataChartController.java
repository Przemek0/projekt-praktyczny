package pl.sdacademy.javafxui;

import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.dao.DbCovidDao;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class DataChartController {
    private CovidDao covidDao;

    public void initialize() {
        CovidDao covidDao = new DbCovidDao();
    }
}
