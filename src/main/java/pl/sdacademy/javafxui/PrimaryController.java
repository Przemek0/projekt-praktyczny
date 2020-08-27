package pl.sdacademy.javafxui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.dao.DbCovidDao;
import pl.sdacademy.entities.Country;
import pl.sdacademy.jsonClasses.ApiEntityDataProvider;
import pl.sdacademy.jsonClasses.Summary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PrimaryController {
    @FXML
    private Button showChartBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Label updatedDateLbl;

    public void initialize() {
        showChartBtn.setOnAction(event -> openInNewWindow("dataChart"));

        updateBtn.setOnAction(event -> {
            ApiEntityDataProvider apiEntityDataProvider = new ApiEntityDataProvider();
            CovidDao covidDao = new DbCovidDao();
            covidDao.storeData(apiEntityDataProvider.load());
            String updated = "Dane zaktualizowano: " + LocalDate.now()
                    .format(DateTimeFormatter.ISO_DATE);
            LocalDate now = LocalDate.now();

        });
    }

    private void openInNewWindow(String fxml) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
