package ubb.scs.map.trenuri;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ubb.scs.map.trenuri.controller.LoginController;
import ubb.scs.map.trenuri.repo.CautariRepo;
import ubb.scs.map.trenuri.repo.CityRepo;
import ubb.scs.map.trenuri.repo.TrainStationRepo;
import ubb.scs.map.trenuri.service.Service;

import java.io.IOException;


public class HelloApplication extends Application {
    Service service;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.service = new Service(new CityRepo("jdbc:postgresql://localhost:5432/trenuri","postgres","dabaeuisale"),
                new TrainStationRepo("jdbc:postgresql://localhost:5432/trenuri","postgres","dabaeuisale"), new CautariRepo());

        primaryStage.setTitle("START PAGE");
        startView(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void startView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./views/loginView.fxml"));
        AnchorPane Layout = fxmlLoader.load();
        stage.setScene(new Scene(Layout));

        LoginController startController = fxmlLoader.getController();
        startController.setService(service);
    }
}