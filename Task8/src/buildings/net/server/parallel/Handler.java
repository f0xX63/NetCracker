package buildings.net.server.parallel;

import buildings.Building;
import buildings.BuildingUnderArrestException;
import buildings.Buildings;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFactory;
import buildings.net.server.sequental.BinaryServer;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Handler extends Thread {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream())) {
            System.out.println("Server: Client is connected");

            while (true) {
                try {
                    Building building = (Building) in.readObject();
                    double price = BinaryServer.getBuildingPricing(building);
                    out.writeBoolean(true);
                    out.flush();
                    out.writeDouble(price);
                    out.flush();
                } catch (BuildingUnderArrestException e) {
                    out.writeBoolean(false);
                    out.flush();
                    out.writeObject(e);
                    out.flush();
                }
                int code = in.readInt();
                if (code == 1)
                    break;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
