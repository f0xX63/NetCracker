package buildings.dwelling;

import buildings.Floor;
import buildings.FloorIterator;
import buildings.Space;
import buildings.SpaceIndexOutOfBoundsException;

import java.io.*;
import java.util.Iterator;

public class DwellingFloor implements Floor, Serializable, Iterable, Comparable<Floor> {
    private Space[] flats;

    public DwellingFloor(int countFlats) throws SpaceIndexOutOfBoundsException {
        if (countFlats < 0) {
            throw new SpaceIndexOutOfBoundsException("Количество квартир не может быть отрицательно");
        }
        flats = new Flat[countFlats];
    }

    public DwellingFloor(Space[] flats) {
        this.flats = flats;
    }

    public int getCountSpace() {
        return flats.length;
    }

    public double getTotalSquareOnFloor() {
        double sum = 0;
        for (int i = 0; i < flats.length; i++) {
            sum += flats[i].getSquare();
        }
        return sum;
    }

    public int getTotalCountRoomOnFloor() {
        int sum = 0;
        for (int i = 0; i < flats.length; i++) {
            sum += flats[i].getCountRoom();
        }
        return sum;
    }

    public Space[] getSpaces() {
        return flats;
    }

    public Space getSpace(int numberSpace) throws SpaceIndexOutOfBoundsException {
        if (numberSpace < 0 || numberSpace >= getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        }
        return flats[numberSpace];
    }


    public void setSpace(int numberSpace, Space newSpace) throws SpaceIndexOutOfBoundsException {
        if (numberSpace < 0 || numberSpace > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        }
        flats[numberSpace] = newSpace;
    }

    public void addSpace(int numberSpace, Space newSpace) throws SpaceIndexOutOfBoundsException {
        if (numberSpace < 0 || numberSpace > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        } else {
            Space[] newFlats = new Flat[getCountSpace() + 1];
            for (int i = 0; i < numberSpace; i++) {
                newFlats[i] = flats[i];
            }
            newFlats[numberSpace] = newSpace;
            for (int i = numberSpace; i < getCountSpace(); i++) {
                newFlats[i + 1] = flats[i];
            }
            flats = newFlats;
        }
    }

    public void deleteSpace(int numberSpace) throws SpaceIndexOutOfBoundsException {
        if (numberSpace < 0 || numberSpace > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        } else {
            Space[] newFlats = new Flat[getCountSpace() - 1];
            for (int i = 0; i < numberSpace; i++) {
                newFlats[i] = flats[i];
            }
            for (int i = numberSpace + 1; i < getCountSpace(); i++) {
                newFlats[i - 1] = flats[i];
            }
            flats = newFlats;
        }
    }

    public Space getBestSpace() {
        Space[] cloneFlats = new Space[getCountSpace()];
        for (int i = 0; i < getCountSpace(); i++) {
            cloneFlats[i] = flats[i];
        }
        int n = cloneFlats.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (cloneFlats[j].getSquare() > cloneFlats[j + 1].getSquare()) {
                    Space temp = cloneFlats[j];
                    cloneFlats[j] = cloneFlats[j + 1];
                    cloneFlats[j + 1] = temp;
                }
        return cloneFlats[getCountSpace() - 1];
    }

    public boolean equals(Object object) {
        if (object instanceof DwellingFloor) {
            DwellingFloor dwellingFloor = ((DwellingFloor) object);
            if (dwellingFloor.getCountSpace() == getCountSpace() && dwellingFloor.getSpaces() == getSpaces())
                return true;
            return false;
        }
        return false;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DwellingFloor " + " " + flats.length + " ( ");
        for (Space flat : flats) {
            builder.append(flat.toString());
        }
        builder.append(" )");
        return builder.toString();
    }

    public int hashCode() {
        int hashCode = getCountSpace() ^ getSpace(0).hashCode();
        for (int i = 1; i < getCountSpace(); i++) {
            hashCode = hashCode ^ getSpace(i).hashCode();
        }
        return hashCode;
    }

    public Object clone() {
        DwellingFloor clone = new DwellingFloor(getCountSpace());
        for (int i = 0; i < getCountSpace(); i++) {
            clone.setSpace(i, (Space) getSpace(i).clone());
        }
        return clone;
        //return CloneHelper.cloneObject(this);
    }

    public Iterator<Space> iterator() {
        return new FloorIterator(this);
    }

    @Override
    public int compareTo(Floor floor){
            if (getCountSpace() == floor.getCountSpace()){
                return 0;
            } else if(getCountSpace() < floor.getCountSpace()){
                return -1;
            } else return 1;

    }
}