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
import model.Part;
import views.tm.CustomerTm;
import views.tm.PartTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDetailFormController {

    public TableView tblCustomer;
    public TableColumn colCustId;
    public TableColumn colCustName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public AnchorPane customerDetailFormContext;
    public ImageView backBtn;
    public Label lblCost;

    public void initialize(){

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),customerDetailFormContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        colCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        try {
            setCustomerToTable(new CustomerController().getAllCustomer());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblCustomer.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int rawNo = (int) newValue;
            lblCost.setText(String.valueOf(rawNo));
        });

    }

    private void setCustomerToTable(ArrayList<Customer> customer) {
        ObservableList<Customer> obList = FXCollections.observableArrayList();
        customer.forEach(e->{
            obList.add(
                    new CustomerTm(e.getCustomerId(), e.getCustomerName(), e.getCustomerAddress(), e.getContactNo()));
        });
        tblCustomer.setItems(obList);
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "backBtn":
                    URL resource = getClass().getResource("../views/CustomerForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) customerDetailFormContext.getScene().getWindow();
                    window.setTitle("Manage Customer");
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
