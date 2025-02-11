package ubb.scs.map.comenzirestaurant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ubb.scs.map.comenzirestaurant.domeniu.MyMenuItem;
import ubb.scs.map.comenzirestaurant.domeniu.Order;
import ubb.scs.map.comenzirestaurant.domeniu.Table;
import ubb.scs.map.comenzirestaurant.events.UtilizatorEntityChangeEvent;
import ubb.scs.map.comenzirestaurant.observer.Observer;
import ubb.scs.map.comenzirestaurant.service.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableController implements Observer<UtilizatorEntityChangeEvent> {
    public Label comandaCurenta;
    private Table table;
    private Service service;
    private Stage dialogStage;

    @FXML
    private VBox tableContainer;

    private List<MyMenuItem> selectedItems = new ArrayList<>();

    public void setService(Service service, Stage dialogStage, Table table) {
        this.service = service;
        this.dialogStage = dialogStage;
        this.table = table;
        initModel();
        this.service.addObserver(this);
    }

    @FXML
    public void initialize() {
    }

    public void initModel() {
        tableContainer.getChildren().clear();
        selectedItems.clear();
        Map<String, List<MyMenuItem>> menuByCategory = service.getMenuGroupedByCategory();
        for (Map.Entry<String, List<MyMenuItem>> entry : menuByCategory.entrySet()) {
            String category = entry.getKey();
            List<MyMenuItem> items = entry.getValue();

            Label categoryLabel = new Label(category);
            categoryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 0 5 0;");

            TableView<MyMenuItem> tableView = new TableView<>();
            tableView.setPrefWidth(350);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            TableColumn<MyMenuItem, String> itemColumn = new TableColumn<>("Item");
            itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));

            TableColumn<MyMenuItem, Float> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("pret"));

            TableColumn<MyMenuItem, String> currencyColumn = new TableColumn<>("Currency");
            currencyColumn.setCellValueFactory(new PropertyValueFactory<>("moneda"));

            tableView.getColumns().addAll(itemColumn, priceColumn, currencyColumn);
            tableView.getItems().addAll(items);
            tableView.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    MyMenuItem clickedItem = tableView.getSelectionModel().getSelectedItem();
                    if (clickedItem != null) {
                        if (selectedItems.contains(clickedItem)) {
                            selectedItems.remove(clickedItem);
                            comandaCurenta.setText("");
                            String lista="";
                            for (MyMenuItem item : selectedItems) {
                                if(!clickedItem.equals(item)) {
                                    lista+=item.getItem()+" "+item.getPret() +" "+item.getMoneda()+"\n";
                                }
                            }
                            comandaCurenta.setText(lista);
                            System.out.println("Removed: " + clickedItem.getItem());
                        } else {
                            selectedItems.add(clickedItem);
                            System.out.println("Added: " + clickedItem.getItem());
                            comandaCurenta.setText("");
                            String lista="";
                            for (MyMenuItem item : selectedItems) {
                                    lista+=item.getItem()+" "+item.getPret() +" "+item.getMoneda()+"\n";
                            }
                            comandaCurenta.setText(lista);
                        }
                    }
                }
            });
            tableContainer.getChildren().add(categoryLabel);
            tableContainer.getChildren().add(tableView);
        }
    }

    public void update(UtilizatorEntityChangeEvent utilizatorEntityChangeEvent) {
        initModel();
    }

    public void handle_place_order(ActionEvent actionEvent) {

        /*for (javafx.scene.Node node : tableContainer.getChildren()) {
            if (node instanceof TableView<?>) {
                TableView<MyMenuItem> tableView = (TableView<MyMenuItem>) node;
                selectedItems.addAll(tableView.getSelectionModel().getSelectedItems());
            }
        }*/

        if (!selectedItems.isEmpty()) {
            System.out.println("Order placed for the following items:");
            selectedItems.forEach(item -> System.out.println("- " + item.getItem() + " (" + item.getPret() + " " + item.getMoneda() + ")"));
            List<Long> itemIds = selectedItems.stream()
                    .map(MyMenuItem::getId)
                    .collect(Collectors.toList());
            service.saveOrder(new Order(table.getId(), itemIds, LocalDateTime.now(), Order.Status.PLACED));

        } else {
            System.out.println("No items selected. Cannot place order.");
        }
        selectedItems.clear();
        comandaCurenta.setText("");
    }
}