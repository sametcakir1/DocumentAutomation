package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class registerController implements Initializable {
    @FXML
    private Label employeeLabel;

    @FXML
    private ChoiceBox<String> employeeChoiceBox;

    private String[] employee= {"Director","Worker"};

    @FXML
    private ImageView registrationImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label registrationLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField tcnoTextField;
    @FXML
    private TextField usernameTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeChoiceBox.getItems().addAll(employee);
        File registrationFile = new File("İmages/user registirion.png");
        Image registrationImage = new Image(registrationFile.toURI().toString());
        registrationImageView.setImage(registrationImage);

    }
    public void registerButtonOnAction(ActionEvent event) throws IOException{
        String firstname = firstnameTextField.getText().toString();
        String lastname = lastnameTextField.getText().toString();
        String tcNo = tcnoTextField.getText().toString();
        String selectedEmployee= employeeChoiceBox.getValue();
        String username = usernameTextField.getText().toString();
        String password = setPasswordField.getText().toString();

        registerUser();
        Users users = new Users(firstname,lastname,tcNo,selectedEmployee,username,password);
        users.UsersFile();

    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();


    }
    public void registerUser(){
        if(setPasswordField.getText().equals(confirmPasswordField.getText())&&!confirmPasswordField.getText().isBlank()){
            registrationLabel.setText("User has been registered successfully!");
        } else if (firstnameTextField.getText().isEmpty()) {
            registrationLabel.setText("Please enter information");
        } else {
            confirmPasswordLabel.setText("Password doesn't match");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public TextField getFirstnameTextField() {
        return firstnameTextField;
    }

    public TextField getLastnameTextField() {
        return lastnameTextField;
    }
}





