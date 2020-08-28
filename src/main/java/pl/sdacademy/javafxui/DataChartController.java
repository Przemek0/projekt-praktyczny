package pl.sdacademy.javafxui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.dao.DbCovidDao;

public class DataChartController {
    private CovidDao covidDao;

    @FXML
    private Label activeCasesWorldLbl;
    @FXML
    private Label totalDeathsWorldLbl;
    public void initialize() {
        int totalDeaths = covidDao.getCurrentWorldData().getTotalDeaths();
        int activeCases = covidDao.getCurrentWorldData().getActiveCases();
        activeCasesWorldLbl.setText(activeCases + "");
        totalDeathsWorldLbl.setText(totalDeaths + "");
    }

    public void setCovidDao(CovidDao covidDao) {
        this.covidDao = covidDao;
    }
}
