package pl.sdacademy.javafxui.examples;

import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class MainController {
    //dodajemy adnotację do deklaracji zmiennej, a definicje wprowadzamy do *.fxml
    @FXML
    private MyClass myClass;
    @FXML
    private Label myLabel;

    public void initialize() {
        myLabel.setText(myClass.getText());
    }
}
