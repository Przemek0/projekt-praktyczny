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
import pl.sdacademy.jsonClasses.ApiEntityDataProvider;
import pl.sdacademy.jsonClasses.EntityDataProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainController {
    private CovidDao covidDao;
    private EntityDataProvider dataProvider;
    @FXML
    private Button showChartBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Label updatedDateLbl;

    public void initialize(CovidDao covidDao, ApiEntityDataProvider dataProvider) {
        this.covidDao  = covidDao;
        this.dataProvider = dataProvider;
        showChartBtn.setOnAction(event -> {
            try {
                openInNewWindow("dataChart");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        updateBtn.setOnAction(event -> {
            covidDao.storeData(dataProvider.load());
            String updated = "Dane zaktualizowano: " +
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            updatedDateLbl.setText(updated);
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
