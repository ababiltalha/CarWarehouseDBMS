package server;


import sample.Car;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerControl  extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private static List<Socket> clientList = new ArrayList();

    public ServerControl(Socket socket) {
        try {
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            clientList.add(this.socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            while (true) {
                String request = reader.readLine();
                checkRequests(request);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkRequests(String str) {
        String[] response = str.split("-");
        if(response[0].equals("LOGIN")) {
            checkCred(response[1]);
        } else if (response[0].equals("ADD")) {
            checkAdd(response[1]);
        } else if(response[0].equals("DELETE") || response[0].equals("BUY")) {
            checkDelete(response[0], response[1]);
        } else if(response[0].equals("LIST")) {
            sendList();
        }
    }

    void checkCred(String userData)
    {
        String[] userInfo= userData.split(" ");
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            line = br.readLine();
            String[] cred= line.split(" ");
            if (cred[0].equals(userInfo[0]) && cred[1].equals(userInfo[1])) {
                // successful login
                writer.println("LOGIN-VALID");

            } else {
                writer.println("LOGIN-INVALID");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void checkAdd(String carData){
        boolean flag= true;
        String[] carInfo= carData.split(",");
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("cars.txt"));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String lineInfo[]= line.split(",");
                if (lineInfo[0].equals(carInfo[0])) {
                    writer.println("ADD-INVALID");
                    flag= false;
                }
            }
            br.close();
            if (flag){
                CarServer.addCar(new CarServer(carInfo[0], carInfo[1], carInfo[2], carInfo[3], carInfo[4], carInfo[5], carInfo[6], carInfo[7]));
                writer.println("ADD-VALID&"+carInfo[0]+","+ carInfo[1]+","+ carInfo[2]+","+  carInfo[3]+","+  carInfo[4]+","+  carInfo[5]+","+ carInfo[6]+","+  carInfo[7]);
//                BufferedWriter bw = new BufferedWriter(new FileWriter("cars.txt", true));
//                bw.append("\n"+carInfo[0]+","+ carInfo[1]+","+ carInfo[2]+","+  carInfo[3]+","+  carInfo[4]+","+  carInfo[5]+","+ carInfo[6]+","+  carInfo[7]);
//                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void checkDelete(String mode, String carData){
        boolean flag= false;
        String[] carInfo= carData.split(",");
        System.out.println(carInfo[0]);
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("cars.txt"));
            line = br.readLine();
            String lineInfo[]= line.split(",");
            if (lineInfo[0].equals(carInfo[0])) {
                writer.println(mode+"-VALID&"+lineInfo[0]);
                CarServer.deleteCar(carInfo[0]);
            } else {
                writer.println(mode+"-INVALID");
            }
            System.out.println("here");
            br.close();
            System.out.println(CarServer.getList().size());
            BufferedWriter bw= new BufferedWriter(new FileWriter("cars.txt"));
            for (CarServer car : CarServer.getList())
            {
                System.out.println(car.carListString());
                bw.write(car.getRegNo()+","
                        +car.getYear()+","
                        +car.getColor()[0]+","
                        +car.getColor()[1]+","
                        +car.getColor()[2]+","
                        +car.getMake()+","
                        +car.getModel()+","
                        +car.getPrice()+"\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void sendList(){
        try {
            String line;
            String str="LIST-";
            BufferedReader br = new BufferedReader(new FileReader("cars.txt"));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                str= str+line+"&";
                System.out.println(str);

                CarServer.addCar(new CarServer(line));
            }

            br.close();
            writer.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
