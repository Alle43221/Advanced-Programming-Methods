package ubb.scs.map.zboruri.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ubb.scs.map.zboruri.domain.Client;
import ubb.scs.map.zboruri.domain.Flight;
import ubb.scs.map.zboruri.domain.Ticket;
import ubb.scs.map.zboruri.service.Service;
import ubb.scs.map.zboruri.utils.observer.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClientController implements Observer {
    public Button previousButton;
    public Label pageLabel;
    private Service service;
    private Client client;
    private int currentPageSize=5;
    private int currentPage=0;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Flight, LocalDateTime> departureColoana;

    @FXML
    private ComboBox<String> fromCombo;

    @FXML
    private TableColumn<Flight, LocalDateTime> landingColoana;

    @FXML
    private TableColumn<Flight, Integer> seatsColoana;

    @FXML
    private TableView<Flight> tableView;

    @FXML
    private ComboBox<String> toCombo;

    @FXML
    private TableColumn<Flight,Integer> availableColoana;
    @FXML
    private Button nextButton;

    private Integer index=5;
    ObservableList<Flight> model= FXCollections.observableArrayList();

    public void setService(Service service,Client client) {
        this.service = service;
        this.client=client;
        initModel();
        service.addObserver(this);
    }

    @FXML
    public void initialize() {
        tableView.setItems(model);

        landingColoana.setCellValueFactory(new PropertyValueFactory<Flight, LocalDateTime>("landing"));
        departureColoana.setCellValueFactory(new PropertyValueFactory<Flight, LocalDateTime>("departure"));
        seatsColoana.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("seats"));

        availableColoana.setCellValueFactory(c -> {
            Flight fl=c.getValue();
            Integer cnt=0;
            for(Ticket t: service.getTickets())
            {
                if(t.getIdFlight().equals(fl.getId()))
                    cnt++;
            }
            return new ReadOnlyObjectWrapper<Integer>(fl.getSeats()-cnt);
        });

        tableView.setItems(model);
    }

    private void initModel() {
        setCombo();
        handleSearch();
    }

    public void setCombo(){
        Set<String> from = new HashSet<>();
        Set<String> to = new HashSet<>();
        for(Flight flight : service.getFlights()) {
            from.add(flight.getFrom());
            to.add(flight.getTo());
        }
        fromCombo.getItems().addAll(from);
        toCombo.getItems().addAll(to);
    }

    public void handleSearch() {
        LocalDate start = datePicker.getValue();
        String from = fromCombo.getValue();
        String to=toCombo.getValue();
        if(start!=null && from!=null && to!=null) {

            model.clear();
            model.addAll(Arrays.asList(service.findAllOnPageFilter(currentPage, from, to, start)));
            int res=service.getNoOfPages(from, to, start);
            int noOfPages=(int)Math.ceil((double)res/currentPageSize);
            if(currentPage==0 && currentPage==noOfPages){
                pageLabel.setText("Page: "+ (currentPage) +" / "+noOfPages);
            }
            else{
                pageLabel.setText("Page: "+ (currentPage+1) +" / "+noOfPages);
            }
            if(currentPage==0)
                previousButton.setDisable(true);
            if(currentPage>0)
                previousButton.setDisable(false);
            if(currentPage+1>=noOfPages)
                nextButton.setDisable(true);
            if(currentPage+1<noOfPages)
                nextButton.setDisable(false);
        }
    }

    public void handleBuy(ActionEvent event) {
        Flight flight= tableView.getSelectionModel().getSelectedItem();
        service.adaugaTicket(client.getUsername(),flight.getId());

        service.notifyObservers();
    }

    @Override
    public void update() {
        initModel();
    }

    public void handleNext(ActionEvent event){
        index=index+currentPageSize;
        currentPage++;

        handleSearch();
    }

    public void handlePrevious(ActionEvent event){
        currentPage--;
        index=index-currentPageSize;
        handleSearch();
    }
}
