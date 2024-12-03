package ubb.scs.map.sem8map;

import ubb.scs.map.sem8map.controllers.NoteController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ubb.scs.map.sem8map.service.Service;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("note-viewer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        NoteController controller =fxmlLoader.getController();
        controller.setService(new Service());

        stage.setTitle("Note Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}