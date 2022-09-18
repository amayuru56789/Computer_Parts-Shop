package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import model.Part;
import model.ReturnPart;
import views.tm.ReturnPartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ReturnPartFormController {
    public AnchorPane returnFormContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtReturnId;
    public JFXTextField txtReturnDate;
    public JFXTextField txtPartCode;
    public JFXTextField txtWarrentyCard;
    public TableView tblReturn;
    public TableColumn colId;
    public TableColumn colDate;
    public TableColumn colPartCode;
    public TableColumn colWarrenty;

    public void initialize(){

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000),returnFormContext);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        colId.setCellValueFactory(new PropertyValueFactory<>("returnId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colPartCode.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        colWarrenty.setCellValueFactory(new PropertyValueFactory<>("warrentyCard"));
        loadDateAndTime();
        try {
            setReturnPartToTable(new ReturnPartController().getAllReturnPart());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setReturnPartToTable(ArrayList<ReturnPart> returnPart){
        ObservableList<ReturnPart> obList = FXCollections.observableArrayList();
        returnPart.forEach(e->{
            obList.add(
                    new ReturnPartTM(e.getReturnId(), e.getReturnDate(), e.getPartCode(), e.getWarrentyCard()));
        });
        tblReturn.setItems(obList);
    }
    private void loadDateAndTime() {

        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO,event -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour()+" : "+currentTime.getMinute()+" : "+currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) returnFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();

    }

    public void saveReturnPartOnAction(ActionEvent actionEvent) {
        ReturnPart r1 = new ReturnPart(
                txtReturnId.getText(),txtReturnDate.getText(),
                txtPartCode.getText(),txtWarrentyCard.getText()
        );
        try {
            if (addReturnPart(r1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Return part Saved...").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean addReturnPart(ReturnPart r1) throws SQLException, ClassNotFoundException {
        return new ReturnPartController().saveReturnPart(r1);
    }

    public void updateReturnPartOnAction(ActionEvent actionEvent) {
        ReturnPart r1 = new ReturnPart(txtReturnId.getText(),txtReturnDate.getText(),
                txtPartCode.getText(),txtWarrentyCard.getText()
        );
        try {
            if (new ReturnPartController().updateReturnPart(r1))
                new Alert(Alert.AlertType.CONFIRMATION,"Return part Updated...").show();
            else
                new Alert(Alert.AlertType.WARNING,"Try again...").show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeReturnPartOnAction(ActionEvent actionEvent) {

    }

    public void searchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String returnId = txtReturnId.getText();
        ReturnPart r1 = new ReturnPartController().getReturnPart(returnId);
        if (r1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...").show();
        }else {
            setData(r1);
        }
    }

    private void setData(ReturnPart r1) {
        txtReturnDate.setText(r1.getReturnDate());
        txtPartCode.setText(r1.getPartCode());
        txtWarrentyCard.setText(r1.getWarrentyCard());
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
