package ubb.scs.map.comenzirestaurant.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import ubb.scs.map.comenzirestaurant.domeniu.Angajat;
import ubb.scs.map.comenzirestaurant.domeniu.MyMenuItem;
import ubb.scs.map.comenzirestaurant.events.UtilizatorEntityChangeEvent;
import ubb.scs.map.comenzirestaurant.observer.Observer;
import ubb.scs.map.comenzirestaurant.service.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ubb.scs.map.comenzirestaurant.domeniu.Order;


public class StaffController implements Observer<UtilizatorEntityChangeEvent> {
    private Service service;
    private Stage dialogStage;
    @FXML
    private TableView<Order> tabel;
    @FXML
    private TableColumn<Order,String>produse;
    @FXML
    private TableColumn<Order, String>data;
    @FXML
    private TableColumn<Order,Long>masa;
    private ObservableList<Order> model = FXCollections.observableArrayList();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void setService(Service service, Stage dialogStage) {
        this.service = service;
        this.dialogStage = dialogStage;
        initModel();
        this.service.addObserver(this);
    }
    @FXML
    public void initialize()
    {
        tabel.setItems(model);
    }

    public void initModel() {
        model.setAll(service.getOrders());
        data.setCellValueFactory((cellData ->
                new SimpleStringProperty(cellData.getValue().getDate().format(formatter)) // Assuming getTableName() is the method you want to call
        ));
        masa.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        produse.setCellValueFactory(cellData -> {
            List<String> itemNames = cellData.getValue().getMenuItems().stream()
                    .map(id -> service.MenufindOne(id))
                    .filter(Objects::nonNull)
                    .map(MyMenuItem::getItem)
                    .collect(Collectors.toList());
            return new SimpleStringProperty(String.join(", ", itemNames));
        });


    }
    public void update(UtilizatorEntityChangeEvent utilizatorEntityChangeEvent) {
        initModel();
    }

}
