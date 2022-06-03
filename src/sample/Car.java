package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Car {
    private String regNo;
    private String year;
    private String[] color= new String[3];
    private String make;
    private String model;
    private String price;

    private static ObservableList<Car> carList =  FXCollections.observableArrayList();


    Car(String regNo, String year, String color1, String color2, String color3, String make, String model, String price)
    {
        setMake(make);
        setColor(color1, color2, color3);
        setModel(model);
        setPrice(price);
        setRegNo(regNo);
        setYear(year);
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
            this.price = price;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public String[] getColors(){
        return color;
    }
    public String getColor() {
        return color[0]+","+color[1]+","+color[2];
    }
    public void setColor(String color1, String color2, String color3) {
        this.color[0] = color1;
        this.color[1] = color2;
        this.color[2] = color3;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
            this.year = year;
    }
    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }


    public static ObservableList getList(){
        return carList;
    }
    public static void addCar(Car car)
    {
        carList.add(car);
    }
    public static void deleteCar(String registrationNo)
    {
        for (int i=0; i<carList.size(); i++)
        {
            if (carList.get(i).getRegNo().equals(registrationNo))
                carList.remove(i);
        }
    }
    public static void clearList(){
        carList.clear();
    }


}
