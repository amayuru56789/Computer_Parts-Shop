package controller;

import db.DbConnection;
import model.Customer;
import model.Part;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService{


    @Override
    public boolean saveCustomer(Customer c1) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Customer VALUES (?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c1.getCustomerId());
        stm.setObject(2,c1.getCustomerName());
        stm.setObject(3,c1.getCustomerAddress());
        stm.setObject(4,c1.getContactNo());
        return (stm.executeUpdate()>0);

    }

    @Override
    public boolean updateCustomer(Customer c1) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET custName=? ,custAddress=? ,contactNo=? WHERE custId=?");
        stm.setObject(1,c1.getCustomerName());
        stm.setObject(2,c1.getCustomerAddress());
        stm.setObject(3,c1.getContactNo());
        stm.setObject(4,c1.getCustomerId());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM Customer WHERE cusId='"+customerId+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Customer getCustomer(String customerId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Customer WHERE custId=?");
        stm.setObject(1,customerId);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> customer = new ArrayList<>();
        while (rst.next()){
            customer.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return customer;
    }

    public List<String> getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> id = new ArrayList<>();
        //String xr[] = new String[5];
        while (rst.next()){
            id.add(rst.getString(1));
        }
        return id;

    }
}
