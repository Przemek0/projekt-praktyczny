package pl.sdacademy.javafxui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.dao.DbCovidDao;
import pl.sdacademy.entities.Country;
import pl.sdacademy.jsonClassEntity.covid19.ApiObjectToEntityMapper;
import pl.sdacademy.jsonClassEntity.covid19.countries.CountryDataProvider;
import pl.sdacademy.jsonClasses.ApiEntityDataProvider;
import pl.sdacademy.jsonClasses.EntityDataProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EventListener;
import java.util.List;

public class MainController {
    private CovidDao covidDao;
    @FXML
    private Button showChartBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Label updatedDateLbl;

    public void initialize(CovidDao covidDao) {
        this.covidDao  = covidDao;
        showChartBtn.setOnAction(event -> {
            try {
                openInNewWindow("dataChart");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        updateBtn.setOnAction(event -> {
            StringBuilder stringBuilder = new StringBuilder().append("Dane zaktualizowano: downloading data");
            updatedDateLbl.setText(stringBuilder.toString());
            Thread thread = new Thread(() -> {
                List<Country> countries = ApiObjectToEntityMapper.map(new CountryDataProvider().getCountries());
                covidDao.storeData(countries);
                stringBuilder.delete(21, stringBuilder.length());
                stringBuilder.append(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
                Platform.runLater(() -> updatedDateLbl.setText(stringBuilder.toString()));
            });
            thread.start();
        });
    }

    private void openInNewWindow(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
            Parent load = fxmlLoader.load();
            DataChartController controller = fxmlLoader.getController();
            controller.initialize(covidDao);
            Stage stage = new Stage();
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.show();
    }
}
