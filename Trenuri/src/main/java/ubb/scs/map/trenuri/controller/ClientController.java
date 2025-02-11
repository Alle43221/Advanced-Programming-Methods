package ubb.scs.map.trenuri.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ubb.scs.map.trenuri.domain.City;
import ubb.scs.map.trenuri.domain.TrainStation;
import ubb.scs.map.trenuri.service.Service;
import ubb.scs.map.trenuri.utils.observer.Observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientController implements Observer {
    public CheckBox checkBox;
    private Service service;
    @FXML
    private ComboBox departureCombo;
    @FXML
    private ComboBox destinationCombo;

    @FXML
    private ListView listView;
    @FXML
    private Label textCasuta=new Label();

    private String idOm;
    private Double constanta=10.0;

    private Double calcul(Integer nrStatii){
        Double rez=constanta*nrStatii;
        return rez;
    }
    ObservableList<String> model= FXCollections.observableArrayList();

    public void setService(Service service, String idOm) {
        this.service = service;
        initModel();
        service.addObserver(this);
        this.idOm=idOm;
        setCombo();
    }

    @FXML
    public void initialize() {
        listView.setItems(model);
    }

    private void initModel() {
        updateCasuta();
    }

    private void updateCasuta(){
        if(departureCombo.getValue() != null && destinationCombo.getValue()!=null) {
            String from = departureCombo.getValue().toString();
            String to = destinationCombo.getValue().toString();
            Integer cnt=service.nrPersoane(from,to);
            if(cnt==0){
                textCasuta.setText("");
            }
            else if(cnt==1)
            {
                textCasuta.setText("ESTE O PERSOANA CU ACEEASI CAUTARE");
            }
            else{
                textCasuta.setText("SUNT " + cnt.toString() + " PERSOANE CU ACEEASI CAUTARE");

            }
        }
    }

    public void setCombo(){
        Set<String> from = new HashSet<>();
        Set<String> to = new HashSet<>();
        for(City c : service.getCities()) {
            from.add(c.getNume());
            to.add(c.getNume());
        }
        departureCombo.getItems().addAll(from);
        destinationCombo.getItems().addAll(to);
    }



    public void handleSearch() {
        if(departureCombo.getValue() != null && destinationCombo.getValue()!=null) {
            if(checkBox.isSelected()) {
                String from = departureCombo.getValue().toString();
                String to = destinationCombo.getValue().toString();
                model.clear();
                for (TrainStation ts : service.getTrainStations()) {
                    String dep=ts.getDeparture();
                    String dest=ts.getDestination();
                    if (service.getNameById(dep).equals(from) && service.getNameById(dest).equals(to)) {
                        model.add(service.getNameById(dep) + " ---------- " + ts.getId() + " --------> " + service.getNameById(dest) + " PRET: " +calcul(1));
                    }
                }
                service.addCautare(idOm,from,to);
            }
            else{
                String from = departureCombo.getValue().toString();
                String to = destinationCombo.getValue().toString();
                model.clear();
                List<List<TrainStation>> results=service.bk(from, to, new ArrayList<>(), new HashSet<>());
                for (List<TrainStation> l : results) {
                    String line = "";
                    for (int i = 0; i < l.size()-1; i++) {
                        String dep= l.get(i).getDeparture();
                        line +=service.getNameById(dep) + " -- " + l.get(i).getId() + " --> ";
                    }
                    line +=service.getNameById(l.get(l.size()-1).getDeparture()) + " -- " + l.get(l.size()-1).getId() + " --> " + service.getNameById(l.get(l.size()-1).getDestination());
                    line+=  " PRET: " +calcul(l.size());
                    model.add(line);
                }

                service.addCautare(idOm,from,to);
            }

        }
    }

    @Override
    public void update() {
        initModel();
    }
}
