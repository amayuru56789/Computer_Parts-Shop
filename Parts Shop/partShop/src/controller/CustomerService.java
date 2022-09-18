package controller;

import db.DbConnection;
import model.Customer;
import model.Part;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 public interface CustomerService {

    public boolean saveCustomer(Customer c1) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(Customer c1) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;
    public Customer getCustomer(String customerId) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;

}
