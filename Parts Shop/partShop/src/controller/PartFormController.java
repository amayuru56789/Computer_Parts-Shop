package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
import model.Employee;
import model.Part;
import model.ReturnPart;
import views.tm.PartTm;
import views.tm.ReturnPartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class PartFormController {
    public JFXTextField txtPartCode;
    public JFXTextField txtBrand;
    public JFXTextField txtDescription;
    public JFXTextField txtPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtCatergory;
    public TableView tblPart;
    public TableColumn colCode;
    public TableColumn colBrand;
    public TableColumn colDesc;
    public TableColumn colPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colCatergory;
    public Label lblTime;
    public Label lblDate;
    public AnchorPane PartFormContext;

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("partBrand"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        colCatergory.setCellValueFactory(new PropertyValueFactory<>("catergory"));
        loadDateAndTime();
        try {
            setPartToTable(new PartController().getAllPart());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setPartToTable(ArrayList<Part> part) {
        ObservableList<Part> obList = FXCollections.observableArrayList();
        part.forEach(e->{
            obList.add(
                    new PartTm(e.getPartCode(), e.getPartBrand(), e.getDescription(), e.getUnitPrice(), e.getQtyOnHand(), e.getCatergory()));
        });
        tblPart.setItems(obList);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, event -> {
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
        Stage window = (Stage) PartFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void savePartOnAction(ActionEvent actionEvent) {
        Part p1 = new Part(txtPartCode.getText(),txtBrand.getText(),
                txtDescription.getText(),Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),txtCatergory.getText()
        );
        try {
            if (savePart(p1)){
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

    private boolean savePart(Part p1) throws SQLException, ClassNotFoundException {
        return new PartController().savePart(p1);
    }

    public void updatePartOnAction(ActionEvent actionEvent) {
        Part p1 = new Part(
                txtPartCode.getText(),txtBrand.getText(),
                txtDescription.getText(),Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),txtCatergory.getText()
        );
        try {
            if (new PartController().updatePart(p1))
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Updated...").show();
            else
                new Alert(Alert.AlertType.WARNING,"Try again...").show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removePartOnAction(ActionEvent actionEvent) {

    }

    public void searchPartOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String partCode = txtPartCode.getText();
        Part p1 = new PartController().getPart(partCode);
        if (p1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();
        }else {
            setData(p1);
        }
    }

    private void setData(Part p1) {
        txtBrand.setText(p1.getPartBrand());
        txtDescription.setText(p1.getDescription());
        txtPrice.setText(String.valueOf(p1.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(p1.getQtyOnHand()));
        txtCatergory.setText(p1.getCatergory());
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
