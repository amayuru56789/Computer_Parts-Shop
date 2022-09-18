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
import javafx.scene.control.Label;
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
import util.Validation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public JFXTextField txtEmpId;
    public JFXTextField txtEmpAddress;
    public JFXTextField txtEmpName;
    public JFXTextField txtEmpSalary;
    public JFXTextField txtEmpTele;
    public JFXTextField txtSearch;
    public AnchorPane employeeFormContext;
    public JFXButton btnSaveEmployee;
    public JFXButton btnUpdate;
    public JFXButton btnRemove;
    public JFXButton btnSave;
    public Label lblDate;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(E)[0-9]{3,}$");
    Pattern namePattern = Pattern.compile("^[A-z]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/]{6,30}$");
    Pattern salaryPattern = Pattern.compile("^[1-9][0-9]*([-][0-9]{2})?$");
    Pattern contactPattern = Pattern.compile("^[1-9][0-9]*([-][0-9]{2})?$");

    public void initialize(){

        loadDateAndTime();

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),employeeFormContext);
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
        map.put(txtEmpId,idPattern);
        map.put(txtEmpName,namePattern);
        map.put(txtEmpAddress,addressPattern);
        map.put(txtEmpSalary,salaryPattern);
        map.put(txtEmpTele,contactPattern);
    }

    public void saveEmployeeOnAction(ActionEvent actionEvent) {
        Employee e1 = new Employee(
                txtEmpId.getText(),txtEmpName.getText(),
                txtEmpAddress.getText(),Double.parseDouble(txtEmpSalary.getText()),
                txtEmpTele.getText()
        );
        try {
            if (addEmployee(e1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Saved...").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean addEmployee(Employee e1) throws SQLException, ClassNotFoundException {
        return new EmployeeController().saveEmployee(e1);
    }

    public void updateEmployeeOnAction(ActionEvent actionEvent) {
        Employee e1 = new Employee(
                txtEmpId.getText(),txtEmpName.getText(),
                txtEmpAddress.getText(),Double.parseDouble(txtEmpSalary.getText()),
                txtEmpTele.getText()
        );
        try {
            if (new EmployeeController().updateEmployee(e1))
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Updated...").show();
            else
                new Alert(Alert.AlertType.WARNING,"Try again...").show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeEmployeeOnAction(ActionEvent actionEvent) {

    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) employeeFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void openViewEmpForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/EmployeeDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) employeeFormContext.getScene().getWindow();
        window.setTitle("Employee details");
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

    public void searchEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String empId = txtSearch.getText();
        Employee e1 = new EmployeeController().getEmployee(empId);
        if (e1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(e1);
        }
    }

    private void setData(Employee e1) {
        txtEmpId.setText(e1.getEmpId());
        txtEmpName.setText(e1.getEmpName());
        txtEmpAddress.setText(e1.getEmpAddress());
        txtEmpSalary.setText(String.valueOf(e1.getEmpSalary()));
        txtEmpTele.setText(e1.getContact());
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
