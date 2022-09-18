package controller;

import model.ReturnPart;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReturnPartService {
    public boolean saveReturnPart(ReturnPart r1) throws SQLException, ClassNotFoundException;
    public boolean updateReturnPart(ReturnPart r1) throws SQLException, ClassNotFoundException;
    public boolean deleteReturnPart(ReturnPart r1);
    public ReturnPart getReturnPart(String returnId) throws SQLException, ClassNotFoundException;
    public ArrayList<ReturnPart> getAllReturnPart() throws SQLException, ClassNotFoundException;
}
