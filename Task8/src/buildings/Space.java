package buildings;

public interface Space extends Comparable<Space> {

    int getCountRoom();

    double getSquare();

    void setCountRoom(int countRoom);

    void setSquare(double square);

    Object clone();

}
