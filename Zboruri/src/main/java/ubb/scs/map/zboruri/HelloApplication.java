package ubb.scs.map.zboruri;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ubb.scs.map.zboruri.controller.LoginController;
import ubb.scs.map.zboruri.repo.ClientRepo;
import ubb.scs.map.zboruri.repo.FlightRepo;
import ubb.scs.map.zboruri.repo.TicketRepo;
import ubb.scs.map.zboruri.service.Service;

import java.io.IOException;

public class HelloApplication extends Application {
    Service service;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        this.service = new Service(new ClientRepo("jdbc:postgresql://localhost:5432/zboruri","postgres","dabaeuisale"),
                new FlightRepo("jdbc:postgresql://localhost:5432/zboruri","postgres","dabaeuisale"),
                new TicketRepo("jdbc:postgresql://localhost:5432/zboruri","postgres","dabaeuisale"));

        primaryStage.setTitle("START PAGE");
        startView(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void startView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./views/loginView.fxml"));
        AnchorPane Layout = fxmlLoader.load();
        stage.setScene(new Scene(Layout));

        LoginController startController = fxmlLoader.getController();
        startController.setService(service);
    }
}