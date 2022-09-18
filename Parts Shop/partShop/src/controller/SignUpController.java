package controller;

import db.DbConnection;
import model.Part;
import model.SignUp;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {
    public boolean saveUser(SignUp s1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO SignUp VALUES (?,?,?,?,?)");
        stm.setObject(1,s1.getUserName());
        stm.setObject(2,s1.getGmail());
        stm.setObject(3,s1.getContact());
        stm.setObject(4,s1.getPassword());
        stm.setObject(5,s1.getConPass());
        return (stm.executeUpdate()>0);
    }
}
