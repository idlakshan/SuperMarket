package controller;


import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class CreateNewAccountFormController {

    public PasswordField txtNewPassword;
    public PasswordField txtConfirmPassword;
    public Label passwordLableOne;
    public Label passwordLableTwo;
    public TextField txtUserName;
    public TextField txtEmail;
    public Label lblUserId;
    public Label lblUserName;
    public Label lblEmail;
    public Label lblNewPassword;
    public Label lblConfirmPassword;
    public AnchorPane root;



    public void initialize() {
        setPasswordLabel(false);
        setDisableLabelAndTextField(true);


    }


    public void btnRegisterOnAction(ActionEvent event) {
        String txtConfirmPasswordText = txtConfirmPassword.getText();
        String txtNewPasswordText = txtNewPassword.getText();


        if (txtNewPasswordText.equals(txtConfirmPasswordText)) {

            register();
            setPasswordLabel(false);
            setBorderColour("transparent");
        } else {
            setBorderColour("red");

            setPasswordLabel(true);
            txtNewPassword.requestFocus();
        }
    }

    public void setBorderColour(String colour) {
        txtNewPassword.setStyle("-fx-border-color: " + colour);
        txtConfirmPassword.setStyle("-fx-border-color: " + colour);

    }

    public void setPasswordLabel(boolean isVisibility) {
        passwordLableOne.setVisible(isVisibility);
        passwordLableTwo.setVisible(isVisibility);

    }

    public void setDisableLabelAndTextField(Boolean isDisable) {
        lblUserName.setDisable(isDisable);
        lblEmail.setDisable(isDisable);
        lblNewPassword.setDisable(isDisable);
        lblConfirmPassword.setDisable(isDisable);
        txtUserName.setDisable(isDisable);
        txtEmail.setDisable(isDisable);
        txtNewPassword.setDisable(isDisable);
        txtConfirmPassword.setDisable(isDisable);
    }

     public void autoGenerateID() {

      Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select user_id from user order by user_id desc limit 1");

            boolean isExist = resultSet.next();

            if (isExist) {
                String userID = resultSet.getString(1);
                userID = userID.substring(1,userID.length());

                int intID = Integer.parseInt(userID);

                intID++;

                if (intID < 10) {
                    lblUserId.setText("U00" + intID);
                } else if (intID < 100) {
                    lblUserId.setText("U0" + intID);
                } else {
                    lblUserId.setText("U" + intID);
                }
            } else {
                lblUserId.setText("U001");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    

    public void btnAddUserOnAction(ActionEvent event) {
        setDisableLabelAndTextField(false);
         autoGenerateID();

    }
    public void register(){
        String id = lblUserId.getText();
        String name = txtUserName.getText();
        String email = txtEmail.getText();
        String confirmPassword = txtConfirmPassword.getText();

        Connection connection=DBConnection.getInstance().getConnection();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement("insert into user values (?,?,?,?) ");
            prepareStatement.setObject(1,id);
            prepareStatement.setObject(2,name);
            prepareStatement.setObject(3,email);
            prepareStatement.setObject(4,confirmPassword);

            prepareStatement.executeUpdate();

            Parent parent= FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
            Scene scene=new Scene(parent);

            Stage primaryStage=(Stage)root.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.centerOnScreen();

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }


    }
}
