package pl.sdacademy.javafxui;

import javafx.css.converter.StringConverter;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.util.List;
import java.util.stream.Collectors;

public class DataChartController {
    private CovidDao covidDao;
    @FXML
    private Label activeCasesWorldLbl;
    @FXML
    private Label totalDeathsWorldLbl;
    @FXML
    private ComboBox<Country> getListCountry;

    public void initialize(CovidDao covidDao) {
        this.covidDao = covidDao;
        StoreData currentWorldData = covidDao.getCurrentWorldData();
        activeCasesWorldLbl.setText(currentWorldData.getActiveCases() + "");
        totalDeathsWorldLbl.setText(currentWorldData.getTotalDeaths() + "");

        // Wypełnianie ComboBox -> nazwami krajów.
        List<Country> countries = covidDao.getCountries();
        Callback<ListView<Country>, ListCell<Country>> factory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
        getListCountry.setCellFactory(factory);
        getListCountry.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });
        getListCountry.getItems().addAll(countries);

        Country poland = getListCountry
                .getItems()
                .stream()
                .filter(e -> e.getName().equals("Poland")).collect(Collectors.toList()).get(0);
        getListCountry.getSelectionModel().select(getListCountry.getItems().indexOf(poland));
    }

    public void setCovidDao(CovidDao covidDao) {
        this.covidDao = covidDao;
    }
}
