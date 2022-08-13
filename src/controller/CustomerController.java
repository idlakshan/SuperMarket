package controller;

import db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    public List<String> getAllCustomerIds() throws SQLException {
        ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement("select * from customer").executeQuery();
        List<String> ids=new ArrayList<>();
        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
