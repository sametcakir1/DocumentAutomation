package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class addDocController {
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private Button addButton2;
    @FXML
    private Label categoryLabel;
    @FXML
    private Button backButton;


    public void initialize() {
        categoryComboBox.getItems().addAll("Transport", "Residential", "Criminal", "Job");
        categoryLabel.setText("Please Select Category");
    }
    @FXML
    public void addButtonOnAction(ActionEvent event) throws IOException{
        String selectedCategory = categoryComboBox.getValue();
        Users User = new Users(currentUser.firstname,currentUser.lastname,currentUser.tcNo,currentUser.employee,currentUser.username,currentUser.password);
        Document newDoc = new Document(selectedCategory,User);
        newDoc.DocFile();
        Document.documentList.add(newDoc);

    }
    @FXML
    public void backButtonOnAction(){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}

