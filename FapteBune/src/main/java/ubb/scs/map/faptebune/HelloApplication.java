package ubb.scs.map.faptebune;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ubb.scs.map.faptebune.controller.AppStartController;
import ubb.scs.map.faptebune.domain.Nevoie;
import ubb.scs.map.faptebune.domain.Persoana;
import ubb.scs.map.faptebune.domain.validators.NevoieValidator;
import ubb.scs.map.faptebune.domain.validators.PersoanaValidator;
import ubb.scs.map.faptebune.domain.validators.Validator;
import ubb.scs.map.faptebune.repository.NevoieRepository;
import ubb.scs.map.faptebune.repository.PersoanaRepository;
import ubb.scs.map.faptebune.repository.Repository;
import ubb.scs.map.faptebune.service.Service;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Validator<Persoana> persoanaValidator = new PersoanaValidator();
        Validator<Nevoie> nevoieValidator = new NevoieValidator();
        PersoanaRepository repo=new PersoanaRepository(persoanaValidator, "./data/persoane.txt");
        NevoieRepository repo2=new NevoieRepository(nevoieValidator, "./data/nevoi.txt");
        Service service = new Service(repo, repo2);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("app-start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("FapteBune!");
        AppStartController controller = fxmlLoader.getController();
        controller.setPersoanaService(service);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}