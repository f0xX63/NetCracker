package buildings.office;

import buildings.Building;
import buildings.BuildingFactory;
import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;

public class OfficeFactory implements BuildingFactory {
    public Space createSpace(double square){
        return new Office(square);
    }
    public Space createSpace(int roomsCount, double square){
        return new Office(roomsCount,square);

    }
    public Floor createFloor(int spacesCount){
        return new OfficeFloor(spacesCount);
    }
    public Floor createFloor(Space[] spaces){
        return new OfficeFloor(spaces);
    }
    public Building createBuilding(int floorsCount, int[] spacesCounts){
        return new OfficeBuilding(floorsCount,spacesCounts);
    }
    public Building createBuilding(Floor[] floors){
        return new OfficeBuilding(floors);
    }

}
