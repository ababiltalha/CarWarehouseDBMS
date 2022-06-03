package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;

public class AddCar {
    private Main main;
    private Car car;
    @FXML
    private TextField regNoText;
    @FXML
    private TextField makeText;
    @FXML
    private TextField modelText;
    @FXML
    private TextField colorsText1;
    @FXML
    private TextField colorsText2;
    @FXML
    private TextField colorsText3;
    @FXML
    private TextField yearText;
    @FXML
    private TextField priceText;

    @FXML
    private Button addButton;

    @FXML
    private Button saveButton;

    void load() {
        saveButton.setVisible(false);
        addButton.setVisible(true);
    }

    void load(Car car) {
        this.car = car;
        addButton.setVisible(false);
        saveButton.setVisible(true);
        regNoText.setText(car.getRegNo());
        makeText.setText(car.getMake());
        modelText.setText(car.getModel());
        colorsText1.setText(car.getColors()[0]);
        colorsText2.setText(car.getColors()[1]);
        colorsText3.setText(car.getColors()[2]);
        yearText.setText(car.getYear());
        priceText.setText(car.getPrice());
    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            main.showViewCars();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void addAction(ActionEvent event) {
        boolean flag = true;
        try{
            int temp = Integer.parseInt(yearText.getText());

        } catch (Exception e) {
            main.showInvalidData();
            flag = false;
        }

        try{
            int temp = Integer.parseInt(priceText.getText());

        } catch (Exception e) {
            main.showInvalidData();
        }
        if (flag){
            String s= regNoText.getText()+","+yearText.getText()+","+colorsText1.getText()+","+colorsText2.getText()+","+colorsText3.getText()+","+makeText.getText()+","+modelText.getText()+","+priceText.getText();
            try {
                PrintWriter output = new PrintWriter(ClientControl.getInstance().getSocket().getOutputStream(), true);
                output.println("ADD-"+s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            regNoText.clear();
            makeText.clear();
            modelText.clear();
            colorsText1.clear();
            colorsText2.clear();
            colorsText3.clear();
            yearText.clear();
            priceText.clear();

        }

    }

    public void saveAction(ActionEvent actionEvent) {
        boolean flag = true;
        try{
            int temp = Integer.parseInt(yearText.getText());
        } catch (Exception e) {
            main.showInvalidData();
            flag = false;
        }

        try{
            int temp = Integer.parseInt(priceText.getText());
        } catch (Exception e) {
            flag = false;
            main.showInvalidData();
        }
        if (flag){
            try {
                PrintWriter output = new PrintWriter(ClientControl.getInstance().getSocket().getOutputStream(), true);
                output.println("DELETE-"+car.getRegNo());
                String s= regNoText.getText()+","+yearText.getText()+","+colorsText1.getText()+","+colorsText2.getText()+","+colorsText3.getText()+","+makeText.getText()+","+modelText.getText()+","+priceText.getText()+"&";
                output.println("ADD-"+s);
            } catch (IOException e) {
                e.printStackTrace();
            }

            regNoText.clear();
            makeText.clear();
            modelText.clear();
            colorsText1.clear();
            colorsText2.clear();
            colorsText3.clear();
            yearText.clear();
            priceText.clear();

        }

    }
    void setMain(Main main) {
        this.main = main;
    }
}
