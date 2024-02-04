package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class directorMenuController implements Initializable {
    @FXML
    private ImageView logoImageView;
    @FXML
    private Label directorLabel;
    @FXML
    private Button showDocButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File registrationFile = new File("İmages/Ekran görüntüsü 2023-12-25 235719.png");
        Image registrationImage = new Image(registrationFile.toURI().toString());
        logoImageView.setImage(registrationImage);
        directorLabel.setText("Hoşgeldiniz (Director)");
    }
    public void showButtonOnAction(ActionEvent event) throws IOException {
        Main main = new Main();
        main.changeScene("showDirectorDoc.fxml","Show Document",633,431);
    }

}
