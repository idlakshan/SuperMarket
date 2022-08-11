package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Item;
import view.tm.ItemTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemFormController {


    public AnchorPane root;

    public TextField txtCode;
    public TableView<ItemTM> tblItem;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colQtyOnHand;
    public TableColumn colPrice;
    public TextField txtName;
    public TextField txtQtyOnHand;
    public TextField txtQtyPrice;

    public void initialize() throws SQLException {

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        loadAllItems();
    }

    public void btnSaveItemOnAction(ActionEvent event) throws SQLException {
        Item item = new Item(txtCode.getText(), txtName.getText(), Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtQtyPrice.getText()));

        if (saveItem(item)) {
            new Alert(Alert.AlertType.INFORMATION, "Saved..").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try again.").show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent event) {
    }

    public void btnUpdateItemOnAction(ActionEvent event) {
    }

    public void btnDeleteItemOnAction(ActionEvent event) {

    }


    boolean saveItem(Item i) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("insert into item values (?,?,?,?)");
        statement.setObject(1, i.getCode());
        statement.setObject(2, i.getName());
        statement.setObject(3, i.getQty());
        statement.setObject(4, i.getPrice());

        return statement.executeUpdate() > 0;

    }

    public void loadAllItems() throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("select * from item");
        ResultSet resultSet = statement.executeQuery();
        ObservableList<ItemTM> observableList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            observableList.add(new ItemTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
                  /*  resultSet.getString(3),
                    resultSet.getString(4)*/

            ));
        }
        tblItem.setItems(observableList);
    }


}
