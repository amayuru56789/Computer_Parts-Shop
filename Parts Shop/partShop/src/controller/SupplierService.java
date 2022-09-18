package controller;

import model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierService {
    public boolean saveSupplier(Supplier s1) throws SQLException, ClassNotFoundException;
    public boolean updateSupplier(Supplier s1) throws SQLException, ClassNotFoundException;
    public boolean deleteSupplier(String supId) throws SQLException, ClassNotFoundException;
    public Supplier getSupplier(String supId) throws SQLException, ClassNotFoundException;
    public ArrayList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException;
}
