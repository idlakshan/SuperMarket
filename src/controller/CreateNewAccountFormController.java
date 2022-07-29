package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class CreateNewAccountFormController {

    public PasswordField txtNewPassword;
    public PasswordField txtConfirmPassword;
    public Label passwordLableOne;
    public Label passwordLableTwo;

    public void initialize() {
        setPasswordLabel(false);

    }


    public void btnRegisterOnAction(ActionEvent event) {
        String txtConfirmPasswordText = txtConfirmPassword.getText();
        String txtNewPasswordText = txtNewPassword.getText();

        if (txtNewPasswordText.equals(txtConfirmPasswordText)) {


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
}
