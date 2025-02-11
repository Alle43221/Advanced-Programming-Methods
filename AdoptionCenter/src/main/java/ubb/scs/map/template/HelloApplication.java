package ubb.scs.map.template;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ubb.scs.map.template.controller.TableController;
import ubb.scs.map.template.domain.AdoptionCenter;
import ubb.scs.map.template.repository.RepoAnimal;
import ubb.scs.map.template.repository.RepoCenter;
import ubb.scs.map.template.repository.RepoRequest;
import ubb.scs.map.template.service.Service;

import java.io.IOException;


public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }
    Service service;
    @Override
    public void start(Stage stage) throws IOException {
        RepoAnimal repoA = new RepoAnimal("jdbc:postgresql://localhost:5432/adoption","postgres","dabaeuisale");
        RepoCenter repoC = new RepoCenter("jdbc:postgresql://localhost:5432/adoption","postgres","dabaeuisale");
        RepoRequest repoR = new RepoRequest("jdbc:postgresql://localhost:5432/adoption","postgres","dabaeuisale");

        service=new Service(repoA, repoC, repoR);

        for (AdoptionCenter i: service.getAllCenters()) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./Table.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            Stage centerStage = new Stage();
            centerStage.setTitle("Center:  " + i.getName());
            centerStage.setScene(scene);
            centerStage.setWidth(600);
            TableController staffController = fxmlLoader.getController();
            staffController.setService(service, centerStage, i.getId());
            centerStage.show();
        }

    }
}