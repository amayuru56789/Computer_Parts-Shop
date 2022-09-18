package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.SignUp;

import java.sql.SQLException;

public class SignUpFormController {
    public JFXTextField txtUser;
    public JFXTextField txtMail;
    public JFXTextField txtContact;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConPassword;

    public void saveAdminOnAction(ActionEvent actionEvent) {
        SignUp s1 = new SignUp(
                txtUser.getText(),txtMail.getText(),txtContact.getText(),txtPassword.getText(),txtConPassword.getText()
        );
        try {
            if (addUser(s1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try again").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean addUser(SignUp s1) throws SQLException, ClassNotFoundException {
        return new SignUpController().saveUser(s1);
    }
}
