package buildings.net.server.sequental;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Date;

public class BinaryServer {

    public static double getBuildingPricing(Building building) throws BuildingUnderArrestException {
        boolean isArest = CheckBuildingArest(building);
        if (isArest)
        {
            throw new BuildingUnderArrestException("The building is arested");
        }
        double sumSquare = 0;
        int countFloor = building.getCountFloors();
        for (int i = 0; i < countFloor; i++) {
            int spaceCount = building.getFloor(i).getCountSpace();
            for (int j = 0; j < spaceCount; j++) {
                sumSquare += building.getFloor(i).getSpace(j).getSquare();
            }
        }
        if (building instanceof Dwelling) {
            return sumSquare * 1000;
        }
        if (building instanceof OfficeBuilding) {
            return sumSquare * 1500;
        }
        if (building instanceof Hotel) {
            return sumSquare * 2000;
        }
        return 0.0;
    }

    public static boolean CheckBuildingArest(Building building) {
        Random random = new Random(System.currentTimeMillis());
        double p = random.nextDouble();
        return  p>=0 && p<=0.1;
    }

    public static void main(String[] args) {
        try {

            ServerSocket server = new ServerSocket(SocketSettings.getPort());
            System.out.println("Server: Server is started on port " + SocketSettings.getPort());

            System.out.println("Server: Wait for client");
            Socket socket = server.accept();
            System.out.println("Server: Client is connected");
            try (InputStream socketInputStream = socket.getInputStream();
                 OutputStream socketOutputStream = socket.getOutputStream();
                 DataInputStream in = new DataInputStream(socketInputStream);
                 DataOutputStream out = new DataOutputStream(socketOutputStream)) {

                while (true) {
                    int bufferLength = in.readInt();
                    byte[] buffer = new byte[bufferLength];
                    in.read(buffer);

                    String buildingType = new String(buffer, StandardCharsets.UTF_8);
                    System.out.println("Server: Get Data from client - \"" + buildingType + "\"");
                    switch (buildingType) {
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

                    bufferLength = in.readInt();
                    Building building = Buildings.inputBuilding(in, bufferLength);
                    try {
                        double price = getBuildingPricing(building);
                        out.writeBoolean(true);
                        out.flush();
                        out.writeDouble(price);
                        out.flush();
                    } catch (BuildingUnderArrestException e) {
                        out.writeBoolean(false);
                        out.flush();
                        bufferLength = e.getMessage().length();
                        out.writeInt(bufferLength);
                        out.flush();
                        out.write(e.getMessage().getBytes(StandardCharsets.UTF_8));
                        out.flush();
                    }

                }
            }  catch (EOFException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch(EOFException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
