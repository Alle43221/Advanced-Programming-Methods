package ubb.scs.map.ofertedevacanta.controller;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ubb.scs.map.ofertedevacanta.domain.Hobbies;
import ubb.scs.map.ofertedevacanta.events.CustomEvent;
import ubb.scs.map.ofertedevacanta.domain.Hotel;
import ubb.scs.map.ofertedevacanta.domain.Location;
import ubb.scs.map.ofertedevacanta.domain.Offers;
import ubb.scs.map.ofertedevacanta.observer.Observer;
import ubb.scs.map.ofertedevacanta.service.Service;

import java.time.LocalDate;
import java.util.Objects;


public class ClientController implements Observer<CustomEvent> {
    @FXML
    public TableView<Offers> tableView;
    @FXML
    private TableColumn<Offers, String> hotelColoana;
    @FXML
    public TableColumn<Offers, String> locatieColoana;
    @FXML
    public TableColumn<Offers, LocalDate> startdateColoana;
    @FXML
    public TableColumn<Offers,LocalDate> enddateColoana;

    ObservableList<Hobbies> model1 = FXCollections.observableArrayList();
    ObservableList<Offers> model = FXCollections.observableArrayList();


    //---------------------------------
    @FXML
    public TableView<Hotel> hotelTable;
    @FXML
    public TableColumn<Hotel,String> numeHotelColoana;
    @FXML
    public DatePicker dataPicker;
    @FXML
    public TextField nrNoptiField;


    private Service service;
    private Long idClient;

    ObservableList<Hotel> modelHotel= FXCollections.observableArrayList();

    public void setService(Service service,Long idClient) {
        this.service = service;
        this.idClient=idClient;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        tableView.setItems(model);

        startdateColoana.setCellValueFactory(new PropertyValueFactory<Offers, LocalDate>("start"));
        enddateColoana.setCellValueFactory(new PropertyValueFactory<Offers, LocalDate>("end"));

        hotelColoana.setCellValueFactory(c -> {
            Offers off=c.getValue();
            for(Hotel h: service.getHotels())
            {
                if(off.getHotel().equals(h.getId()))
                    return new ReadOnlyObjectWrapper<String>(h.getName());
            }
            return null;
        });

        locatieColoana.setCellValueFactory(c -> {
            Offers off=c.getValue();
            for(Hotel h: service.getHotels())
            {
                if(off.getHotel().equals(h.getId())){
                    Double idLocatie=h.getLid();
                    for(Location l: service.getLocations())
                        if(l.getId().equals(idLocatie))
                            return new ReadOnlyObjectWrapper<String>(l.getName());
                }
            }
            return null;
        });

        tableView.setItems(model);


        hotelTable.setItems(modelHotel);
        numeHotelColoana.setCellValueFactory(new PropertyValueFactory<Hotel, String>("name"));
    }

    private void initModel() {
        for(Offers off : this.service.getOffersAvailable(idClient)){
            model.add(off);
        }

        for(Hotel h : this.service.getHotels()){
            modelHotel.add(h);
        }
    }



    public void handleRezerva(ActionEvent event){
        if(hotelTable.getSelectionModel().getSelectedItem() == null)
            return;
        Hotel hotel = hotelTable.getSelectionModel().getSelectedItem();

        LocalDate start = dataPicker.getValue();
        Integer noNights = Integer.parseInt(nrNoptiField.getText());
        Double idHotel=hotel.getId();

        service.adaugaRezervare(idClient,idHotel,start,noNights);

        nrNoptiField.setText("");
        dataPicker.setValue(null);
        service.makeEvent(new CustomEvent(service.getClientById(idClient).getHobby(), idClient));
    }

    @Override
    public void update(CustomEvent customEvent) {
        if(service.getClientById(idClient).getHobby()==customEvent.getHobby() && !Objects.equals(idClient, customEvent.getUserId()))
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "INFO", "Another person with the same hobby made a reservation!");
    }
}
