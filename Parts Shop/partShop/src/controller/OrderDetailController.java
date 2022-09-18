package controller;

import db.DbConnection;
import model.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailController {

    public ArrayList<OrderDetail> getAllOrderDetail() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select o.orderId,o.orderDate,o.custId,od.unitPrice,od.qty,od.total from `order` o inner join `order detail` od on o.orderId = od.OrderId;");
        ResultSet rst = stm.executeQuery();
        ArrayList<OrderDetail> detail = new ArrayList<>();
        while (rst.next()){
            detail.add(new OrderDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            ));
        }
        return detail;
    }

}
