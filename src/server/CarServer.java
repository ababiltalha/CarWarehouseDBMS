package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CarServer {

    private String regNo;
    private String year;
    private String[] color= new String[3];
    private String make;
    private String model;
    private String price;

    private static final ArrayList<server.CarServer> carList =  new ArrayList();


    CarServer(String regNo, String year, String color1, String color2, String color3, String make, String model, String price)
    {
        setMake(make);
        setColor(color1, color2, color3);
        setModel(model);
        setPrice(price);
        setRegNo(regNo);
        setYear(year);


    }
    CarServer(String line){
        String[] carInfo= line.split(",");
        setMake(carInfo[5]);
        setColor(carInfo[2],carInfo[3],carInfo[4]);
        setModel(carInfo[6]);
        setPrice(carInfo[7]);
        setRegNo(carInfo[0]);
        setYear(carInfo[1]);
    }

    public static ArrayList<CarServer> getList(){
        return carList;
    }
    public static void addCar(CarServer car)
    {
        carList.add(car);
    }
    public static void deleteCar(String registrationNo)
    {
        System.out.println(carList.size());
        for (int i=0; i<carList.size(); i++) {
            if (carList.get(i).getRegNo().equals(registrationNo)){
                carList.remove(i);
                break;
            }
        }
        System.out.println(carList.size());
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

    public String[] getColor() {
        return color;
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

    public String carListString(){
        String s= "";
        for (CarServer car : carList)
        {
            s= s+car.getRegNo()+","
                    +car.getYear()+","
                    +car.getColor()[0]+","
                    +car.getColor()[1]+","
                    +car.getColor()[2]+","
                    +car.getMake()+","
                    +car.getModel()+","
                    +car.getPrice()+"&";
        }
        return s;
    }


}