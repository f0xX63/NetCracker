package buildings.net;

public class SocketSettings {
    private static final int port  = 7536;
    private static final String address = "localhost";

    public static int getPort() {
        return port;
    }

    public static String getAddress() {
        return address;
    }
}
