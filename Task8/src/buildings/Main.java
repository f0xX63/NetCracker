package buildings;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.threads.*;

import java.util.concurrent.Semaphore;

public class Main {
    public static Space[] createFlats(int countFlats, int[] countsRooms, double[] square) {
        Space[] allFlats = new Flat[countFlats];
        for (int i = 0; i < countFlats; i++) {
            allFlats[i] = new Flat(countsRooms[i], square[i]);
        }
        return allFlats;
    }

    public static Floor[] createDwellingFloors(int countFloors, int[] countFlats, int[][] countsRooms, double[][] square) {
        Floor[] allFloors = new DwellingFloor[countFloors];
        for (int i = 0; i < countFloors; i++) {
            Space[] flatsOnFloor = createFlats(countFlats[i], countsRooms[i], square[i]);
            allFloors[i] = new DwellingFloor(flatsOnFloor);
        }
        return allFloors;
    }

    public static Space[] createOffices(int countOffices, int[] countsRooms, double[] square) {
        Space[] allOffices = new Office[countOffices];
        for (int i = 0; i < countOffices; i++) {
            allOffices[i] = new Office(countsRooms[i], square[i]);
        }
        return allOffices;
    }

    public static Floor[] createOfficeBuildingFloors(int countFloors, int[] countOffices, int[][] countsRooms, double[][] square) {
        Floor[] allFloors = new OfficeFloor[countFloors];
        for (int i = 0; i < countFloors; i++) {
            Space[] officesOnFloor = createOffices(countOffices[i], countsRooms[i], square[i]);
            allFloors[i] = new OfficeFloor(officesOnFloor);
        }
        return allFloors;
    }

    public static void main(String[] args) {
        Space space1 = Buildings.createSpace(2, 1.0);
        Space space2 = Buildings.createSpace(7, 2.0);
        Space space3 = Buildings.createSpace(1, 3.0);
        Space space4 = Buildings.createSpace(1, 4.0);
        Space space5 = Buildings.createSpace(1, 5.0);
        Space space6 = Buildings.createSpace(1, 6.0);
        Space space7 = Buildings.createSpace(1, 7.0);
        Space space8 = Buildings.createSpace(1, 8.0);
        Space space9 = Buildings.createSpace(1, 9.0);
        Space space10 = Buildings.createSpace(1, 10.0);

        Space[] spaces = new Space[]{ space1, space2, space3, space4, space5, space6, space7, space8, space9, space10 };
        Floor floor = Buildings.createFloor(spaces);
        SequentialSemaphore semaphore = new SequentialSemaphore();
//        Semaphore semaphore1 = new Semaphore(1);

        Runnable repairer = new SequentalRepairer(floor, semaphore);
        Runnable cleaner = new SequentalCleaner(floor, semaphore);
        Thread thread1 = new Thread(repairer);
        Thread thread2 = new Thread(cleaner);

        thread1.start();
        thread2.start();



    }
}

