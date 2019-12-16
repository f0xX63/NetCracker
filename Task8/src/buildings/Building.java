package buildings;

public interface Building {

    int getCountFloors();

    int getCountSpaces();

    double getTotalSquare();

    int getTotalCountRoom();

    Floor[] getArrayFloor();

    Floor getFloor(int numberFloor);

    void setFloor(int numberFloor, Floor newFloor);

    Space getSpace(int numberSpace);

    void setSpace(int numberSpace, Space newSpace);

    void addSpace(int numberOffice, Space newOffice);

    void deleteSpace(int numberSpace);

    Space getBestSpace();

    Space[] squareDescending();

    String toString();

    Object clone();

}
