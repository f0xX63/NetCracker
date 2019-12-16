package buildings;
import java.util.Iterator;

public interface Floor extends Comparable<Floor> {

    int getCountSpace();

    double getTotalSquareOnFloor();

    int getTotalCountRoomOnFloor();

    Space[] getSpaces();

    Space getSpace(int numberSpace);

    void setSpace(int numberSpace, Space newSpace);

    void addSpace(int numberSpace, Space newSpace);

    void deleteSpace(int numberSpace);

    Space getBestSpace();

    Object clone();

    Iterator<Space> iterator();

}
