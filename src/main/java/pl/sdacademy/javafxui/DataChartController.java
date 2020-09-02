package pl.sdacademy.javafxui;

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
//        StoreData currentWorldData = covidDao.getCurrentWorldData();
//        activeCasesWorldLbl.setText(currentWorldData.getActiveCases() + "");
//        totalDeathsWorldLbl.setText(currentWorldData.getDeaths() + "");
        initializeComboBox();
    }

    public void setCovidDao(CovidDao covidDao) {
        this.covidDao = covidDao;
    }

    private void initializeComboBox() {
        List<Country> countries = covidDao.getCountries();
        getListCountry.setCellFactory(getFactory());
        getListCountry.setButtonCell(getListCellFactory());
        getListCountry.getItems().addAll(countries);
        getListCountry.getSelectionModel().select(getListCountry.getItems().indexOf(getCountry()));
    }

    private Country getCountry() {
        return getListCountry
                .getItems()
                .stream()
                .filter(e -> e.getName().equals("Poland"))
                .collect(Collectors.toList())
                .get(0);
    }

    private Callback<ListView<Country>, ListCell<Country>> getFactory(){
        return lv -> new ListCell<>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
    }

    private ListCell<Country> getListCellFactory() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
    }
}
