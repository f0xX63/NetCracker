package buildings;


import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {
    private Space[] flats;

    public SynchronizedFloor(Space[] flats) {
        this.flats = flats;
    }

    @Override
    public synchronized int getCountSpace() {
        return flats.length;
    }



    @Override
    public synchronized double getTotalSquareOnFloor() {
        double sum = 0;
        for (int i = 0; i < flats.length; i++) {
            sum += flats[i].getSquare();
        }
        return sum;
    }

    @Override
    public synchronized int getTotalCountRoomOnFloor() {
        int sum = 0;
        for (int i = 0; i < flats.length; i++) {
            sum += flats[i].getCountRoom();
        }
        return sum;
    }

    @Override
    public synchronized Space[] getSpaces() {
        return flats;
    }

    @Override
    public synchronized Space getSpace(int numberSpace) {
        if (numberSpace < 0 || numberSpace >= getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        }
        return flats[numberSpace];
    }

    @Override
    public synchronized void setSpace(int numberSpace, Space newSpace) {
        if (numberSpace < 0 || numberSpace > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        }
        flats[numberSpace] = newSpace;
    }

    @Override
    public synchronized void addSpace(int numberSpace, Space newSpace) {
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

    @Override
    public synchronized void deleteSpace(int numberSpace) {
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

    @Override
    public synchronized Space getBestSpace() {
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

    @Override
    public synchronized Object clone() {
        DwellingFloor clone = new DwellingFloor(getCountSpace());
        for (int i = 0; i < getCountSpace(); i++) {
            clone.setSpace(i, (Space) getSpace(i).clone());
        }
        return clone;
    }

    @Override
    public Iterator<Space> iterator() {
        return null;
    }

    @Override
    public synchronized int compareTo(Floor floor) {
        if (getCountSpace() == floor.getCountSpace()){
            return 0;
        } else if(getCountSpace() < floor.getCountSpace()){
            return -1;
        } else return 1;
    }

    @Override
    public synchronized int hashCode() {
        int hashCode = getCountSpace() ^ getSpace(0).hashCode();
        for (int i = 1; i < getCountSpace(); i++) {
            hashCode = hashCode ^ getSpace(i).hashCode();
        }
        return hashCode;
    }

    @Override
    public synchronized boolean equals(Object object) {
        if (object instanceof DwellingFloor) {
            DwellingFloor dwellingFloor = ((DwellingFloor) object);
            if (dwellingFloor.getCountSpace() == getCountSpace() && dwellingFloor.getSpaces() == getSpaces())
                return true;
            return false;
        }
        return false;
    }

    @Override
    public synchronized String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DwellingFloor " + " " + flats.length + " ( ");
        for (Space flat : flats) {
            builder.append(flat.toString());
        }
        builder.append(" )");
        return builder.toString();
    }
}
