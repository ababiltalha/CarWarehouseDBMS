package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;


public class Login {

    private Main main;

    @FXML
    private Button confirmLogin;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button resetLogin;

    @FXML
    private void confirm(ActionEvent click){

        String userName = usernameField.getText();
        String password = passwordField.getText();

        try {
            PrintWriter output = new PrintWriter(ClientControl.getInstance().getSocket().getOutputStream(), true);
            output.println("LOGIN-"+userName+" "+password);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void reset(ActionEvent click){
        usernameField.setText(null);
        passwordField.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }


}
