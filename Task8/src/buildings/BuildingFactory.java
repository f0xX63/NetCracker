package buildings;

public interface BuildingFactory {

    public Space createSpace(double square);
    public Space createSpace(int roomsCount, double square);
    public Floor createFloor(int spacesCount);
    public Floor createFloor(Space[] spaces);
    public Building createBuilding(int floorsCount, int[] spacesCounts);
    public Building createBuilding(Floor[] floors);
}
