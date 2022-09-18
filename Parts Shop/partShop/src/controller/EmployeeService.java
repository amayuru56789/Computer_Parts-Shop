package controller;

import model.Customer;
import model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeService {
    public boolean saveEmployee(Employee e1) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(Employee e1) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException;
    public Employee getEmployee(String empId) throws SQLException, ClassNotFoundException;
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;
}
