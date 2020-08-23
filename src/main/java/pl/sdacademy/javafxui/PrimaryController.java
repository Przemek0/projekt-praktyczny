package pl.sdacademy.javafxui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class PrimaryController {
    @FXML
    private Button showChartBtn;

    public void initialize() {
        showChartBtn.setOnAction(event -> {
            openInNewWindow("/pl/sdacademy/dataChart.fxml");
        });
    }

    private void openInNewWindow(String fxml) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
