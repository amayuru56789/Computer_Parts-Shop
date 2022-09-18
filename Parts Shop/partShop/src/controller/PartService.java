package controller;

import model.Part;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartService {
    public boolean savePart(Part p1) throws SQLException, ClassNotFoundException;
    public boolean updatePart(Part p1) throws SQLException, ClassNotFoundException;
    public boolean deletePart(String id);
    public Part getPart(String partCode) throws SQLException, ClassNotFoundException;
    public ArrayList<Part> getAllPart() throws SQLException, ClassNotFoundException;
}
