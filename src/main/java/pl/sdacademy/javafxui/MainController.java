package pl.sdacademy.javafxui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.sdacademy.dao.CovidDao;
import pl.sdacademy.jsonClasses.EntityDataProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MainController {
    @FXML
    private CovidDao covidDao;
    @FXML
    private EntityDataProvider dataProvider;
    @FXML
    private Button showChartBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Label updatedDateLbl;

    public void initialize() {
        showChartBtn.setOnAction(event -> openInNewWindow("dataChart"));

        updateBtn.setOnAction(event -> {
            covidDao.storeData(dataProvider.load());
            String updated = "Dane zaktualizowano: " +
                    LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            updatedDateLbl.setText(updated);
        });
    }

    private void openInNewWindow(String fxml) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
