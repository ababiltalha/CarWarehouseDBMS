package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.io.PrintWriter;

public class HomeController {

    private Main main;
    ObservableList<String> accountType= FXCollections.observableArrayList("Manufacturer","Viewer");

    @FXML
    private Label welcomeLabel;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private Button confirmButton;

    @FXML
    private void initialize() {
        choiceBox.setItems(accountType);
    }

    @FXML
    private void confirm(ActionEvent click) {
        main.setAccountType((String) choiceBox.getValue());
        try {
            if (main.getAccountType()==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please choose an account type");
                alert.setContentText("You have not chosen an account type.");
                alert.showAndWait();
            }
            else if (main.getAccountType().equals("Manufacturer"))
                main.showLogin();
            else if (main.getAccountType().equals("Viewer")){
                try {
                    PrintWriter output = new PrintWriter(ClientControl.getInstance().getSocket().getOutputStream(), true);
                    output.println("LIST-");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                main.showViewCars();
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    void setMain(Main main) {
        this.main = main;
    }




}
