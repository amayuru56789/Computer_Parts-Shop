package views.tm;

import model.Employee;

public class EmployeeTm extends Employee {
    private String employeeId;
    private String empName;
    private String address;
    private double salary;
    private String contact;

    public EmployeeTm() {
    }

    public EmployeeTm(String employeeId, String empName, String address, double salary, String contact) {
        this.setEmployeeId(employeeId);
        this.setEmpName(empName);
        this.setAddress(address);
        this.setSalary(salary);
        this.setContact(contact);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "EmployeeTm{" +
                "employeeId='" + employeeId + '\'' +
                ", empName='" + empName + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", contact='" + contact + '\'' +
                '}';
    }
}
