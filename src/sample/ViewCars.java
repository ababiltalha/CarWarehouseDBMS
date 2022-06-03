package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.PrintWriter;


public class ViewCars {

    private Main main;

    @FXML
    private ChoiceBox searchChoice;

    @FXML
    private TableView table;
    @FXML
    private TableColumn<Car, String> regNo;
    @FXML
    private TableColumn<Car, String> carMake;
    @FXML
    private TableColumn<Car, String> carModel;
    @FXML
    private TableColumn<Car, String> color;
    @FXML
    private TableColumn<Car, String> year;
    @FXML
    private TableColumn<Car, String> price;

    @FXML
    private Button buy;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button search;
    @FXML
    private Button logout;
    @FXML
    private Button exit;
    @FXML
    private Button edit;
    @FXML
    private Button viewAll;
    @FXML
    private TextField searchByText;

    ObservableList<Car> carObservableList = FXCollections.observableArrayList();
    ObservableList<String> searchType= FXCollections.observableArrayList("Registration No","Make and Model");

    @FXML
    private void initialize() {
        searchChoice.setItems(searchType);
        searchChoice.setValue("Registration No");
    }



    public void viewList()
    {
        if (main.getAccountType().equals("Viewer"))
        {
            logout.setVisible(false);
            add.setVisible(false);
            delete.setVisible(false);
            edit.setVisible(false);
        }
        else
        {
            exit.setVisible(false);
            buy.setVisible(false);
        }

        regNo.setCellValueFactory(new PropertyValueFactory<Car, String>("regNo"));
        carMake.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
        carModel.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        color.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
        price.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));
        year.setCellValueFactory(new PropertyValueFactory<Car, String>("year"));


        carObservableList = Car.getList();
        table.setItems(carObservableList);

    }

    @FXML
    private void buyAction(ActionEvent click) {
        Car car = (Car) table.getSelectionModel().getSelectedItem();
        try {
            PrintWriter output = new PrintWriter(ClientControl.getInstance().getSocket().getOutputStream(), true);
            output.println("BUY-"+car.getRegNo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void addAction(ActionEvent click) {
        try {
            main.showAddCar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void deleteAction(ActionEvent click) {
        Car car = (Car) table.getSelectionModel().getSelectedItem();
        try {
            PrintWriter output = new PrintWriter(ClientControl.getInstance().getSocket().getOutputStream(), true);
            output.println("DELETE-"+car.getRegNo());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void searchAction(ActionEvent click) {
        ObservableList <Car> visibleCars = FXCollections.observableArrayList();
        if (searchChoice.getValue().equals("Registration No")) {
            String regNo = searchByText.getText();
            System.out.println(regNo);
            for (int i = 0; i < carObservableList.size(); i++) {
                if (carObservableList.get(i).getRegNo().equalsIgnoreCase(regNo)) {
                    visibleCars.add(carObservableList.get(i));
                    break;
                }
            }

        } else if (searchChoice.getValue().equals("Make and Model")) {
            String carDetails[] = searchByText.getText().split(",");
            for (int i = 0; i < carObservableList.size(); i++) {
                if (carObservableList.get(i).getMake().equalsIgnoreCase(carDetails[0])) {
                    if (carObservableList.get(i).getModel().equalsIgnoreCase(carDetails[1]) || carDetails[1].equalsIgnoreCase("ANY")) {
                        visibleCars.add(carObservableList.get(i));
                    }
                }
            }

        }
        table.setItems(visibleCars);
    }
    @FXML
    private void logoutAction(ActionEvent click)  {
        try {
            main.showHome();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void exitAction(ActionEvent click) {
        try {
            main.showHome();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void viewAllAction(ActionEvent click) {
        try {
            main.showViewCars();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void editAction(ActionEvent click){
        try {
            Car car = (Car) table.getSelectionModel().getSelectedItem();
            main.showAddCar(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main){
        this.main=main;
    }

}
