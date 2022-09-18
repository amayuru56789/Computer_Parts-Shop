package controller;

import db.DbConnection;
import model.Employee;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierController implements SupplierService{
    @Override
    public boolean saveSupplier(Supplier s1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Supplier VALUES (?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,s1.getSupId());
        stm.setObject(2,s1.getSupName());
        stm.setObject(3,s1.getAddress());
        stm.setObject(4,s1.getContact());
        return (stm.executeUpdate()>0);
    }

    @Override
    public boolean updateSupplier(Supplier s1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Supplier SET supName=? ,Address=? ,contact=? WHERE supId=?");
        stm.setObject(1,s1.getSupName());
        stm.setObject(2,s1.getAddress());
        stm.setObject(3,s1.getContact());
        stm.setObject(4,s1.getSupId());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteSupplier(String supId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM Supplier WHERE suppId='"+supId+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Supplier getSupplier(String supId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Supplier WHERE supId=?");
        stm.setObject(1,supId);
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            return new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier");
        ResultSet rst = stm.executeQuery();
        ArrayList<Supplier> supplier = new ArrayList<>();
        while (rst.next()){
            supplier.add(new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return supplier;
    }
}
