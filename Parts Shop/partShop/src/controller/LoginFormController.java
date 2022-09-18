package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public JFXTextField txtUnamebox;
    public JFXPasswordField txtPnamebox;
    public AnchorPane LoginFormContext;

    public void openDashBoardForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) LoginFormContext.getScene().getWindow();
        window.setTitle("System Login");
        if (txtUnamebox.getText().equalsIgnoreCase("1") && txtPnamebox.getText().equalsIgnoreCase("1")){
            window.setScene(new Scene(load));
            window.centerOnScreen();
        }else {
            new Alert(Alert.AlertType.WARNING,"Username Or Password Incorrect").show();
        }
    }
}
