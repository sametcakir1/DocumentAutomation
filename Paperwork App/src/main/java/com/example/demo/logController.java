package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;




public class logController  implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Button signinButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView documentImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File documentFile = new File("İmages/Logo.png");
        Image documentImage = new Image(documentFile.toURI().toString());
        documentImageView.setImage(documentImage);
    }
    public void loginButtonOnAction(ActionEvent event) {
        loginMessageLabel.setText("You try to login");
        try {
            if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
                validateLogin();
            } else {
                loginMessageLabel.setText("Please enter username and password");
            }
        } catch (InvalidLoginException e) {
            showAlert("Invalid Login", "Invalid username or password. Please try again.");

        } catch (IOException e) {
            e.printStackTrace();
            loginMessageLabel.setText(e.getMessage());
            System.out.println("Invalid login attempt for user: " + currentUser.username);
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    protected void signinButtonOnAction() throws IOException {
        Stage stage = (Stage) signinButton.getScene().getWindow();
        createAccountForm();
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin() throws IOException,InvalidLoginException {
        currentUser.username = usernameTextField.getText().toString();
        String enteredPassword= enterPasswordField.getText().toString();
        String controlUser = "Worker"+ "-"+currentUser.username+"-"+ enteredPassword;
        String controlUser2 = "Director"+ "-"+currentUser.username+"-"+ enteredPassword;
        BufferedReader reader;
        Main main = new Main();
        reader = new BufferedReader(new FileReader("users.txt"));
        ArrayList<String> exitData = new ArrayList<>();
        String line ;

        while ((line = reader.readLine()) != null){
            exitData.add(line);
        }
        reader.close();

        if (exitData.contains(controlUser)) {
            main.changeScene("workerMenu.fxml","Worker Menu",622,352);
            loginMessageLabel.setText("Login successful!");
            System.out.println("Successful login for user: " + currentUser.username);
        }
        else if(exitData.contains(controlUser2)){
            main.changeScene("directorMenu.fxml","Director Menu",630,357);
            loginMessageLabel.setText("Login successful!");
            System.out.println("Successful login for user: " + currentUser.username);
            }
         else {
           throw new InvalidLoginException("Invalid username or password. Please try again.");
        }
    }
    public void createAccountForm(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(root, 520, 417);
            registerStage.setScene(scene);
            registerStage.setTitle("Register Form");
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }
    }



}
