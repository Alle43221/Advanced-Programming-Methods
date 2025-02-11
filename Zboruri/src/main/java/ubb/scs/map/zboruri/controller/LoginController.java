package ubb.scs.map.zboruri.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ubb.scs.map.zboruri.HelloApplication;
import ubb.scs.map.zboruri.domain.Client;
import ubb.scs.map.zboruri.service.Service;


public class LoginController {
    private Service service;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;

    public void setService(Service service) {
        this.service = service;
        //initModel();
    }

    public void handleLogin(ActionEvent actionEvent){
        try{
            String username = usernameField.getText();
            Client c = service.getClientByUsername(username);
            if(c == null){
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./views/clientView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            ClientController clientController = fxmlLoader.getController();
            clientController.setService(service,c);
            stage.show();
            //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
