package ubb.scs.map.sem8map.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import ubb.scs.map.sem8map.domain.NotaDto;
import ubb.scs.map.sem8map.domain.Tema;
import ubb.scs.map.sem8map.service.Service;

import java.util.List;
import java.util.stream.Collectors;

public class NoteController {
    @FXML
    public TableView<NotaDto> tableView;

    @FXML
    public TableColumn<NotaDto, String> tableColumnStudent;
    @FXML
    public TableColumn<NotaDto, String> tableColumnTema;
    @FXML
    public TableColumn<NotaDto, Double> tableColumnNota;
    @FXML
    public TextField textFieldNume;
    @FXML
    public TextField textFieldTema;
    @FXML
    public TextField textFieldNota;
    @FXML
    public ComboBox<String> comboBoxTema;

    ObservableList<NotaDto> model= FXCollections.observableArrayList();
    List<String> comboBoxOptions;

    private Service noteService;

    public void setService(Service s){
        noteService = s;
        modelInit();
    }

    private List<NotaDto> getNoteDTO(){
        return noteService.findAllGrades()
                .stream()
                .map(g->new NotaDto(g.getStudent().getName(), g.getTema().getId(), g.getValue(), g.getProfesor()))
                .collect(Collectors.toList());
    }

    public void modelInit(){
        tableColumnStudent.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tableColumnTema.setCellValueFactory(new PropertyValueFactory<>("temaId"));
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));

        model.setAll(getNoteDTO());
        comboBoxOptions=noteService.findAllHomeWorks().stream()
                .map(Tema::getId)
                .collect(Collectors.toList());
        comboBoxOptions.add(0, null);

       comboBoxTema.getItems().setAll(comboBoxOptions);
       tableView.setItems(model);
    }

    @FXML
    public void handleFilterSelection(ActionEvent event) {
        handleFilter();
    }

    private void handleFilter() {
        String nume=textFieldNume.getText();
        String temaId=textFieldTema.getText();
        Double notaFromField= (textFieldNota.getText().isEmpty())? null:Double.parseDouble(textFieldNota.getText());

        String selectedTemaId=comboBoxTema.getSelectionModel().getSelectedItem();


         model.setAll(getNoteDTO().stream().filter(nota->
                            nota.getStudentName().startsWith(nume) &&
                            nota.getTemaId().startsWith(temaId) &&
                                    (notaFromField==null ||
                            nota.getNota()>notaFromField) &&
                                    (selectedTemaId==null || nota.getTemaId().equals(selectedTemaId))
                 ).collect(Collectors.toList()));
    }

    public void handleKeyFilter(KeyEvent keyEvent) {
        handleFilter();
    }
}
