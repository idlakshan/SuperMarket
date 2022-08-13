package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Customer;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class PlaceOrderFormController {

    public Label lblDate;
    public Label lblTime;
    public ComboBox<String> cmbCusIds;
    public ComboBox<String> cmbItemIds;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTp;



    public void initialize(){
        loadDateAndTime();

        try {
            loadCustomerIds();
            loadItemCodes();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    private void loadItemCodes() throws SQLException {
        List<String> allItemCodes=new ItemController().getAllItemIds();
        cmbItemIds.getItems().addAll(allItemCodes);



    }

    private void loadCustomerIds() throws SQLException {
        List<String> allCustomerIds = new CustomerController().getAllCustomerIds();
        cmbCusIds.getItems().addAll(allCustomerIds);

    }

    private void loadDateAndTime() {
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));


        Timeline time=new Timeline(new KeyFrame(Duration.ZERO,event -> {
            LocalTime currentTime=LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : "+ currentTime.getMinute()+" : "+currentTime.getSecond()
            );
        }),
            new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


}
