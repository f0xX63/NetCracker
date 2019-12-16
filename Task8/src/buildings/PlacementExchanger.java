package buildings;

public class PlacementExchanger {

    public static boolean checkSpaceChange(Space one, Space two){
            return one.getCountRoom()==two.getCountRoom() && one.getSquare()==two.getSquare();
    }

    public static boolean checkFloorChange(Floor one, Floor two){
        return one.getTotalCountRoomOnFloor() ==two.getTotalCountRoomOnFloor() && one.getTotalSquareOnFloor()==two.getTotalSquareOnFloor();
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException, SpaceIndexOutOfBoundsException{
        if (index1 < 0 || index1 >= floor1.getCountSpace() || index2 < 0 || index2 >= floor2.getCountSpace() ) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        }else if (!checkSpaceChange(floor1.getSpace(index1),floor2.getSpace(index2))){
            throw new InexchangeableSpacesException("Обмен невозможен!");
       }
       floor1.setSpace(index1,floor2.getSpace(index2));
       floor2.setSpace(index2,floor1.getSpace(index1));
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException, FloorIndexOutOfBoundsException{
        if (index1 < 0 || index1 >= building1.getCountFloors() || index2 < 0 || index2 >= building2.getCountFloors() ) {
            throw new FloorIndexOutOfBoundsException("Такого этажа не существует!");
        }else if (!checkFloorChange(building1.getFloor(index1),building2.getFloor(index2))){
            throw new InexchangeableFloorsException("Обмен невозможен!");
        }
        building1.setFloor(index1,building2.getFloor(index2));
        building2.setFloor(index2,building1.getFloor(index1));
    }
}


