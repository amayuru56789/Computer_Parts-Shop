package controller;

import db.DbConnection;
import model.Order;
import model.PartDetail;
import views.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1").executeQuery();
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId+1;
            if (tempId<9){
                return "O-00"+tempId;
            }else if (tempId<99){
                return "O-0"+tempId;
            }else {
                return "O-"+tempId;
            }
        }
        return "O-001";
    }

    public boolean placeOrder(Order order) {
            Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.prepareStatement("INSERT INTO `Order` VALUES (?,?,?,?)");
            stm.setObject(1,order.getOrderId());
            stm.setObject(2,order.getOrderDate());
            stm.setObject(3,order.getCustomerId());
            stm.setObject(4,order.getEmployeeId());

            if (stm.executeUpdate()>0){
                if (saveOrderDetail(order.getOrderId(), order.getPart())){
                    con.commit();
                    return true;
                }else {
                    con.rollback();
                    return false;
                }
            }else {
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    private boolean saveOrderDetail(String orderId, ArrayList<PartDetail> part) throws SQLException, ClassNotFoundException {
        for (PartDetail temp:part
             ) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `Order detail` VALUES (?,?,?,?,?,?)");
            stm.setObject(1,temp.getPartCode());
            stm.setObject(2,orderId);
            stm.setObject(3,temp.getUnitPrice());
            stm.setObject(4,temp.getQtyForSell());
            stm.setObject(5,temp.getDiscount());
            stm.setObject(6,temp.getTot());
            if (stm.executeUpdate()>0){
                if(updateQty(temp.getPartCode(),temp.getQtyForSell())){

                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }
    private boolean updateQty(String partCode,int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement("UPDATE Part SET qtyOnHand=(qtyOnHand-" + qty + ") WHERE partCode='" + partCode + "'");
        return stm.executeUpdate()>0;
    }

}
