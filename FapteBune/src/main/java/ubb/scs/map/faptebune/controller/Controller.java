package ubb.scs.map.faptebune.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ubb.scs.map.faptebune.service.Service;

import java.util.Arrays;

public class Controller {

    @FXML
    private ListView<String> listView;
    @FXML
    private ComboBox<Integer> monthComboBox;
    @FXML
    private TextField yearField;
    private final ObservableList<String> items = FXCollections.observableArrayList();
    private Service service;

    @FXML
    public void initialize() {
        listView.setItems(items);
        monthComboBox.getItems().setAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
    }

    public void setService(Service service) {
        this.service = service;
    }
}
