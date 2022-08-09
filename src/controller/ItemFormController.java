package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.tm.ItemTM;

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

    public void btnSaveItemOnAction(ActionEvent event) {
    }

    public void btnSearchItemOnAction(ActionEvent event) {
    }

    public void btnUpdateItemOnAction(ActionEvent event) {
    }

    public void btnDeleteItemOnAction(ActionEvent event) {

    }
}
