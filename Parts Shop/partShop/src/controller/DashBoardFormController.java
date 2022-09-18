package controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
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

public class DashBoardFormController {
    public AnchorPane DashBoardFormContext;
    public ImageView order;
    public ImageView customer;
    public ImageView orderDetail;
    public ImageView employee;
    public ImageView Return;
    public ImageView item;
    public Label lblMenu;
    public Label lblDesc;

    public void openOrderForm(MouseEvent actionEvent) throws IOException {

        if(actionEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) actionEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "order":
                    URL resource = getClass().getResource("../views/OrderForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
                    window.setTitle("Place Order");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }

    }

    public void openCustomerForm(MouseEvent actionEvent) throws IOException {

        if(actionEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) actionEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()){
                case "customer" :
                    URL resource = getClass().getResource("../views/CustomerForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window =(Stage) DashBoardFormContext.getScene().getWindow();
                    window.setTitle("Manage Customer");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;

                    /*TranslateTransition tt = new TranslateTransition(Duration.millis(350), window.getScene().getRoot());
                    tt.setFromX(-window.getWidth());
                    tt.setToX(0);
                    tt.play();
*/
            }

        }
    }

    public void openAdminForm(MouseEvent actionEvent) throws IOException {

        if(actionEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) actionEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "admin":
                    URL resource = getClass().getResource("../views/AdminForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
                    window.setTitle("Administrator");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }

    }

   /* public void openOrderDetail(MouseEvent actionEvent) {

    }*/

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()){
                case "customer" :
                    lblMenu.setText("Manage Customer");
                    lblDesc.setText("Click to add, edit, delete, search or view Customer");
                    break;

                case "employee" :
                    lblMenu.setText("Manage Employee");
                    lblDesc.setText("Click to add, edit, delete, search or view Employee");
                    break;

                case "order" :
                    lblMenu.setText("Place Orders");
                    lblDesc.setText("Click here if you want to place a new order");
                    break;

                case "orderDetail" :
                    lblMenu.setText("Order Detail");
                    lblDesc.setText("Click if you want to read orders");
                    break;

                case "Return" :
                    lblMenu.setText("return Item");
                    lblDesc.setText("Click if you want to Read return");
                    break;

                case "admin" :
                    lblMenu.setText("Administrator");
                    lblDesc.setText("Click if you want to Login admin");
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

        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200),icon);
            scaleT.setToX(1);
            scaleT.setToX(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDesc.setText("Please select one of above main operations to proceed");
        }

    }

    public void openEmployeeForm(MouseEvent mouseEvent) throws IOException {

        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "employee":
                    URL resource = getClass().getResource("../views/EmployeeForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
                    window.setTitle("Manage Employee");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }

    }

    public void openOrderDetailForm(MouseEvent mouseEvent) throws IOException {

        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "orderDetail":
                    URL resource = getClass().getResource("../views/OrderDetailForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
                    window.setTitle("Order Details");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }

    }

    public void openReturnForm(MouseEvent mouseEvent) throws IOException {

        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            //Parent DashBoardFormContext = null;

            switch (icon.getId()) {
                case "Return":
                    URL resource = getClass().getResource("../views/ReturnPartForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
                    window.setTitle("Return Parts");
                    window.setScene(new Scene(load));
                    window.centerOnScreen();
                    break;
            }
        }


    }

    public void openSignUpForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/SignUpForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setTitle("Sign Up");
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }
}
