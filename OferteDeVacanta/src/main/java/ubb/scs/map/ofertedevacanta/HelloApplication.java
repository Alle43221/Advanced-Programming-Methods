package ubb.scs.map.ofertedevacanta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ubb.scs.map.ofertedevacanta.controller.ClientController;
import ubb.scs.map.ofertedevacanta.controller.MainController;
import ubb.scs.map.ofertedevacanta.controller.MessageAlert;
import ubb.scs.map.ofertedevacanta.domain.Client;
import ubb.scs.map.ofertedevacanta.events.CustomEvent;
import ubb.scs.map.ofertedevacanta.repo.*;
import ubb.scs.map.ofertedevacanta.service.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class HelloApplication extends Application {
    Service service;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.service = new Service(new LocationRepo("jdbc:postgresql://localhost:5432/oferte","postgres","dabaeuisale"),
                new HotelRepo("jdbc:postgresql://localhost:5432/oferte","postgres","dabaeuisale"),
                new OffRepo("jdbc:postgresql://localhost:5432/oferte","postgres","dabaeuisale"),
                new ClientRepo("jdbc:postgresql://localhost:5432/oferte","postgres","dabaeuisale"),
                new RezervareRepo("jdbc:postgresql://localhost:5432/oferte","postgres","dabaeuisale"));

        primaryStage.setTitle("START PAGE");
        startView(primaryStage);
        primaryStage.show();


        Parameters param = getParameters();
        List<String> list = param.getRaw();
        System.out.println(list.size());
        for(String clientId : list){
            Long id = Long.parseLong(clientId.replace("client:",""));
            System.out.println(id);

            getUsers(id);
        }
    }

    private void startView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./views/startView.fxml"));
        AnchorPane Layout = fxmlLoader.load();
        stage.setScene(new Scene(Layout));

        MainController mainController = fxmlLoader.getController();
        mainController.setService(this.service);
    }

    public void getUsers(Long clientId) {
        for (Client c : service.getClients()) {
            //System.out.println(c.getName());
            if (Objects.equals(clientId, c.getId())) {
                try {
                    Stage stageClient = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./views/clientView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());

                    stageClient.setTitle(c.getName());
                    stageClient.setScene(scene);
                    ClientController clientController = fxmlLoader.getController();
                    clientController.setService(service, clientId);

                    stageClient.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}