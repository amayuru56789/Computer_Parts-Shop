package controller;

import db.DbConnection;
import model.ReturnPart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnPartController implements ReturnPartService{
    @Override
    public boolean saveReturnPart(ReturnPart r1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("INSERT INTO `Return` VALUES (?,?,?,?)");
        stm.setObject(1,r1.getReturnId());
        stm.setObject(2,r1.getReturnDate());
        stm.setObject(3,r1.getPartCode());
        stm.setObject(4,r1.getWarrentyCard());
        return (stm.executeUpdate()>0);
    }

    @Override
    public boolean updateReturnPart(ReturnPart r1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Return` SET returnDate=? ,partCode=? ,warrenty_Card=? WHERE returnId=?");
        stm.setObject(1,r1.getReturnDate());
        stm.setObject(2,r1.getPartCode());
        stm.setObject(3,r1.getWarrentyCard());
        stm.setObject(4,r1.getReturnId());
        return stm.executeUpdate()>0;

    }

    @Override
    public boolean deleteReturnPart(ReturnPart r1) {
        return false;
    }

    @Override
    public ReturnPart getReturnPart(String returnId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM `Return` WHERE returnId=?");
        stm.setObject(1,returnId);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new ReturnPart(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<ReturnPart> getAllReturnPart() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Return`");
        ResultSet rst = stm.executeQuery();
        ArrayList<ReturnPart> returnPart = new ArrayList<>();
        while (rst.next()){
            returnPart.add(new ReturnPart(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return returnPart;
    }
}
