package pl.sdacademy.javafxui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
//    @FXML
//    private CovidDao covidDao;
//    @FXML
//    private EntityDataProvider dataProvider;
    private CovidDao covidDao = new DbCovidDao();
    private EntityDataProvider dataProvider = new ApiEntityDataProvider();
    @FXML
    private Button showChartBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Label updatedDateLbl;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            showChartBtn.setOnAction(event -> openInNewWindow("dataChart"));

            updateBtn.setOnAction(event -> {
                covidDao.storeData(dataProvider.load());
                String updated = "Dane zaktualizowano: " +
                        LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                updatedDateLbl.setText(updated);
            });
        });
    }

    private void openInNewWindow(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
            DataChartController controller = fxmlLoader.getController();
            controller.setCovidDao(covidDao);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCovidDao(CovidDao covidDao) {
        this.covidDao = covidDao;
    }

    public void setDataProvider(EntityDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }
}
