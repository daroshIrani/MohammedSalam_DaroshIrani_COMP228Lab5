package com.example.mohammedsalam_daroshirani_comp228lab5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onInsertPlayerInformationButtonClick(ActionEvent actionEvent) {
    }
}