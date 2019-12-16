package buildings.dwelling;

import buildings.Building;
import buildings.BuildingFactory;
import buildings.Floor;
import buildings.Space;

public class DwellingFactory implements BuildingFactory {
    public Space createSpace(double square){
        return new Flat(square);
    }
    public Space createSpace(int roomsCount, double square){
        return new Flat(roomsCount,square);

    }
    public Floor createFloor(int spacesCount){
        return new DwellingFloor(spacesCount);
    }
    public Floor createFloor(Space[] spaces){
        return new DwellingFloor(spaces);
    }
    public Building createBuilding(int floorsCount, int[] spacesCounts){
        return new Dwelling(floorsCount,spacesCounts);
    }
    public Building createBuilding(Floor[] floors){
        return new Dwelling(floors);
    }
}
