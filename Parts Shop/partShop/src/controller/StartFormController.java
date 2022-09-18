package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;

public class StartFormController {
    public AnchorPane StartFormContext;

    public void openDashBoardForm(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) StartFormContext.getScene().getWindow();
        window.setTitle("Login");
        window.setScene(new Scene(load));
        window.centerOnScreen();

    }
}
