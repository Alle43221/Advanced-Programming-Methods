package ubb.scs.map.faptebune.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ubb.scs.map.faptebune.Session;
import ubb.scs.map.faptebune.domain.Orase;
import ubb.scs.map.faptebune.domain.Persoana;
import ubb.scs.map.faptebune.service.Service;


import java.util.Arrays;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class LogInController {
    @FXML
    public ChoiceBox<String> choicesUser;
    @FXML


    private Service service;
    Stage dialogStage;

    @FXML
    private void initialize() {

    }

    public void setService(Service service, Stage stage) {
        this.service = service;
        this.dialogStage=stage;
        choicesUser.getItems().addAll(StreamSupport.stream(service.getAllPersoane().spliterator(), false)
                .map(Persoana::getUsername).toArray(String[]::new));
    }

    @FXML
    public void handleLogIn(){
        String emailText=choicesUser.getValue();

        Optional<Persoana> utilizator1=service.findByCredentials(emailText);

        if(utilizator1.isPresent()){
            logInUser(utilizator1.get());
        }
        else{
            MessageAlert.showMessage(null, Alert.AlertType.WARNING,"Invalid User","Email and password don't match");
        }
    }

    private void logInUser(Persoana m)
    {
            Session session=Session.getInstance();
            Long id=session.getSessionID();
            session.setSessionID(m.getId());
            dialogStage.close();
    }

    @FXML
    public void handleCancel(){
        dialogStage.close();
    }
}
