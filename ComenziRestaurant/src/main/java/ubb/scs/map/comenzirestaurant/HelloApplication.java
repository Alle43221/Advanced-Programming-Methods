package ubb.scs.map.comenzirestaurant;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ubb.scs.map.comenzirestaurant.controller.StaffController;
import ubb.scs.map.comenzirestaurant.controller.TableController;
import ubb.scs.map.comenzirestaurant.domeniu.Angajat;
import ubb.scs.map.comenzirestaurant.domeniu.MyMenuItem;
import ubb.scs.map.comenzirestaurant.domeniu.Order;
import ubb.scs.map.comenzirestaurant.domeniu.Table;
import ubb.scs.map.comenzirestaurant.repository.*;
import ubb.scs.map.comenzirestaurant.service.Service;

import java.io.IOException;


public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }
    Service service;
    @Override
    public void start(Stage stage) throws IOException {
        Repository<Long, Table> tableRepo = new DBTable();
        Repository<Long, Angajat>angajatRepo=new DBStaff();
        Repository<Long, MyMenuItem>menuItemRepo=new DBMenu(tableRepo);
        Repository<Long, Order>orderRepo=new DBComanda(tableRepo);
        service=new Service(angajatRepo,menuItemRepo,tableRepo,orderRepo);

        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./views/Staff.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            Stage staffStage = new Stage();
            staffStage.setTitle("Staff");
            staffStage.setScene(scene);
            staffStage.setWidth(600);
            StaffController staffController = fxmlLoader.getController();
            staffController.setService(service, staffStage);
            staffStage.show();
        }
        for (Table table : service.getTables()) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("./views/Table.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            Stage staffStage = new Stage();
            staffStage.setTitle("Table:  " + table.getId());
            staffStage.setScene(scene);
            staffStage.setWidth(600);
            TableController staffController = fxmlLoader.getController();
            staffController.setService(service, staffStage, table);
            staffStage.show();
        }

    }
}