package buildings.dwelling;

import buildings.*;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable, Iterable {

    private Floor[] floors;

    public Dwelling(int countFloors, int[] countFlatsOnFloor) throws FloorIndexOutOfBoundsException {
        if (countFloors < 0 ) {
            throw new FloorIndexOutOfBoundsException("Не верное количество этажей");
        }
        floors = new DwellingFloor[countFloors];
        for (int i = 0; i < countFloors; i++) {
            floors[i] = new DwellingFloor(countFlatsOnFloor[i]);
        }
    }

    public Dwelling(Floor[] floors){
        this.floors = floors;
    }

    public int getCountFloors() {
        return floors.length;
    }

    public int getCountSpaces(){
        int sum=0;
        for (int i = 0; i <floors.length ; i++) {
            sum += floors[i].getCountSpace();
        }
        return sum;
    }

    public double getTotalSquare(){
        double sum=0;
        for (int i = 0; i <floors.length ; i++) {
            sum += floors[i].getTotalSquareOnFloor();

        }
        return sum;
    }

    public int getTotalCountRoom(){
        int sum=0;
        for (int i = 0; i <floors.length ; i++) {
            sum += floors[i].getTotalCountRoomOnFloor();
        }
        return sum;
    }

    public Floor[] getArrayFloor(){
        return floors;
    }

    public Floor getFloor(int numberFloor) throws FloorIndexOutOfBoundsException{
        if (getCountFloors() <= numberFloor || numberFloor < 0 ) {
            throw new FloorIndexOutOfBoundsException("Такого этажа не существует");
        }
        return floors[numberFloor];
    }

    public void setFloor(int numberFloor, Floor newFloor) throws FloorIndexOutOfBoundsException {
        if (getCountFloors() <= numberFloor || numberFloor < 0 ) {
            throw new FloorIndexOutOfBoundsException("Такого этажа не существует");
        }
        floors[numberFloor] = newFloor;
    }

    public Space getSpace(int numberSpace) throws SpaceIndexOutOfBoundsException{
        Space getFlat = null;
        if (numberSpace < 0 || numberSpace >= getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        } else {
            int currentFlat = 0;
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    if (currentFlat == numberSpace) {
                        getFlat=getFloor(i).getSpace(numberSpace);
                        break;
                    }
                    currentFlat++;
                }
            }
        }
        return getFlat;
    }

    public void setSpace(int numberSpace, Space newSpace) throws SpaceIndexOutOfBoundsException {
        if (numberSpace < 0 || numberSpace >= getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        } else {
            int currentFlat = 0;
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    if (currentFlat == numberSpace) {
                        getFloor(i).setSpace(numberSpace,newSpace);
                    }
                    currentFlat++;
                }
            }
        }
    }

    public void addSpace(int numberSpace, Space newFlat) throws SpaceIndexOutOfBoundsException {
        if (numberSpace < 0 || numberSpace > getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        } else {
            int currentFlat = 0;
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    if (currentFlat == numberSpace) {
                        getFloor(i).addSpace(j, newFlat);
                        return;
                    }
                    currentFlat++;
                }
            }
        }
    }
    public void deleteSpace(int numberSpace) throws SpaceIndexOutOfBoundsException {
        if (numberSpace < 0 || numberSpace >= getCountSpaces()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        } else {
            int currentFlat = 0;
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    if (currentFlat == numberSpace) {
                        getFloor(i).deleteSpace(j);
                        return;
                    }
                    currentFlat++;
                }
            }
        }
    }

    public Space getBestSpace(){
        Space[] bestSpaces = new Space[getCountFloors()];
        for (int i = 0; i < getCountFloors() ; i++) {
            for (int j = 0; j < getFloor(i).getCountSpace() ; j++) {
                bestSpaces[i] = getFloor(i).getBestSpace();
            }
        }
        int n = bestSpaces.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (bestSpaces[j].getSquare() > bestSpaces[j+1].getSquare())
                {
                    Space temp = bestSpaces[j];
                    bestSpaces[j] = bestSpaces[j+1];
                    bestSpaces[j+1] = temp;
                }
        return bestSpaces[n-1];
    }
    public Space[] squareDescending(){
        Space[] flatsWithDescendingSquare = new Space[getCountSpaces()];
        int index = 0;
        for (int i = 0; i < getCountFloors() ; i++) {
            for (int j = 0; j <getFloor(i).getCountSpace() ; j++) {
                flatsWithDescendingSquare[index++] = getFloor(i).getSpace(j);
            }
        }
        int n = flatsWithDescendingSquare.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (flatsWithDescendingSquare[j].getSquare() < flatsWithDescendingSquare[j+1].getSquare())
                {
                    Space temp = flatsWithDescendingSquare[j];
                    flatsWithDescendingSquare[j] = flatsWithDescendingSquare[j+1];
                    flatsWithDescendingSquare[j+1] = temp;
                }
        return flatsWithDescendingSquare;
    }

    public boolean equals(Object object){
        if (object instanceof Dwelling){
            Dwelling dwelling = ((Dwelling) object);
            if(dwelling.getCountFloors() == getCountFloors() && dwelling.getArrayFloor() == getArrayFloor())
                return true;
            return  false;
        }
        return false;
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = floors.length-1; i >= 0; i--) {
            builder.append("Dwelling " + getCountFloors() + " ( " + floors[i].toString() + " ) " );
        }
        return builder.toString();
    }

    public int hashCode(){
        int hashCode = getCountFloors() ^ getFloor(0).hashCode();
        for (int i = 1; i < getCountFloors() ; i++) {
            hashCode = hashCode^getFloor(i).hashCode();
        }
        return hashCode;
    }

    public Object clone(){
        int[] arrayCountSpaces = new int[getCountFloors()];
        for (int i = 0; i < getCountFloors() ; i++) {
            arrayCountSpaces[i] = getFloor(i).getCountSpace();
        }
        Dwelling clone = new Dwelling(getCountFloors(), arrayCountSpaces);
        for (int i = 0; i < getCountFloors(); i++) {
            clone.setFloor(i, (Floor) getFloor(i).clone());
        }
        return clone;
        //return CloneHelper.cloneObject(this);
    }

    public Iterator<Space> iterator() {
        return new BuildingIterator(this);
    }
}
