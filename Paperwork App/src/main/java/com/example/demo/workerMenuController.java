package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class workerMenuController implements Initializable {
    @FXML
    private ImageView logoImageView;
    @FXML
    private Button addButton;
    @FXML
    private Button showButton;
    @FXML
    private Label workerLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File registrationFile = new File("İmages/Ekran görüntüsü 2023-12-25 235719.png");
        Image registrationImage = new Image(registrationFile.toURI().toString());
        logoImageView.setImage(registrationImage);
        workerLabel.setText("Hoşgeldiniz"+"(Worker)");

    }
    public void addButtonOnAction(ActionEvent event) throws IOException{
        Main main = new Main();
        main.changeScene("addDoc.fxml","Add Document",321,154);
    }
    public void showButtonOnAction(ActionEvent event) throws IOException{
        Main main = new Main();
        main.changeScene("showDocument.fxml","Show Document",633,431);
    }





}
