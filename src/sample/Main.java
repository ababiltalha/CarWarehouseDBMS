package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    Stage stage;
    private String accountType;
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getAccountType(){
        return this.accountType;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        ClientControl.getInstance().setMain(this);
        showHome();
    }

    public void showHome() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }

    public void showLogin() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        Login controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }

    public void showViewCars() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewCars.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ViewCars controller = loader.getController();
        controller.setMain(this);
        controller.viewList();

        // Set the primary stage
        stage.setTitle("View Cars");
        stage.setScene(new Scene(root, 550, 550));
        stage.show();
    }

    public void showAddCar(Car car) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addCar.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddCar controller = loader.getController();
        controller.load(car);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Add Cars");
        stage.setScene(new Scene(root, 550, 550));
        stage.show();
    }

    public void showAddCar() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addCar.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddCar controller = loader.getController();
        controller.load();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Add Cars");
        stage.setScene(new Scene(root, 550, 550));
        stage.show();
    }


    public void showInvalidData(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Data");
        alert.setHeaderText("Incorrect Data");
        alert.setContentText("Please enter a number for the given field.");
        alert.showAndWait();
    }
    public void showInvalidLogin(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("Please enter correct username and password.");
        alert.showAndWait();
    }
    public void showInvalidAdd(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Data");
        alert.setHeaderText("Incorrect Data");
        alert.setContentText("Please enter unique details.");
        alert.showAndWait();
    }
    public void showInvalidDelete(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Data");
        alert.setHeaderText("Incorrect Data");
        alert.setContentText("Please enter correct details.");
        alert.showAndWait();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
