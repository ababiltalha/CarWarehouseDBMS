package sample;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientControl extends Thread{
    private static ClientControl instance;
    private Main main;
    private Socket socket;
    private BufferedReader reader;

    private ClientControl() {
        try {
            socket = new Socket("localhost", 3600);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start();
    }

    public static ClientControl getInstance(){
        if( instance == null ){
            instance = new ClientControl();
        }
        return instance;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String response = reader.readLine();
                System.out.println(response);
                serverResponse(response);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void serverResponse(String str) {
        String[] response = str.split("-");
        if(response[0].equals("LOGIN")) {
            loginResponse(response[1]);
        } else if (response[0].equals("ADD")) {
            addResponse(response[1]);
        } else if(response[0].equals("DELETE")) {
            deleteResponse(response[1]);
        } else if(response[0].equals("BUY")) {
            buyResponse(response[1]);
        } else if(response[0].equals("LIST")) {
            listResponse(response[1]);
        }
    }

    private void listResponse(String listString) {
        Car.clearList();
        String[] str = listString.split("&");

        for(int i=0; i<str.length; i++){
            String[] car = str[i].split(",");
            Car.addCar(new Car(car[0], car[1], car[2], car[3], car[4], car[5], car[6], car[7]));
        }

    }

    public void loginResponse(String loginCheck) {
        if(loginCheck.equals("VALID")){
            try {
                PrintWriter output = new PrintWriter(ClientControl.getInstance().getSocket().getOutputStream(), true);
                output.println("LIST-");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.setAccountType("Manufacturer");
                        main.showViewCars();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showInvalidLogin();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public void addResponse(String addData) {
        String[] str = addData.split("&");
        if(str[0].equals("VALID")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(addData);
                        String[] car = str[1].split(",");
                        Car.addCar(new Car(car[0], car[1], car[2], car[3], car[4], car[5], car[6], car[7]));
                        main.setAccountType("Manufacturer");
                        main.showViewCars();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showInvalidAdd();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void deleteResponse(String deleteData) {
        String[] str = deleteData.split("&");
        if(str[0].equals("VALID")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Car.deleteCar(str[1]);
                        main.setAccountType("Manufacturer");
                        main.showViewCars();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showInvalidDelete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void buyResponse(String deleteData) {
        String[] str = deleteData.split("&");
        if(str[0].equals("VALID")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Car.deleteCar(str[1]);
                        main.setAccountType("Viewer");
                        main.showViewCars();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showInvalidDelete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public Socket getSocket(){
        return this.socket;
    }

    public void setMain(Main main){
        this.main=main;
    }

}
