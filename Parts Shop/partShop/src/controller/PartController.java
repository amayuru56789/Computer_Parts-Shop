package controller;

import db.DbConnection;
import model.Part;
import model.ReturnPart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartController implements PartService {

    @Override
    public boolean savePart(Part p1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("INSERT INTO Part VALUES (?,?,?,?,?,?)");
        stm.setObject(1,p1.getPartCode());
        stm.setObject(2,p1.getPartBrand());
        stm.setObject(3,p1.getDescription());
        stm.setObject(4,p1.getUnitPrice());
        stm.setObject(5,p1.getQtyOnHand());
        stm.setObject(6,p1.getCatergory());
        return (stm.executeUpdate()>0);
    }

    @Override
    public boolean updatePart(Part p1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Part SET partBrand=? ,Description=? ,UnitPrice=? ,qtyOnHand=? ,catergory=? WHERE partCode=?");
        stm.setObject(1,p1.getPartBrand());
        stm.setObject(2,p1.getDescription());
        stm.setObject(3,p1.getUnitPrice());
        stm.setObject(4,p1.getQtyOnHand());
        stm.setObject(5,p1.getCatergory());
        stm.setObject(6,p1.getPartCode());
        return stm.executeUpdate()>0;

    }

    @Override
    public boolean deletePart(String id) {
        return false;
    }

    @Override
    public Part getPart(String partCode) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Part WHERE partCode=?");
        stm.setObject(1,partCode);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Part(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Part> getAllPart() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Part");
        ResultSet rst = stm.executeQuery();
        ArrayList<Part> part = new ArrayList<>();
        while (rst.next()){
            part.add(new Part(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)
            ));
        }
        return part;
    }
    public List<String> getPartId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Part").executeQuery();
        List<String> id = new ArrayList<>();
        while (rst.next()){
            id.add(rst.getString(1));
        }
        return id;
    }
}
