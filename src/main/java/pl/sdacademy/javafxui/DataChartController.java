package pl.sdacademy.javafxui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.dao.DbCovidDao;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.util.List;

public class DataChartController {
    private CovidDao covidDao;
    @FXML
    private Label activeCasesWorldLbl;
    @FXML
    private Label totalDeathsWorldLbl;

    public void initialize(CovidDao covidDao) {
        this.covidDao = covidDao;
        StoreData currentWorldData = covidDao.getCurrentWorldData();
        activeCasesWorldLbl.setText(currentWorldData.getActiveCases() + "");
        totalDeathsWorldLbl.setText(currentWorldData.getTotalDeaths() + "");
    }

    public void setCovidDao(CovidDao covidDao) {
        this.covidDao = covidDao;
    }
}
