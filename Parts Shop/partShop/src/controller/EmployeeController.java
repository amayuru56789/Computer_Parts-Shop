package controller;

import db.DbConnection;
import model.Customer;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements EmployeeService{
    @Override
    public boolean saveEmployee(Employee e1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Employee VALUES (?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,e1.getEmpId());
        stm.setObject(2,e1.getEmpName());
        stm.setObject(3,e1.getEmpAddress());
        stm.setObject(4,e1.getEmpSalary());
        stm.setObject(5,e1.getContact());
        return (stm.executeUpdate()>0);
    }

    @Override
    public boolean updateEmployee(Employee e1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Employee SET empName=? ,empAddress=? ,empSalary=? ,contact=? WHERE empId=?");
        stm.setObject(1,e1.getEmpName());
        stm.setObject(2,e1.getEmpAddress());
        stm.setObject(3,e1.getEmpSalary());
        stm.setObject(4,e1.getContact());
        stm.setObject(5,e1.getEmpId());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM Employee WHERE empId='"+empId+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Employee getEmployee(String empId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Employee WHERE empId=?");
        stm.setObject(1,empId);
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee");
        ResultSet rst = stm.executeQuery();
        ArrayList<Employee> employee = new ArrayList<>();
        while (rst.next()){
            employee.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)
            ));
        }
        return employee;

    }

    public List<String> getEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Employee").executeQuery();
        List<String> id = new ArrayList<>();
        while (rst.next()){
            id.add(
                    rst.getString(1)
            );
        }
        return id;
    }
}
