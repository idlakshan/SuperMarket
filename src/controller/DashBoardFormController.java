package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {

    public AnchorPane root;
    public ToggleGroup GOne;


    public void initialize() throws IOException {
        CustomerManage();
    }

   public void CustomerManage() throws IOException {
       URL resource = getClass().getResource("../view/CustomerForm.fxml");
       Parent load = FXMLLoader.load(resource);
       root.getChildren().clear();
       root.getChildren().add(load);
    }


    public void btnItemManage(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/ItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);

    }



    public void btnPlaceOrder(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../view/PlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);

    }

    public void btnCustomerManage(ActionEvent event) throws IOException {
        CustomerManage();
    }
}
