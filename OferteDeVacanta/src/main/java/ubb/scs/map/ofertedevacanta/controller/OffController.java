package ubb.scs.map.ofertedevacanta.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import ubb.scs.map.ofertedevacanta.domain.Hotel;
import ubb.scs.map.ofertedevacanta.domain.Offers;
import ubb.scs.map.ofertedevacanta.service.Service;

import java.time.LocalDate;
import java.util.Objects;

public class OffController {

    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker endDate;
    @FXML
    public ListView offList;
    private Service service;
    private Hotel hotel;
    ObservableList<String> offModel= FXCollections.observableArrayList();

    public void setService(Service service,Hotel hotel) {
        this.service = service;
        this.hotel=hotel;
        initModel();
    }

    @FXML
    public void initialize() {
        offList.setItems(offModel);
    }
    private void initModel() {
        for(Offers offer : this.service.getOffers()){
            if(Objects.equals(this.hotel.getId(), offer.getHotel()))
            offModel.add("start: "+offer.getStart() + ", "+"end:" +offer.getEnd()+", percent: "+offer.getPercent());
        }
    }

    public void handleSearch(ActionEvent event) {
        if (endDate.getValue() == null || startDate.getValue() == null)
            return;
        LocalDate sd = startDate.getValue();
        LocalDate ed = endDate.getValue();
        offModel.clear();
        for (Offers off : service.getOffers()) {
            if (!off.getStart().isBefore(sd) && !off.getEnd().isAfter(ed) && this.hotel.getId().equals(off.getHotel())) {
                offModel.add(off.getId() + ":"
                        + off.getPercent() + "% începe la "
                        + off.getStart().toString()
                        + " până la " + off.getEnd().toString());
            }
        }
    }



}
