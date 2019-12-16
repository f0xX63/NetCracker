package buildings.office;

import buildings.*;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building, Serializable, Iterable {

    private ElementOfOfficeBuilding headFloor;

    public OfficeBuilding(int countFloor, int[] countOfficesOnFloors) throws FloorIndexOutOfBoundsException {
        if (countFloor < 0 ) {
            throw new FloorIndexOutOfBoundsException("Такого этажа не существует");
        }
       headFloor = new ElementOfOfficeBuilding(new OfficeFloor(countOfficesOnFloors[0]),null,null);
        for (int i = 1; i < countFloor; i++) {
            insertFloor(new OfficeFloor(countOfficesOnFloors[i]), 0);
        }
    }

    public OfficeBuilding(Floor[] arrayOfficesFloors){
        headFloor = new ElementOfOfficeBuilding(arrayOfficesFloors[0],null,null);
        for (int i = 1; i < arrayOfficesFloors.length; i++) {
            insertFloor(arrayOfficesFloors[i], i);
        }
    }

    public int getCountFloors() {
        int count = 0;
        if (headFloor == null)

            return count;
        ElementOfOfficeBuilding temp = headFloor.getNext();
        count++;
        while (temp != headFloor && temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    public int getCountSpaces(){
        int sum =0;
        if (headFloor == null)
            return sum;
        ElementOfOfficeBuilding temp = headFloor.getNext();
        sum+= headFloor.getCurrentElement().getCountSpace();
        while (temp != headFloor && temp != null) {
            sum+= temp.getCurrentElement().getCountSpace();
            temp = temp.getNext();
        }
        return sum;
    }

    public double getTotalSquare(){
        double sum = 0;
        if (headFloor == null)
            return sum;
        ElementOfOfficeBuilding temp = headFloor.getNext();
        sum+= headFloor.getCurrentElement().getTotalSquareOnFloor();
        while (temp != headFloor && temp != null) {
            sum+= headFloor.getNext().getCurrentElement().getTotalSquareOnFloor();
            temp = temp.getNext();
        }
        return sum;
    }

    public int getTotalCountRoom(){
        int sum =0;
        if (headFloor == null)
            return sum;
        ElementOfOfficeBuilding temp = headFloor.getNext();
        sum+= headFloor.getCurrentElement().getTotalCountRoomOnFloor();
        while (temp != headFloor && temp != null) {
            sum+= headFloor.getNext().getCurrentElement().getTotalCountRoomOnFloor();
            temp = temp.getNext();
        }
        return sum;
    }

    public Floor[] getArrayFloor(){
        Floor[] arrayOffices = new Floor[getCountFloors()];
        for (int i = 0; i < getCountFloors(); i++) {
            arrayOffices[i] = getElementOfList(i).getCurrentElement();
        }
        return arrayOffices;
    }

    public Floor getFloor(int numberFloor) throws FloorIndexOutOfBoundsException {
            return getElementOfList(numberFloor).getCurrentElement();
    }

    public void setFloor(int numberFloor, Floor newFloor) throws FloorIndexOutOfBoundsException {
        getElementOfList(numberFloor).setCurrentElement(newFloor);
    }

    public Space getSpace(int numberSpace) throws SpaceIndexOutOfBoundsException{
        Space office= null;
        if (numberSpace < 0 || numberSpace >= getCountSpaces() ) {
            throw new SpaceIndexOutOfBoundsException("Неверное значение колличества офисов");
        } else {
            int currentFlat = 0;
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    if (currentFlat == numberSpace) {
                        office= getFloor(i).getSpace(numberSpace);
                        break;
                    }
                    currentFlat++;
                }
            }
        }
        return office;
    }

    public void setSpace(int numberSpace, Space newSpace) throws SpaceIndexOutOfBoundsException{
        if (numberSpace < 0 || numberSpace >= getCountSpaces() ) {
            throw new SpaceIndexOutOfBoundsException("Неверное значение колличества офисов");
        } else {
            int currentOffice = 0;
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    if (currentOffice == numberSpace) {
                        getFloor(i).setSpace(currentOffice,newSpace);;
                        break;
                    }
                    currentOffice++;
                }
            }
        }
    }

    public void addSpace(int numberSpace, Space newOffice) throws SpaceIndexOutOfBoundsException{
        if (numberSpace < 0 || numberSpace > getCountSpaces() ) {
            throw new SpaceIndexOutOfBoundsException("Неверное значение колличества офисов");
        } else {
            int currentOffice = 0;
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    if (currentOffice == numberSpace) {
                        getFloor(i).addSpace(j, newOffice);
                        break;
                    }
                    currentOffice++;
                }
            }
        }
    }

    public void deleteSpace(int numberSpace) throws SpaceIndexOutOfBoundsException{
        if (numberSpace < 0 || numberSpace >= getCountSpaces() ) {
            throw new SpaceIndexOutOfBoundsException("Неверное значение колличества офисов");
        } else {
            int currentOffice = 0;
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    if (currentOffice == numberSpace) {
                        getFloor(i).deleteSpace(j);
                        break;
                    }
                    currentOffice++;
                }
            }
        }
    }

    public Space getBestSpace(){
            int currentOffice = 0;
            Space[] arrayBestSpace = new Space[getCountFloors()];
            for (int i = 0; i < getCountFloors(); i++) {
                for (int j = 0; j < getFloor(i).getCountSpace(); j++) {
                    arrayBestSpace[i] = getFloor(i).getBestSpace();
                    break;
                }
                currentOffice++;
            }
        int n = arrayBestSpace.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arrayBestSpace[j].getSquare() > arrayBestSpace[j+1].getSquare())
                {
                    Space temp = arrayBestSpace[j];
                    arrayBestSpace[j] = arrayBestSpace[j+1];
                    arrayBestSpace[j+1] = temp;
                }
        return arrayBestSpace[n-1];
    }

    public Space[] squareDescending(){
        Space[] officesWithDescendingSquare = new Space[getCountSpaces()];
        int index = 0;
        for (int i = 0; i < getCountFloors() ; i++) {
            for (int j = 0; j < getFloor(i).getCountSpace() ; j++) {
                officesWithDescendingSquare[index++] = getFloor(i).getSpace(j);
            }
        }
        int n = officesWithDescendingSquare.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (officesWithDescendingSquare[j].getSquare() < officesWithDescendingSquare[j+1].getSquare())
                {
                    Space temp = officesWithDescendingSquare[j];
                    officesWithDescendingSquare[j] = officesWithDescendingSquare[j+1];
                    officesWithDescendingSquare[j+1] = temp;
                }
        return officesWithDescendingSquare;
    }

    private ElementOfOfficeBuilding getElementOfList(int numberElement) throws FloorIndexOutOfBoundsException {
        if (getCountFloors() - 1 < numberElement || numberElement < 0 ) {
            throw new FloorIndexOutOfBoundsException("Такого этажа не существует");
        }
        ElementOfOfficeBuilding current = headFloor;
        for (int i = 0; i < numberElement; i++) {
            current = current.getNext();
        }
        return current;
    }

    private void insertFloor(Floor newFloor, int numberFloor) throws FloorIndexOutOfBoundsException {
        if (getCountFloors() < numberFloor || numberFloor < 0 ) {
            throw new FloorIndexOutOfBoundsException("Такого этажа не существует");
        }else if (numberFloor == 0) {
            ElementOfOfficeBuilding newElem = new ElementOfOfficeBuilding(newFloor, headFloor, headFloor);
            getElementOfList(getCountFloors() - 1).setNext(newElem);
            headFloor = newElem;
        } else if (numberFloor == getCountFloors()) {
            ElementOfOfficeBuilding previousElement = getElementOfList(getCountFloors() - 1);
//            ElementOfOfficeBuilding nextElement = getElementOfList(getCountFloors() - 1);
            ElementOfOfficeBuilding newElem = new ElementOfOfficeBuilding(newFloor,headFloor,previousElement);
//            nextElement.setNext(headFloor);
//            nextElement.setPrev(newElem);
            previousElement.setNext(newElem);
//            previousElement.setPrev(getElementOfList(getCountFloors() - 2));
            headFloor.setPrev(newElem);

        } else {
            ElementOfOfficeBuilding previousElement = getElementOfList(numberFloor - 1);
            ElementOfOfficeBuilding nextElement = getElementOfList(numberFloor);
            ElementOfOfficeBuilding newElem = new ElementOfOfficeBuilding(newFloor, nextElement, previousElement);
            previousElement.setNext(newElem);
            previousElement.setPrev(getElementOfList(numberFloor - 2));
            nextElement.setNext(headFloor);
            nextElement.setPrev(newElem);
        }
    }

    private void deleteFloor(int numberFloor) throws FloorIndexOutOfBoundsException {
        if (getCountFloors()-1 < numberFloor || numberFloor < 0 ) {
            throw new FloorIndexOutOfBoundsException("Такого этажа не существует");
        } else if (numberFloor == 0) {
            getElementOfList(getCountFloors() - 1).setNext(headFloor.getNext());
            getElementOfList(getCountFloors() - 1).setPrev(headFloor.getPrev());
            headFloor = headFloor.getNext();
        } else if (numberFloor == getCountFloors()) {
            getElementOfList(getCountFloors() - 2).setNext(headFloor);
            headFloor.setPrev(getElementOfList(getCountFloors() - 2));
        } else {
            ElementOfOfficeBuilding previousElement = getElementOfList(numberFloor - 1);
            ElementOfOfficeBuilding nextElement = getElementOfList(numberFloor + 1);
            previousElement.setNext(nextElement);
            nextElement.setPrev(previousElement);

        }
    }
    public boolean equals(Object object){
        if (object instanceof OfficeBuilding){
            OfficeBuilding officeBuilding = ((OfficeBuilding) object);
            if(officeBuilding.getCountFloors() == getCountFloors() && officeBuilding.getArrayFloor() == getArrayFloor())
                return true;
            return  false;
        }
        return false;
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OfficeBuilding " + getCountFloors() + " ( " + headFloor.getCurrentElement().toString());
        ElementOfOfficeBuilding floor = headFloor.getNext();
        int index = 1;
        while (floor != headFloor && floor != null)
        {
            builder.append( floor.getCurrentElement().toString() );
            floor = floor.getNext();
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
        int[] arrayCountOffices = new int[getCountFloors()];
        for (int i = 0; i < getCountFloors() ; i++) {
            arrayCountOffices[i] = getFloor(i).getCountSpace();
        }
        OfficeBuilding clone = new OfficeBuilding(getCountFloors(), arrayCountOffices);
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
