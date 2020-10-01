package pl.sdacademy.javafxui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.util.Callback;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.entities.Country;
import pl.sdacademy.entities.StoreData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class DataChartController {
    private CovidDao covidDao;
    @FXML
    private Label activeCasesWorldLbl;
    @FXML
    private Label totalDeathsWorldLbl;
    @FXML
    private ComboBox<Country> getListCountry;
    @FXML
    private LineChart<String, Number> dataChart;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;

    public void initialize(CovidDao covidDao) {
        this.covidDao = covidDao;
        StoreData currentWorldData = covidDao.getCurrentWorldData();
        activeCasesWorldLbl.setText(currentWorldData.getActiveCases() + "");
        totalDeathsWorldLbl.setText(currentWorldData.getDeaths() + "");
        initializeComboBox();
        initializeDataChart();
        fromDatePicker.setOnAction(event -> updateDataChart());
        toDatePicker.setOnAction(event -> updateDataChart());
        getListCountry.setOnAction(event -> updateDataChart());
    }

    private void updateDataChart() {
        int countryId = getCountryIdFromComboBox();
        List<StoreData> sortedStoreData = getSortedStoreData(covidDao, countryId);
        setSeriesData(sortedStoreData);
    }

    private int getCountryIdFromComboBox() {
        return getListCountry.getValue().getId();
    }

    private void initializeDataChart() {
        List<StoreData> sortedData = getSortedStoreData(covidDao, getCountryIdFromComboBox());
        initializeSeries(sortedData);
    }

    private void initializeSeries(List<StoreData> sortedData) {
        List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();
        seriesList.add(createSeries("Zgony"));
        seriesList.add(createSeries("Infekcje"));
        seriesList.add(createSeries("Ozdrowienia"));
        seriesList.add(createSeries("Chorzy"));
        dataChart.getData().setAll(seriesList);
        setSeriesData(sortedData);
    }

    private void setSeriesData(List<StoreData> sortedData) {
        ObservableList<XYChart.Series<String, Number>> data = dataChart.getData();
        data.forEach(e -> e.getData().clear());
        sortedData.forEach(e -> {
            data.get(0).getData().add(new XYChart.Data<>(e.getDate().toLocalDate().toString(), e.getDeaths()));
            data.get(1).getData().add(new XYChart.Data<>(e.getDate().toLocalDate().toString(), e.getInfections()));
            data.get(2).getData().add(new XYChart.Data<>(e.getDate().toLocalDate().toString(), e.getRecoveries()));
            data.get(3).getData().add(new XYChart.Data<>(e.getDate().toLocalDate().toString(), e.getActiveCases()));
        });
    }

    private XYChart.Series<String, Number> createSeries(String name) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);
        return series;
    }

    private List<StoreData> getSortedStoreData(CovidDao covidDao, int countryId) {
        LocalDate fromDatePickerValue = fromDatePicker.getValue();
        LocalDate toDatePickerValue = toDatePicker.getValue();
        LocalDateTime from;
        LocalDateTime to;
        if (fromDatePickerValue != null) {
            from = LocalDateTime.of(fromDatePickerValue, LocalTime.MIDNIGHT);
        } else {
            from = LocalDateTime.of(LocalDate.EPOCH, LocalTime.MIDNIGHT);
        }
        if (toDatePickerValue != null) {
            to = LocalDateTime.of(toDatePickerValue, LocalTime.MIDNIGHT);
        } else {
            to = LocalDateTime.now();
        }
        Set<StoreData> dataByCountryAndDateRange = covidDao.getDataByCountryAndDateRange(
                countryId,
                from,
                to);
        return dataByCountryAndDateRange
                .stream()
                .sorted(Comparator.comparing(StoreData::getDate))
                .collect(Collectors.toList());
    }

    private void initializeComboBox() {
        List<Country> countries = covidDao.getCountries();
        countries.sort(Comparator.comparing(Country::getName));
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

    private Callback<ListView<Country>, ListCell<Country>> getFactory() {
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
