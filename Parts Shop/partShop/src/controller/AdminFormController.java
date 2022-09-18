package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminFormController {
    public Pane adminFormContext;
    public JFXTextField txtUser;
    public JFXPasswordField pwdField;

    public void openDashBoardForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) adminFormContext.getScene().getWindow();
        window.setTitle("Order Details");
        if (txtUser.getText().equalsIgnoreCase("amayuru5678") && pwdField.getText().equalsIgnoreCase("123")){
            window.setScene(new Scene(load));
            window.centerOnScreen();
        }else {
            new Alert(Alert.AlertType.WARNING,"User name or Password incorrect...").show();
        }
    }
}
