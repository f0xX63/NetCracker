package buildings;

import buildings.dwelling.DwellingFactory;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;

public class Buildings  {

    private static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    public static Building createBuilding(Floor[] floors) {
       return buildingFactory.createBuilding(floors);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount,spacesCounts);
    }

    public static Floor createFloor(int spacesCount){
        return buildingFactory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaces){
        return buildingFactory.createFloor(spaces);
    }

    public static Space createSpace(double square) {
        return buildingFactory.createSpace(square);

    }
    public static Space createSpace(int roomsCount, double square) {
        return buildingFactory.createSpace(roomsCount, square);
    }

    public static <T extends Comparable> T[] sort(T[] objects)
    {
        int n = objects.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (objects[j].compareTo(objects[j+1]) > 0 ) {
                    T temp = objects[j];
                    objects[j] = objects[j + 1];
                    objects[j + 1] = temp;
                }
        return objects;
    }

    public static <T extends Comparator<T>> T[] sort(T[] objects,  Comparator<T> comparator)
    {
        int n = objects.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (comparator.compare(objects[j],objects[j+1]) >0 ) {
                    T temp = objects[j];
                    objects[j] = objects[j + 1];
                    objects[j + 1] = temp;
                }
        return objects;
    }

    public static void outputBuilding (Building building, OutputStream out) throws IOException {
        DataOutputStream output = new DataOutputStream(out);
        int countFloors = building.getCountFloors();
        output.writeInt(countFloors);
        for (int i = 0; i < building.getCountFloors() ; i++) {
           int countSpaceInCurrentFloor =  building.getFloor(i).getCountSpace();
            output.writeInt(countSpaceInCurrentFloor);
            for (int j = 0; j <  building.getFloor(i).getCountSpace(); j++) {
                int countRoom = building.getFloor(i).getSpace(j).getCountRoom();
                output.writeInt(countRoom);
                double square = building.getFloor(i).getSpace(j).getSquare();
                output.writeDouble((square));
            }
        }
    }

    public static Building inputBuilding (InputStream in, int bufferLength) throws IOException {
        byte[] buffer = new byte[bufferLength];
        in.read(buffer);
        String buildingLine = new String(buffer, StandardCharsets.UTF_8);
        Reader inputString = new StringReader(buildingLine);
        return readBuilding(inputString);
    }

    public static Building inputBuilding (InputStream in) throws IOException {
        DataInputStream input = new DataInputStream(in);
        int countFloors = input.readInt();
        ArrayList<Floor> floors = new ArrayList<Floor>();
        while (input.available()>0)
        {
            int countSpaceInCurrentFloor = input.readInt();
            ArrayList<Space> spaces = new ArrayList<Space>();
            for (int i = 0; i < countSpaceInCurrentFloor ; i++) {
                int countRoom = input.readInt();
                double square = input.readDouble();
                Space space = createSpace(countRoom, square); //спросить как сделать Space
                spaces.add(space);
            }
            Floor floor = createFloor(spaces.toArray(new Space[spaces.size()]));
            floors.add(floor);
        }
        Building building = createBuilding(floors.toArray(new Floor[floors.size()]));
        return building;
    }

    public static void writeBuilding (Building building, Writer out) throws IOException {
        BufferedWriter writer = new BufferedWriter(out);
        int countFloors = building.getCountFloors();
        writer.write(countFloors + " ");
        for (int i = 0; i < building.getCountFloors() ; i++) {
            int countSpaceInCurrentFloor =  building.getFloor(i).getCountSpace();
            writer.write(countSpaceInCurrentFloor + " ");
            for (int j = 0; j <  building.getFloor(i).getCountSpace(); j++) {
                int countRoom = building.getFloor(i).getSpace(j).getCountRoom();
                writer.write(countRoom + " ");
                double square = building.getFloor(i).getSpace(j).getSquare();
                writer.write(Double.toString(square) + " ");
            }
        }
        writer.flush();

    }

    public static Building readBuilding (Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        String line = reader.readLine();
        String[] allValues = line.split(" ");
        int currentIndex = 0;
        int countFloors = Integer.parseInt(allValues[currentIndex++]);
        ArrayList<Floor> floors = new ArrayList<Floor>();
        for (int i = 0; i < countFloors; i++) {
            int countSpaceInCurrentFloor = Integer.parseInt(allValues[currentIndex++]);
            ArrayList<Space> spaces = new ArrayList<Space>();
            for (int j = 0; j < countSpaceInCurrentFloor; j++) {
                int countRoom = Integer.parseInt(allValues[currentIndex++]);
                double square = Double.parseDouble(allValues[currentIndex++]);
                Space space = createSpace(countRoom, square); //спросить как сделать Space
                spaces.add(space);
            }
            floors.add(createFloor(spaces.toArray(new Space[spaces.size()])));
        }

        Building building = createBuilding(floors.toArray(new Floor[floors.size()]));
        return building;
    }

    public static void writeBuildingFormat (Building building, Writer out) throws IOException {
        Formatter formatter = new Formatter(out);
        formatter.format("%2d", building.getCountFloors());
        Floor[] floors = building.getArrayFloor();
        for (Floor floor: floors) {
            formatter.format("%5d", floor.getCountSpace());
            Space[] spaces = floor.getSpaces();
            for (Space space: spaces) {
                formatter.format("%3d", space.getCountRoom());
                formatter.format("%7.2f", space.getSquare());
            }
        }
        formatter.flush();
        formatter.close();
    }

    public static Building readBuilding(Scanner in) throws IOException {
        String doublePattern = "\\d+\\,\\d+";
        String intPattern = "\\d+";
        int countFloor = Integer.parseInt(in.next(intPattern));
        ArrayList<Floor> floors = new ArrayList<Floor>();
        for (int j = 0; j < countFloor; j++) {
            int countFlat = Integer.parseInt(in.next(intPattern));
            ArrayList<Space> spaces = new ArrayList<Space>();
            for (int i = 0; i < countFlat; i++) {
                int countRoom = Integer.parseInt(in.next(intPattern));
                double square = Double.parseDouble(in.next(doublePattern).replace(",", "."));
                Space space = createSpace(countRoom, square); //спросить как сделать Space
                spaces.add(space);
            }
            Floor floor = createFloor(spaces.toArray(new Space[spaces.size()]));
            floors.add(floor);
        }
        Building building = createBuilding(floors.toArray(new Floor[floors.size()]));
        return building;
    }

    public static void serializeBuilding (Building building, OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(building);
        oos.flush();
    }

    public static Building deserializeBuilding (InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream oin = new ObjectInputStream(in);
        Building building = (Building) oin.readObject();
        return building;
    }

    public static Floor synchronizedFloor (Floor floor){
        SynchronizedFloor synchronizedFloor = ((SynchronizedFloor) floor);
        return synchronizedFloor;
    }
}
