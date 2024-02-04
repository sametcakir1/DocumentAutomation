package com.example.demo;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class showDocController implements Initializable  {
    @FXML
    private TreeTableView<Document> docTable;
    @FXML
    private TreeTableColumn<Document, Integer> serialColumn;
    @FXML
    private TreeTableColumn<Document, Integer> codeColumn;
    @FXML
    private TreeTableColumn<Document, String> categoryColumn;
    @FXML
    private TreeTableColumn<Document, String> dateColumn;
    @FXML
    private TreeTableColumn<Document, String> validityColumn;
    @FXML
    private TreeTableColumn<Document, String> added_byColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Main.docVerileri == null) {
            Main.docVerileri = FileRead.fileReading("documents.txt");

        }
        Main.docVerileri = FileRead.fileReading("documents.txt");
        addDocTable();
    }
    protected void addDocTable() {

        try {
            TreeItem<Document> treeItem = new TreeItem<>();

            for (Document doc : Main.docVerileri) {
                TreeItem<Document> docItem = new TreeItem<>(doc);
                treeItem.getChildren().add(docItem);
            }

            serialColumn.setCellValueFactory(myFile -> { // setCellValue metodu hücrenin değerini belirlemeye yarar.
                Document doc = myFile.getValue().getValue();
                return new ReadOnlyObjectWrapper<>(doc != null ? doc.getSerialNo() : null);
            });// ReadOnlyObjectWrapper her bir değer null ise null döndürmeye yarar.

            codeColumn.setCellValueFactory(myFile -> {
                Document doc = myFile.getValue().getValue();
                return new ReadOnlyObjectWrapper<>(doc != null ? doc.getCode() : null);

            });
            categoryColumn.setCellValueFactory(myFile -> {
                Document doc = myFile.getValue().getValue();
                return new ReadOnlyObjectWrapper<>(doc != null ? doc.getCategory() : null);
            });

            dateColumn.setCellValueFactory(myFile -> {
                Document doc = myFile.getValue().getValue();
                return new ReadOnlyObjectWrapper<>(doc != null ? doc.getDate() : null);
            });
            validityColumn.setCellValueFactory(myFile -> {
                String control;
                Document doc =myFile.getValue().getValue();
                if(doc.isValidity() == true){
                     control= "geçerli değil";
                }
                else {
                     control="geçerli ";
                }
                return new ReadOnlyObjectWrapper<>(doc != null ? control : null);
            });
            added_byColumn.setCellValueFactory(myFile -> {
                Document doc = myFile.getValue().getValue();

                return new ReadOnlyObjectWrapper<>(doc != null ? doc.isim : null);
            });




            docTable.setRoot(treeItem);
            docTable.setShowRoot(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
