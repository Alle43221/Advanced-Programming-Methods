package ubb.scs.map.faptebune;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ubb.scs.map.faptebune.controller.MessageAlert;
import ubb.scs.map.faptebune.domain.Nevoie;
import ubb.scs.map.faptebune.domain.Persoana;
import ubb.scs.map.faptebune.service.Service;
import ubb.scs.map.faptebune.utils.events.PersoanaEntityChangeEvent;
import ubb.scs.map.faptebune.utils.observer.Observer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HelloController implements Observer<PersoanaEntityChangeEvent> {
    Long idUser;
    @FXML
    public Button button;
    public DatePicker textFieldDate;
    public TextArea textFieldDesc;
    public TextField textFieldTitlu;
    ObservableList<Nevoie> model = FXCollections.observableArrayList();
    Service service;
    @FXML
    public TableView<Nevoie> tableviewStanga;
    @FXML
    public TableView<Nevoie> tableviewDreapta;
    @FXML
    public TableColumn<Nevoie, String> tableCDreaptaTitlu;
    @FXML
    public TableColumn<Nevoie, String> tableCDreaptaDesc;
    @FXML
    public TableColumn<Nevoie, LocalDateTime> tableCDreaptaDeadline;
    @FXML
    public TableColumn<Nevoie, Long> tableCDreaptaInNevoie;
    @FXML
    public TableColumn<Nevoie, String> tableCDreaptaStatus;
    @FXML
    public TableColumn<Nevoie, String> tableCStangaTitlu;
    @FXML
    public TableColumn<Nevoie, String> tableCStangaDesc;
    @FXML
    public TableColumn<Nevoie, LocalDateTime> tableCStangaDeadline;
    @FXML
    public TableColumn<Nevoie, Long> tableCStangaInNevoie;
    @FXML
    public TableColumn<Nevoie, String> tableCStangaStatus;
    @FXML
    public TableColumn<Nevoie, Long> tableCStangaSalvator;

    public void initialize(){
    }

    @Override
    public void update(PersoanaEntityChangeEvent utilizatorEntityChangeEvent) {
        if(idUser==null){}
        else{
            initModel();
        }
    }

    private void initModel() {
        tableCStangaTitlu.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        tableCDreaptaTitlu.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        tableCStangaDesc.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        tableCDreaptaDesc.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        tableCDreaptaDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        tableCStangaDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        tableCDreaptaInNevoie.setCellValueFactory(new PropertyValueFactory<>("omInNevoie"));
        tableCDreaptaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableCStangaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableCStangaInNevoie.setCellValueFactory(new PropertyValueFactory<>("omInNevoie"));
        tableCStangaSalvator.setCellValueFactory(new PropertyValueFactory<>("omSalvator"));

        Optional<Persoana> Persoana = service.findOnePersoana(idUser);
        List<Nevoie> list1 = StreamSupport.stream(service.getAllNevoieFromCity(Persoana.get().getOras()).spliterator(), false)
                .filter(x->x.getOmInNevoie()!=idUser).collect(Collectors.toList());

        List<Nevoie> list2 = StreamSupport.stream(service.getAllNevoieSolvedByPerson(Persoana.get().getId()).spliterator(), false)
                .collect(Collectors.toList());

        List<Nevoie> allNevoie = StreamSupport.stream(service.getAllNevoie().spliterator(), false)
                .collect(Collectors.toList());

        ObservableList<Nevoie> model = FXCollections.observableArrayList(allNevoie);

        ObservableList<Nevoie> filteredList1 = FXCollections.observableArrayList(
                model.stream().filter(list1::contains).collect(Collectors.toList()));

        ObservableList<Nevoie> filteredList2 = FXCollections.observableArrayList(
                model.stream().filter(list2::contains).collect(Collectors.toList()));

        tableviewDreapta.setItems(filteredList2);
        tableviewStanga.setItems(filteredList1);
    }

    public void handleAccept(ActionEvent actionEvent) {
        tableviewStanga.getSelectionModel().getSelectedItem();
        if(tableviewStanga.getSelectionModel().getSelectedItem()!=null){
            Nevoie n = tableviewStanga.getSelectionModel().getSelectedItem();

            if(n.getOmSalvator()!=-1){
                MessageAlert.showMessage(null, Alert.AlertType.WARNING,"This entry has already been chosen!","Please select another entry.");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Accept entry with title " + n.getTitlu() +"?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                service.selectedNevoie(n,idUser);

            }
        }
        else{
            MessageAlert.showMessage(null, Alert.AlertType.WARNING,"No selection","Please select an entry.");
        }
    }

    public void handleSubmit(ActionEvent actionEvent) {
        String Titlu = textFieldTitlu.getText();
        String Desc=textFieldDesc.getText();
        LocalDateTime date= textFieldDate.getValue().atTime(0, 0);

        if(Titlu.isEmpty() || Desc.isEmpty() || date==null){
            MessageAlert.showMessage(null, Alert.AlertType.WARNING,"Empty fields","Please fill the required fields.");
            return;
        }

        Nevoie n= new Nevoie(service.getNextNevoieId(), Titlu, Desc, date, idUser);
        service.addNevoie(n);

        textFieldTitlu.clear();
        textFieldDesc.clear();


    }

    public void setService(Service service) {
        idUser=Session.getInstance().getSessionID();
        this.service=service;
        service.addObserver(this);
        initModel();
    }
}