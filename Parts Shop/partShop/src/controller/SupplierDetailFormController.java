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
import model.Employee;
import model.Supplier;
import views.tm.EmployeeTm;
import views.tm.SupplierTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDetailFormController {
    public TableView tblSupplier;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public AnchorPane supDetailFormContext;
    public ImageView backBtn;
    public Label lblCost;

    public void initialize(){

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),supDetailFormContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        try {
            setSupplierToTable(new SupplierController().getAllSupplier());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblSupplier.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int rawNo = (int) newValue;
            lblCost.setText(String.valueOf(rawNo));
        });

    }

    private void setSupplierToTable(ArrayList<Supplier> supplier) {
        ObservableList<Supplier> obList = FXCollections.observableArrayList();
        supplier.forEach(e->{
            obList.add(
                    new SupplierTm(e.getSupId(), e.getSupName(), e.getAddress(), e.getContact()));
        });
        tblSupplier.setItems(obList);
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

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "backBtn":
                    URL resource = getClass().getResource("../views/SupplierForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) supDetailFormContext.getScene().getWindow();
                    window.setTitle("Manage Employee");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }
    }
}
