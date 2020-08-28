package pl.sdacademy.javafxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.sdacademy.dao.DbCovidDao;
import pl.sdacademy.jsonClasses.ApiEntityDataProvider;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main")); //examples/main aby uruchomić kontroler i widok z examples
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        MainController controller = fxmlLoader.getController();
        controller.setCovidDao(new DbCovidDao());
        controller.setDataProvider(new ApiEntityDataProvider());
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
