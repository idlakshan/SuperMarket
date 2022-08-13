package controller;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    public List<String> getAllItemIds() throws SQLException {
        ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement("select * from item").executeQuery();
        List<String> ids=new ArrayList<>();
        while (resultSet.next()){
            ids.add(
                    resultSet.getString(1)
            );

        }
        return ids;
    }
}
