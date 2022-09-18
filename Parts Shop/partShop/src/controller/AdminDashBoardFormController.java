package controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class AdminDashBoardFormController {
    public ImageView supplier;
    public ImageView item;
    public Label lblMenu;
    public Label lblDesc;
    public AnchorPane dashBoardFormContext;

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()){
                case "supplier" :
                    lblMenu.setText("Manage Supplier");
                    lblDesc.setText("Click to add, edit, delete, search or view Supplier");
                    break;
                case "item" :
                    lblMenu.setText("Manage Part");
                    lblDesc.setText("Click to add, edit, delete, search or view Part");
                    break;
            }
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200),icon);
            scaleT.setToX(1.2);
            scaleT.setToX(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.WHITE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(40);
            icon.setEffect(glow);
        }
    }

    public void playMouseExiteAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToX(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDesc.setText("Please select one of above main operations to proceed");
        }
    }

    public void openSupplierForm(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "supplier":
                    URL resource = getClass().getResource("../views/SupplierForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
                    window.setTitle("Manage Supplier");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }
    }

    public void openPartForm(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "item":
                    URL resource = getClass().getResource("../views/PartForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
                    window.setTitle("Manage Part");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }
    }
}
