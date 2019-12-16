package buildings.net.server.parallel;

import buildings.net.SocketSettings;
import java.io.IOException;
import java.net.ServerSocket;

public class BinaryServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(SocketSettings.getPort());
            System.out.println("Server: Server is started on port " + SocketSettings.getPort());
            try {
                while (true) {
                    System.out.println("Server: Wait for client");
                        new Handler(server.accept()).start();
                }
            } finally {
                server.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
