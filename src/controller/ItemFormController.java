package controller;

import db.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

        tblItem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM newValue) {
                txtCode.setText(tblItem.getSelectionModel().getSelectedItem().getCode());
                txtName.setText(tblItem.getSelectionModel().getSelectedItem().getName());
                txtQtyOnHand.setText(String.valueOf(tblItem.getSelectionModel().getSelectedItem().getQty()));
                txtQtyPrice.setText(String.valueOf(tblItem.getSelectionModel().getSelectedItem().getPrice()));
            }

        });

    }

    public void btnSaveItemOnAction(ActionEvent event) throws SQLException {
        Item item = new Item(txtCode.getText(), txtName.getText(), Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtQtyPrice.getText()));

        if (saveItem(item)) {
            new Alert(Alert.AlertType.INFORMATION, "Saved..").show();
            loadAllItems();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try again.").show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent event) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("select * from item where code=?");
        statement.setObject(1, txtCode.getText());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            Item item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
            searchItem(item);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty results").show();
        }


    }

    public void btnUpdateItemOnAction(ActionEvent event) throws SQLException {

        Item item = new Item(
                txtCode.getText(), txtName.getText(), Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtQtyPrice.getText())

        );


        if (updateItem(item)) {
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            loadAllItems();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }


    }

    public void btnDeleteItemOnAction(ActionEvent event) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("delete from item where code=?");
        statement.setObject(1, txtCode.getText());

        if (statement.executeUpdate() > 0) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            loadAllItems();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try again").show();
        }

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

            ));
        }
        tblItem.setItems(observableList);
    }

    void searchItem(Item i) {
        txtCode.setText(i.getCode());
        txtName.setText(i.getName());
        txtQtyOnHand.setText(String.valueOf(i.getQty()));
        txtQtyPrice.setText(String.valueOf(i.getPrice()));

    }

    boolean updateItem(Item i) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("update item set name=?,qtyOnhand=?,unitPrice=? where code=?");
        statement.setObject(1, i.getName());
        statement.setObject(2, i.getQty());
        statement.setObject(3, i.getPrice());
        statement.setObject(4, i.getCode());

        return statement.executeUpdate() > 0;

    }

    public void clearTextFields() {
        txtCode.clear();
        txtName.clear();
        txtQtyOnHand.clear();
        txtQtyPrice.clear();

        txtCode.requestFocus();
    }


}
