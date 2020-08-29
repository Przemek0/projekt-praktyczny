package pl.sdacademy.javafxui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.entities.StoreData;

public class DataChartController {
    private CovidDao covidDao;
    @FXML
    private Label activeCasesWorldLbl;
    @FXML
    private Label totalDeathsWorldLbl;
    @FXML
    private ComboBox<String> getListCountry;

    public void initialize(CovidDao covidDao) {
        this.covidDao = covidDao;
        StoreData currentWorldData = covidDao.getCurrentWorldData();
        activeCasesWorldLbl.setText(currentWorldData.getActiveCases() + "");
        totalDeathsWorldLbl.setText(currentWorldData.getTotalDeaths() + "");

        // Wypełnianie ComboBox -> nazwami krajów.
        covidDao.getCountries().forEach(country -> getListCountry.getItems().add(country.getName()));
        getListCountry.setValue("Poland");
    }

    public void setCovidDao(CovidDao covidDao) {
        this.covidDao = covidDao;
    }
}
