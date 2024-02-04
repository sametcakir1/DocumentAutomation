package com.example.demo;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class showDirectorDocController implements docProcess  {
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
    @FXML
    private TreeTableColumn<Document, String> signatoryColumn;
    @FXML
    private Button deleteButton;
    @FXML
    private Button activateButton;

    @FXML
    public void initialize() {
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

            serialColumn.setCellValueFactory(myFile -> {
                Document doc = myFile.getValue().getValue();
                return new ReadOnlyObjectWrapper<>(doc != null ? doc.getSerialNo() : null);
            });

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
    public void deleteButtonOnAction(ActionEvent event) throws IOException{
        deleteSelectedDocuments();
        }
    public void activateButtonOnAction(ActionEvent event) throws IOException {
        try {
            ObservableList<TreeItem<Document>> selectedItems = docTable.getSelectionModel().getSelectedItems();
            for (TreeItem<Document> selectedItem : selectedItems) {
                Document selectedDoc = selectedItem.getValue();

                if (selectedDoc != null) {
                    selectedDoc.setValidity(!selectedDoc.isValidity());
                    updateValidityColumn(selectedItem, selectedDoc.isValidity());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void deleteSelectedDocuments() {
        try {
            ObservableList<TreeItem<Document>> selectedItems = docTable.getSelectionModel().getSelectedItems();


            for (TreeItem<Document> selectedItem : selectedItems) {
                TreeItem<Document> parent = selectedItem.getParent();
                if (parent != null) {
                    parent.getChildren().remove(selectedItem);
                } else {
                    docTable.getRoot().getChildren().remove(selectedItem);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void updateValidityColumn(TreeItem<Document> treeItem, boolean isValid) {
        int validityColumnIndex = docTable.getColumns().indexOf(validityColumn);

        if (validityColumnIndex >= 0) {
            treeItem.getChildren().forEach(childItem -> {

                ObservableList<TreeTableColumn<Document, ?>> columns = docTable.getColumns();
                TreeItem<Document> validityItem = childItem.getChildren().get(validityColumnIndex);

                Document updatedDocument = validityItem.getValue();
                if (updatedDocument != null) {
                    updatedDocument.setValidity(isValid);

                    validityItem.setValue(updatedDocument);
                }

                updateValidityColumn(childItem, isValid);
            });
            docTable.refresh();
        }
    }

}

