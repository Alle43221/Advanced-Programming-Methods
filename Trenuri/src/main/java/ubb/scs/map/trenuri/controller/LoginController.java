package ubb.scs.map.trenuri.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ubb.scs.map.trenuri.HelloApplication;
import ubb.scs.map.trenuri.service.Service;

public class LoginController {
    private Service service;
    @FXML
    private Button loginButton;
    private int users=0;

    public void setService(Service service) {
        this.service = service;
    }

    public void handleLogin(ActionEvent actionEvent){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./views/clientView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            ClientController clientController = fxmlLoader.getController();
            users++;
            clientController.setService(service, String.valueOf(users));
            stage.setTitle("CLIENT PAGE FOR USER " + String.valueOf(users));
            stage.setOnCloseRequest(new EventHandler<>() {
                @Override
                public void handle(WindowEvent event) {
                    service.removeCautare(String.valueOf(users));
                    service.notifyObservers();
                }
            });
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
