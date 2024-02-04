package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application  {
    static ArrayList<Document> docVerileri ;
    private static Stage stg;


    @Override
    public void start(Stage stage) throws IOException {
        docVerileri = FileRead.fileReading("documents.txt");
        stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root, 528, 400);
        stage.setScene(scene);

        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
        }
    public void changeScene(String fxml, String title,int v, int v1) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = fxmlLoader.load();
        Scene scene = new Scene(pane, v, v1);
        Stage stage = new Stage();
        stage.setMinWidth(v);
        stage.setMinHeight(v1);
        stage.setMaxWidth(v);
        stage.setMaxHeight(v1);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }
    public void changeScene(String fxml) throws  IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}








