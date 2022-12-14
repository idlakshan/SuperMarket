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
import model.Customer;
import view.tm.CustomerTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFormController {

    public AnchorPane root;
    public TextField txtCusCode;
    public TextField txtCusName;
    public TextField txtCusAddress;
    public TextField txtCusTp;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCusCode;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn colCusTp;

    public void initialize() throws SQLException {


        colCusCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCusTp.setCellValueFactory(new PropertyValueFactory<>("tp"));

        loadAllCustomers();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM newValue) {
                txtCusCode.setText(tblCustomer.getSelectionModel().getSelectedItem().getId());
                txtCusName.setText(tblCustomer.getSelectionModel().getSelectedItem().getName());
                txtCusAddress.setText(tblCustomer.getSelectionModel().getSelectedItem().getAddress());
                txtCusTp.setText(tblCustomer.getSelectionModel().getSelectedItem().getTp());
            }

        });


    }


    public void btnCustomerSaveOnAction(ActionEvent event) throws SQLException {

        Customer customer = new Customer(txtCusCode.getText(), txtCusName.getText(), txtCusAddress.getText(), txtCusTp.getText());

        if (saveCustomer(customer)) {
            new Alert(Alert.AlertType.INFORMATION, "Saved..").show();
            loadAllCustomers();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try again.").show();
        }

    }

    public void btnCustomerSearchOnAction(ActionEvent event) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("select * from customer where id=?");
        statement.setObject(1, txtCusCode.getText());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            Customer customer=new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            searchCustomer(customer);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty results").show();
        }

    }

    public void btnCustomerUpdateOnAction(ActionEvent event) throws SQLException {

     Customer customer=new Customer(
             txtCusCode.getText(),txtCusName.getText(),txtCusAddress.getText(),txtCusTp.getText()

     );


        if (updateCustomer(customer)) {
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            loadAllCustomers();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnCustomerDeleteOnAction(ActionEvent event) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("delete from customer where id=?");
        statement.setObject(1, txtCusCode.getText());

        if (statement.executeUpdate() > 0) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
            loadAllCustomers();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try again").show();
        }

    }




    boolean saveCustomer(Customer c) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("insert into customer values (?,?,?,?)");
        statement.setObject(1, c.getId());
        statement.setObject(2, c.getName());
        statement.setObject(3, c.getAddress());
        statement.setObject(4, c.getTp());

        return statement.executeUpdate() > 0;

    }

    void searchCustomer(Customer c){
        txtCusCode.setText(c.getId());
        txtCusName.setText(c.getName());
        txtCusAddress.setText(c.getAddress());
        txtCusTp.setText(c.getTp());

    }

    boolean updateCustomer(Customer c) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("update customer set name=?,address=?,tp=? where id=?");
        statement.setObject(1, c.getName());
        statement.setObject(2, c.getAddress());
        statement.setObject(3, c.getTp());
        statement.setObject(4, c.getId());

        return statement.executeUpdate() > 0;

    }

    public void loadAllCustomers() throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("select * from customer");
        ResultSet resultSet = statement.executeQuery();
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            observableList.add(new CustomerTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }
        tblCustomer.setItems(observableList);
    }

    public void clearTextFields() {
        txtCusCode.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusTp.clear();

        txtCusCode.requestFocus();
    }

}


