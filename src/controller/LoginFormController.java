package controller;


import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginFormController {

    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPassword;


    public void lblCreateNewAccountOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("../view/CreateNewAccountForm.fxml"));

        Scene scene=new Scene(parent);

        Stage primaryStage= (Stage) root.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Register");


    }

    public void btnLoginOnAction(ActionEvent event) {

        String name = txtUserName.getText();
        String password = txtPassword.getText();

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where name =? and password = ?");
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Parent parent = FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml"));
                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) root.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.setTitle("DashBoard");
                primaryStage.centerOnScreen();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "User Name or Password does not matched");
                alert.showAndWait();
                txtUserName.clear();
                txtPassword.clear();
            }

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }



    }
}
