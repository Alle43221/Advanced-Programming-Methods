package ubb.scs.map.template.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ubb.scs.map.template.domain.AdoptionCenter;
import ubb.scs.map.template.domain.Animal;
import ubb.scs.map.template.service.Service;
import ubb.scs.map.template.utils.events.RequestEntityChangeEvent;
import ubb.scs.map.template.utils.observer.Observer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TableController implements Observer<RequestEntityChangeEvent> {
    public Label nameLabel;
    public Label locationLabel;
    public Label capacityLabel;
    public Label percentLabel;
    public ListView<Animal> listView;
    public ComboBox<String> comboBox;
    private Service service;
    private Stage dialogStage;
    public int CenterId;

    public int getCenterId() {
        return this.CenterId;
    }

    ObservableList<Animal> model= FXCollections.observableArrayList();

    public void setService(Service service, Stage dialogStage, int CenterId) {
        this.service = service;
        this.dialogStage = dialogStage;
        this.CenterId = CenterId;
        initModel();
        this.service.addObserver(this);
    }

    @FXML
    public void initialize() {
        listView.setItems(model);
    }

    public void initModel() {
        comboBox.setItems(
                FXCollections.observableArrayList(
                        Stream.concat(
                                Stream.of("All Types"),
                                Arrays.stream(Animal.Type.values())
                                        .map(Enum::name)
                        ).collect(Collectors.toList())
                )
        );
        Optional<AdoptionCenter> res=service.getAdoptionCenter(CenterId);
        nameLabel.setText(res.get().getName());
        locationLabel.setText(res.get().getLocation());
        capacityLabel.setText(Integer.toString(res.get().getCapacity()));
        listView.getItems().clear();
        model.clear();
        int cate_animale=0;
        List< Animal> res1=service.getAllAnimalsFromCenter(CenterId);
        cate_animale=res1.size();

        if(comboBox.getSelectionModel().getSelectedItem()==null || comboBox.getSelectionModel().getSelectedItem().equals("All Types")){

            for (Animal a: res1) {
                model.add(a);
            }
        }
        else{
            String type=comboBox.getSelectionModel().getSelectedItem();
            List< Animal> res2=service.getAllOfType(Animal.Type.valueOf(type), CenterId);
            for (Animal a: res2) {
                model.add(a);
            }

        }

        listView.setItems(model);
        listView.setCellFactory(param -> new ListCell<Animal>() {
            public void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {

                    Label label = new Label(item.toString());
                    Button button = new Button("Request Transfer");

                    button.setOnAction(event -> {
                        service.addRequest(CenterId, item.getId());
                    });

                    HBox hBox = new HBox(10, label, button);
                    setGraphic(hBox);
                }
            }
        });
        double percentage = ((double) cate_animale / res.get().getCapacity()) * 100;
        percentLabel.setText("Occupancy: " + String.format("%.2f%%", percentage));
        listView.setItems(model);
    }



    @Override
    public void update(RequestEntityChangeEvent animalEntityChangeEvent) {
        initModel();
        if(animalEntityChangeEvent.getData().getIdSursa()!=CenterId && animalEntityChangeEvent.getData().getIdAnimal()!=-1){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(service.getAdoptionCenter(animalEntityChangeEvent.getData().getIdSursa()).get().getName() + " requested to transfer " +
                    service.getAnimal(animalEntityChangeEvent.getData().getIdAnimal()).getName());
            alert.setHeaderText("Do you want to accept the transfer?");
            alert.setContentText("Answer for Center: " + service.getAdoptionCenter(CenterId).get().getName());

            ButtonType acceptButton = new ButtonType("Accept");
            ButtonType ignoreButton = new ButtonType("Ignore");

            alert.getButtonTypes().setAll(acceptButton, ignoreButton);

            alert.showAndWait().ifPresent(response -> {
                if (response == acceptButton) {
                    service.editRequest(animalEntityChangeEvent.getData(), CenterId);
                } else if (response == ignoreButton) {
                }
            });
        }
    }

    public void handleSelectionChange(ActionEvent actionEvent) {initModel();}
}