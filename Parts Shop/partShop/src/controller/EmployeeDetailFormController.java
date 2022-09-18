package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Employee;
import views.tm.CustomerTm;
import views.tm.EmployeeTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDetailFormController {
    public TableView tblEmployee;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colContact;
    public AnchorPane employeeFormContext;
    public ImageView backBtn;
    public Label lblCost;

    public void initialize(){

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),employeeFormContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        try {
            setEmployeeToTable(new EmployeeController().getAllEmployee());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblEmployee.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int rawNo = (int) newValue;
            lblCost.setText(String.valueOf(rawNo));
        });

    }

    private void setEmployeeToTable(ArrayList<Employee> employee) {
        ObservableList<Employee> obList = FXCollections.observableArrayList();
        employee.forEach(e->{
            obList.add(
                    new EmployeeTm(e.getEmpId(), e.getEmpName(), e.getEmpAddress(), e.getEmpSalary(),e.getContact()));
        });
        tblEmployee.setItems(obList);
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "backBtn":
                    URL resource = getClass().getResource("../views/EmployeeForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) employeeFormContext.getScene().getWindow();
                    window.setTitle("Manage Employee");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }
    }

    public void print(MouseEvent mouseEvent) {

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
