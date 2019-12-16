package buildings.dwelling.hotel;

import buildings.Building;
import buildings.BuildingFactory;
import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

public class HotelFactory implements BuildingFactory {
    public Space createSpace(double square){
        return new Flat(square);
    }
    public Space createSpace(int roomsCount, double square){
        return new Flat(roomsCount,square);

    }
    public Floor createFloor(int spacesCount){
        return new HotelFloor(spacesCount);
    }
    public Floor createFloor(Space[] spaces){
        return new HotelFloor(spaces);
    }
    public Building createBuilding(int floorsCount, int[] spacesCounts){
        return new Hotel(floorsCount,spacesCounts);
    }
    public Building createBuilding(Floor[] floors){
        return new Hotel(floors);
    }

}
