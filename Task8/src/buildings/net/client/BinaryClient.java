package buildings.net.client;

import buildings.Building;
import buildings.BuildingUnderArrestException;
import buildings.Buildings;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFactory;
import buildings.net.SocketSettings;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BinaryClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(SocketSettings.getAddress(), SocketSettings.getPort());
        } catch (Exception e) {
            System.out.println("Client: Server is disable");
            return;
        }
        System.out.println("Client: Connection with server is set up");



        try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             Writer fos = new FileWriter("price.txt")) {

            BufferedReader buildingsTypesReader = new BufferedReader(new FileReader("C:\\Task8\\src\\resources\\buildingTypes.txt"));
            BufferedReader buildingsReader = new BufferedReader(new FileReader("C:\\Task8\\src\\resources\\buildings.txt"));
            while (true) {

                //Send Building Type
//                String type = buildingsTypesReader.readLine();
//                if (type == null) {
//                    break;
//                }
//                byte[] typeToByteArray = type.getBytes(StandardCharsets.UTF_8);
//                dataOutputStream.writeInt(typeToByteArray.length);
//                dataOutputStream.write(typeToByteArray);
//                dataOutputStream.flush();

                  //Send Building Line
//                String buildingLine = buildingsReader.readLine();
//                byte[] buildingLineArray = buildingLine.getBytes(StandardCharsets.UTF_8);
//                dataOutputStream.writeInt(buildingLineArray.length);
//                dataOutputStream.write(buildingLineArray);
//                dataOutputStream.flush();

//                //Recive data
//                boolean isSuccess = dataInputStream.readBoolean();
//                if (isSuccess)
//                {
//                    double price = dataInputStream.readDouble();
//                    System.out.println("Client: Price of "+ type + " is " + price);
//                    fos.write(price + "\n");
//                } else {
//                    int bufferLength = dataInputStream.readInt();
//                    byte[] buffer = new byte[bufferLength];
//                    dataInputStream.read(buffer);
//                    String exceptionMessage = new String(buffer, StandardCharsets.UTF_8);
//                    System.out.println("Client: Server exception: " + exceptionMessage);
//                    fos.write("Building arested\n");
//                }

                String type = buildingsTypesReader.readLine();
                if (type == null) {
                    break;
                }
                switch (type) {
                    case "OfficeBuilding": {
                        Buildings.setBuildingFactory(new OfficeFactory());
                    }
                    break;
                    case "Dwelling": {
                        Buildings.setBuildingFactory(new DwellingFactory());
                    }
                    break;
                    case "Hotel": {
                        Buildings.setBuildingFactory(new HotelFactory());
                    }
                    break;
                }
                String buildingLine = buildingsReader.readLine();
                Reader reader = new StringReader(buildingLine);
                Building building = Buildings.readBuilding(reader);

                oos.writeObject(building);
                oos.flush();
                boolean isSuccess = ois.readBoolean();
                if (isSuccess){
                    double price = ois.readDouble();
                    System.out.println("Client: Price of "+ type + " is " + price);
                    fos.write(price + "\n");
                } else {
                    BuildingUnderArrestException exception = (BuildingUnderArrestException) ois.readObject();
                    System.out.println("Client: Server exception: " + exception.getMessage());
                    fos.write("Building arested\n");
                }

                oos.writeInt(1);
                oos.flush();
                System.out.println("Client: Client completed success");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Client: Client completed Failed " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Client: Client completed Failed " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Client: Client completed Failed "+ e.getMessage());
        }
    }
}
