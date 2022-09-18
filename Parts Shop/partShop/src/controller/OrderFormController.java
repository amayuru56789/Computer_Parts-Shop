package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Order;
import model.Part;
import model.PartDetail;
import views.tm.CartTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFormController {
    public AnchorPane orderFormContext;
    public Label lblTotal;
    public TableView<CartTm> tblOrder;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colDesc;
    public TableColumn colQTY;
    public Label lblOrderId;
    public JFXComboBox<String> cmbPartCode;
    public JFXTextField txtPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDesc;
    public JFXTextField txtBrand;
    public JFXTextField txtQTY;
    public JFXComboBox<String> cmbCustId;
    public JFXComboBox<String> cmbEmpId;
    public JFXTextField txtDescription;
    public JFXTextField txtCatergory;
    public TableColumn colTot;
    public Label lblDate;

    int RawForRemove = -1;

    public void initialize(){

        loadDateAndTime();
        setOrderId();

        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTot.setCellValueFactory(new PropertyValueFactory<>("tot"));

        try {
            loadCustomerId();
            loadPartId();
            loadEmployeeId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbPartCode.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setPartData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            RawForRemove = (int) newValue;
        });
    }

    private void setOrderId() {
        try {
            lblOrderId.setText(new OrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        /*Timeline time = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour()+" : "+currentTime.getMinute()+" : "+currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();*/
    }

    private void loadEmployeeId() throws SQLException, ClassNotFoundException {
        List<String> empId = new EmployeeController().getEmployeeId();
        cmbEmpId.getItems().addAll(empId);
    }

    private void setPartData(String partCode) throws SQLException, ClassNotFoundException {
        Part p1 = new PartController().getPart(partCode);
        if (p1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set...").show();
        }else {
            txtBrand.setText(p1.getPartBrand());
            txtDescription.setText(p1.getDescription());
            txtPrice.setText(String.valueOf(p1.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(p1.getQtyOnHand()));
            txtCatergory.setText(p1.getCatergory());
        }
    }

    private void loadPartId() throws SQLException, ClassNotFoundException {
        List<String> partId = new PartController().getPartId();
        cmbPartCode.getItems().addAll(partId);
    }

    private void loadCustomerId() throws SQLException, ClassNotFoundException {
        List<String> customerId = new CustomerController().getCustomerId();
        cmbCustId.getItems().addAll(customerId);
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) orderFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();

    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<PartDetail> part = new ArrayList<>();
        for (CartTm temp:obList
             ) {
            part.add(new PartDetail(
                    temp.getPartCode(),
                    temp.getUnitPrice(),
                    temp.getQty(),
                    temp.getDiscount(),
                    temp.getTot()
            ));
        }
        Order order = new Order(
                lblOrderId.getText(),
                lblDate.getText(),
                cmbCustId.getValue(),
                cmbEmpId.getValue(),
                part
        );
        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
            setOrderId();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try again..").show();
        }
    }

    ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double discount = Double.parseDouble(txtDesc.getText());
        int qtyForCustomer = Integer.parseInt(txtQTY.getText());
        double tot = qtyForCustomer * unitPrice;

        if (qtyOnHand<qtyForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CartTm otm = new CartTm(
                cmbPartCode.getValue(),
                description,
                unitPrice,
                qtyOnHand,
                discount,
                qtyForCustomer,
                tot
        );
        int raw = isExists(otm);
        if (raw==-1){
            obList.add(otm);
        }else {
            CartTm temp = obList.get(raw);
            CartTm newOtm = new CartTm(
                    temp.getPartCode(),
                    temp.getDescription(),
                    temp.getUnitPrice(),
                    temp.getQtyOnHand(),
                    temp.getDiscount(),
                    temp.getQty()+qtyForCustomer,
                    tot + temp.getTot()
            );
            obList.remove(raw);
            obList.add(newOtm);
        }

        tblOrder.setItems(obList);
        calculateCost();
    }

    private int isExists(CartTm otm){
        for (int i = 0; i < obList.size(); i++){
            if (otm.getPartCode().equals(obList.get(i).getPartCode())){
                return i;
            }
        }
        return -1;
    }
    public void calculateCost(){
        double tot = 0;
        for (CartTm temp:obList
             ) {
            tot+=temp.getTot();
        }
        lblTotal.setText(tot+" /=");
    }

    public void clearOnAction(ActionEvent actionEvent) {
        if (RawForRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a Raw").show();
        }else {
            obList.remove(RawForRemove);
            calculateCost();
            tblOrder.refresh();
        }
    }
}
