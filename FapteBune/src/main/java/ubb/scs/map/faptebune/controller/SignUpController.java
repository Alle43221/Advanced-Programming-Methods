package ubb.scs.map.faptebune.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ubb.scs.map.faptebune.Session;
import ubb.scs.map.faptebune.domain.Orase;
import ubb.scs.map.faptebune.domain.Persoana;
import ubb.scs.map.faptebune.domain.validators.ValidationException;
import ubb.scs.map.faptebune.service.Service;

import java.util.Arrays;
import java.util.Optional;


public class SignUpController {

    @FXML
    public TextField textFieldFirstName;
    @FXML
    public TextField textFieldLastName;
    @FXML
    public ChoiceBox<String> boxOras;
    @FXML
    public TextField textFieldNrStrada;
    @FXML
    public TextField textFieldStrada;
    @FXML
    public TextField textFieldTelefon;
    @FXML
    public PasswordField textFieldConfirmPassword;
    @FXML
    public PasswordField textFieldPassword;
    @FXML
    public TextField textFieldUsername;
    @FXML

    private Service service;
    Stage dialogStage;

    @FXML
    public void initialize() {

    }

    public void setService(Service service, Stage stage) {
        this.service = service;
        this.dialogStage=stage;
        boxOras.getItems().addAll(Arrays.stream(Orase.values()).map(Enum::toString).toList());
    }

    public void saveUser(ActionEvent actionEvent) {
        String firstName=textFieldFirstName.getText();
        String lastName=textFieldLastName.getText();
        String nrStrada=textFieldNrStrada.getText();
        String strada=textFieldStrada.getText();
        String telefon=textFieldTelefon.getText();
        String confirmPassword=textFieldConfirmPassword.getText();
        String password=textFieldPassword.getText();
        String oras=boxOras.getValue();
        String username=textFieldUsername.getText();

        if(firstName.isEmpty() || lastName.isEmpty() || strada.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
            ||oras.isEmpty() || telefon.isEmpty() || nrStrada.isEmpty() || username.isEmpty()
        ) {
            MessageAlert.showMessage(null, Alert.AlertType.WARNING,"Empty fields","Please fill the required fields.");
            return;
        }

        if(confirmPassword.equals(password)) {
            Persoana m=new Persoana(service.getNextPersoanaId(), firstName, lastName, username, password,oras, strada, nrStrada, telefon);
            try {
                Optional<Persoana> r= this.service.addPersoana(m);
                if (r.isEmpty()){
                    r=this.service.findByCredentials(username);
                    MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Account created","You are now logged in your new account.");
                    Session.getInstance().setSessionID(r.get().getId());
                    dialogStage.close();
                }
                else{
                    MessageAlert.showMessage(null, Alert.AlertType.WARNING,"Account exists","An account already exists for this email.");
                }
            } catch (ValidationException e) {
                MessageAlert.showErrorMessage(null,e.getMessage());
            }
        }
        else{
            MessageAlert.showMessage(null, Alert.AlertType.WARNING,"","Passwords do not match");
        }
    }

    @FXML
    public void handleCancel(){
        dialogStage.close();
    }
}
