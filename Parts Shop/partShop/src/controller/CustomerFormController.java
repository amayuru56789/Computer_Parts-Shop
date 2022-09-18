package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import model.Part;
import util.Validation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerFormController implements Initializable {
    public JFXTextField txtCustId;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustName;
    public JFXTextField txtCustNo;
    public JFXTextField txtSearch;
    public AnchorPane customerFormContext;
    public JFXButton btnSaveCustomer;
    public JFXButton btnUpdate;
    public JFXButton btnRemove;
    public JFXButton btnSave;
    public Label lblDate;

    LinkedHashMap<JFXTextField,Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(C)[0-9]{3,}$");
    Pattern namePattern = Pattern.compile("^[A-z]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/]{6,30}$");
    Pattern contactPattern = Pattern.compile("^[1-9][0-9]*([-][0-9]{2})?$");

    public void openAddCustomerForm(ActionEvent actionEvent) {

    }

    public void openDeleteCustomerForm(ActionEvent actionEvent) {

    }

    public void openUpdateCustomerForm(ActionEvent actionEvent) {

    }

    public void openSearchCustomerForm(ActionEvent actionEvent) {

    }

    public void openViewCustomerForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) customerFormContext.getScene().getWindow();
        window.setTitle("Customer details");
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void saveCustomerOnAction(ActionEvent actionEvent) {

        Customer c1 = new Customer(
                txtCustId.getText(),txtCustName.getText(),txtCustAddress.getText(),txtCustNo.getText()
        );
        try {
            if (addCustomer(c1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved...").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean addCustomer(Customer c1) throws SQLException, ClassNotFoundException {
        return new CustomerController().saveCustomer(c1);
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) {
        Customer c1 = new Customer(
                txtCustId.getText(),txtCustName.getText(),
                txtCustAddress.getText(),txtCustNo.getText()
        );
        try {
            if (new CustomerController().updateCustomer(c1))
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated...").show();
            else
                new Alert(Alert.AlertType.WARNING,"Try again...").show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeCustomerOnAction(ActionEvent actionEvent) {
        try {
            if (new CustomerController().deleteCustomer(txtCustId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       /* Customer c1 = new Customer();
        String customerId = c1.getCustomerId();
        customerId = txtCustId.getText();
        try {
            if (new CustomerController().deleteCustomer(customerId))
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted...").show();
            else
                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void custId_KeyPress(KeyEvent keyEvent) {
       /* String regEx = "^(C)[0-9]{3,}$";
        String custId = txtCustId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(custId).matches();
        System.out.println(matches);
        if (matches){
            txtCustId.setStyle("-fx-border-color: green");
            btnSaveCustomer.setDisable(false);
            btnUpdate.setDisable(false);
            btnRemove.setDisable(false);
        }else {
            txtCustId.setStyle("-fx-border-color: red");
            btnSaveCustomer.setDisable(true);
            btnUpdate.setDisable(true);
            btnRemove.setDisable(true);
        }
        if (keyEvent.getCode()== KeyCode.ENTER){
            if (matches){
                txtCustName.requestFocus();
            }
        }*/
    }
    public void backToHome(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) customerFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadDateAndTime();

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),customerFormContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);
        storeValidation();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
    }

    private void storeValidation() {
        map.put(txtCustId,idPattern);
        map.put(txtCustName,namePattern);
        map.put(txtCustAddress,addressPattern);
        map.put(txtCustNo,contactPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);
        /*String cusIdRegEx = "^(C)[0-9]{3,}$";
        String cusNameRegEx = "^[A-z]{3,20}$";
        String cusAddressRegEx = "^[A-z0-9/]{6,30}$";
        String cusContactRegEx = "^[1-9][0-9]*([-][0-9]{2})?$";

        Pattern idPattern = Pattern.compile(cusIdRegEx);
        Pattern namePattern = Pattern.compile(cusNameRegEx);
        Pattern addressPattern = Pattern.compile(cusAddressRegEx);
        Pattern contactPattern = Pattern.compile(cusContactRegEx);

        String typeCustomerId = txtCustId.getText();
        if(idPattern.matcher(typeCustomerId).matches()){
            txtCustId.setStyle("-fx-border-color: green");
            txtCustName.requestFocus();

            String typeCustomerName = txtCustName.getText();
            if (namePattern.matcher(typeCustomerName).matches()) {
                txtCustName.setStyle("-fx-border-color: green");
                txtCustAddress.requestFocus();

                String typeCustomerAddress = txtCustAddress.getText();
                if (addressPattern.matcher(typeCustomerAddress).matches()) {
                    txtCustAddress.setStyle("-fx-border-color: green");
                    txtCustNo.requestFocus();

                    String typedCustomerNo = txtCustNo.getText();
                    if (contactPattern.matcher(typedCustomerNo).matches()) {
                        txtCustNo.setStyle("-fx-border-color: green");


                    }else {
                        txtCustNo.setStyle("-fx-border-color: red");
                        txtCustNo.requestFocus();
                    }

                }else {
                    txtCustAddress.setStyle("-fx-border-color: red");
                    txtCustAddress.requestFocus();
                }

            }else {
                txtCustName.setStyle("-fx-border-color: red");
                txtCustName.requestFocus();
            }

        }else {
            txtCustId.setStyle("-fx-border-color: red");
            txtCustId.requestFocus();
        }*/

       /* String regEx = "^(C)[0-9]{3,}$";
        String custId = txtCustId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(custId).matches();
        System.out.println(matches);
        if (matches){
            txtCustId.setStyle("-fx-border-color: green");
            btnSaveCustomer.setDisable(false);
            btnUpdate.setDisable(false);
            btnRemove.setDisable(false);
        }else {
            txtCustId.setStyle("-fx-border-color: red");
            btnSaveCustomer.setDisable(true);
            btnUpdate.setDisable(true);
            btnRemove.setDisable(true);
        }
        if (keyEvent.getCode()== KeyCode.ENTER){
            if (matches){
                txtCustName.requestFocus();
            }
        }*/
        Object response = Validation.validate(map,btnSave,btnUpdate,btnRemove);
        if (keyEvent.getCode()==KeyCode.ENTER){
            if (response instanceof JFXTextField){
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            }
        }
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtSearch.getText();
        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(c1);
        }
    }

    private void setData(Customer c1) {
        txtCustId.setText(c1.getCustomerId());
        txtCustName.setText(c1.getCustomerName());
        txtCustAddress.setText(c1.getCustomerAddress());
        txtCustNo.setText(c1.getContactNo());
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
}
