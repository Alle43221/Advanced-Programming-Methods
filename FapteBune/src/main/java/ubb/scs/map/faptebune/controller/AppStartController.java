package ubb.scs.map.faptebune.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ubb.scs.map.faptebune.HelloApplication;
import ubb.scs.map.faptebune.HelloController;
import ubb.scs.map.faptebune.Session;
import ubb.scs.map.faptebune.domain.Persoana;
import ubb.scs.map.faptebune.service.Service;
import ubb.scs.map.faptebune.utils.events.PersoanaEntityChangeEvent;
import ubb.scs.map.faptebune.utils.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class AppStartController implements Observer<PersoanaEntityChangeEvent> {
    Service service;
    ObservableList<Persoana> model = FXCollections.observableArrayList();

    @FXML
    Button buttonLogIn, buttonSignUp;

    public void setPersoanaService(Service service) {
        this.service = service;
        service.addObserver(this);
        initModel();
        Session session=Session.getInstance();
        session.setSessionID(null);
    }

    @FXML
    public void initialize() {
    }

    private void initModel() {
        Iterable<Persoana> messages = service.getAllPersoane();
        List<Persoana> users = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(users);
    }

    @Override
    public void update(PersoanaEntityChangeEvent PersoanaEntityChangeEvent) {
        initModel();
    }

    public void showMessageTaskEditDialog(String title, String resource) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(resource));

            AnchorPane root = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            if(Objects.equals(title, "LogIn")){
                LogInController logInController = loader.getController();
                logInController.setService(service, dialogStage);
            }
            else{
                SignUpController logInController = loader.getController();
                logInController.setService(service, dialogStage);
            }

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToUserApp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../hello-view.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("FapteBune");
        stage.initModality(Modality.WINDOW_MODAL);

        HelloController controller = loader.getController();
        controller.setService(service);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void handleLogIn(ActionEvent actionEvent) throws IOException {
        showMessageTaskEditDialog( "LogIn", "../login-user.fxml");
        Session session=Session.getInstance();

        if(session.getSessionID()!=null)
        {
            goToUserApp();
        }
    }

    public void handleSignUp(ActionEvent actionEvent) throws IOException {
        showMessageTaskEditDialog("SignUp", "../save-user.fxml");
        Session session=Session.getInstance();
        if(session.getSessionID()!=null) {
            goToUserApp();
        }
    }
}
