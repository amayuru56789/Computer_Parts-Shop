package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Employee;
import model.Supplier;
import util.Validation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SupplierFormController {
    public JFXTextField txtSupId;
    public JFXTextField txtSupName;
    public JFXTextField txtSupAddress;
    public JFXTextField txtContact;
    public AnchorPane supFormContext;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnRemove;
    public JFXTextField txtSearch;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(S)[0-9]{3,}$");
    Pattern namePattern = Pattern.compile("^[A-z]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/]{6,30}$");
    Pattern contactPattern = Pattern.compile("^[1-9][0-9]*([-][0-9]{2})?$");

    public void initialize(){

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),supFormContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);
        storeValidation();

    }

    private void storeValidation() {
        map.put(txtSupId,idPattern);
        map.put(txtSupName,namePattern);
        map.put(txtSupAddress,addressPattern);
        map.put(txtContact,contactPattern);
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) supFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);

        Object response = Validation.validate(map,btnSave,btnUpdate,btnRemove);
        if (keyEvent.getCode()== KeyCode.ENTER){
            if (response instanceof JFXTextField){
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            }
        }
    }

    public void saveSupplierOnAction(ActionEvent actionEvent) {
        Supplier s1 = new Supplier(
                txtSupId.getText(),txtSupName.getText(),
                txtSupAddress.getText(),txtContact.getText()
        );
        try {
            if (addSupplier(s1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Saved...").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean addSupplier(Supplier s1) throws SQLException, ClassNotFoundException {
        return new SupplierController().saveSupplier(s1);
    }

    public void updateSupplierOnAction(ActionEvent actionEvent) {
        Supplier s1 = new Supplier(
                txtSupId.getText(),txtSupName.getText(),
                txtSupAddress.getText(),txtContact.getText()
        );
        try {
            if (new SupplierController().updateSupplier(s1))
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated...").show();
            else
                new Alert(Alert.AlertType.WARNING,"Try again...").show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeSupplierOnAction(ActionEvent actionEvent) {

    }

    public void supId_KeyPressed(KeyEvent keyEvent) {

    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();


            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.BLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(40);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
        }
    }

    public void openViewEmpForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/SupplierDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) supFormContext.getScene().getWindow();
        window.setTitle("Supplier details");
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void searchEmpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String supId = txtSearch.getText();
        Supplier s1 = new SupplierController().getSupplier(supId);
        if (s1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(s1);
        }
    }

    private void setData(Supplier s1) {
        txtSupId.setText(s1.getSupId());
        txtSupName.setText(s1.getSupName());
        txtSupAddress.setText(s1.getAddress());
        txtContact.setText(s1.getContact());
    }
}
