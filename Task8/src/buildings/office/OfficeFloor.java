package buildings.office;

import buildings.Floor;
import buildings.FloorIterator;
import buildings.Space;
import buildings.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Iterable, Comparable<Floor> {

    private ElementOfOfficeFloor headOffice;
    private int numberSpace;

//    public OfficeFloor(Office head) {
//        headOffice = new ElementOfOfficeFloor(head, null);
//    }

    public OfficeFloor(int countOffices) {
        headOffice = new ElementOfOfficeFloor(null, null);
        for (int i = 1; i < countOffices; i++) {
            //headOffice.setNext(new ElementOfList(null,null));
            insertOffice(0, null);
        }

    }

    public OfficeFloor(Space[] officesMass) {
        headOffice = new ElementOfOfficeFloor(officesMass[0], null);
        for (int i = 1; i < officesMass.length; i++) {
           insertOffice(i , officesMass[i]);
        }
    }

    public int getCountSpace() {
        int count = 0;
        if (headOffice == null)
            return count;

        ElementOfOfficeFloor temp = headOffice.getNext();
        count++;
        while (temp != headOffice && temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    public double getTotalSquareOnFloor() {
        double sum = 0;
        ElementOfOfficeFloor temp = headOffice.getNext();
        while (temp != headOffice && temp != null) {
            sum += temp.getCurrentElement().getSquare();
            temp = temp.getNext();
        }
        return sum;
    }

    public int getTotalCountRoomOnFloor() {
        int sum = 0;
        ElementOfOfficeFloor temp = headOffice.getNext();
        while (temp != headOffice && temp != null) {
            sum += temp.getCurrentElement().getCountRoom();
            temp = temp.getNext();
        }
        return sum;
    }

    public Space[] getSpaces() {
        Space[] arrayOffices = new Space[getCountSpace()];
        for (int i = 0; i < getCountSpace(); i++) {
            arrayOffices[i] = getElementOfList(i).getCurrentElement();
        }
        return arrayOffices;
    }

    public Space getSpace(int numberSpace) throws SpaceIndexOutOfBoundsException {
        return getElementOfList(numberSpace).getCurrentElement();
    }

    public void setSpace(int numberSpace, Space newSpace) throws SpaceIndexOutOfBoundsException{
         getElementOfList(numberSpace).setCurrentElement(newSpace);
    }

    public void addSpace( int numberSpace, Space newSpace) throws SpaceIndexOutOfBoundsException {
        insertOffice(numberSpace,newSpace);
    }

    public void deleteSpace(int numberSpace) throws SpaceIndexOutOfBoundsException {
        deleteOffice(numberSpace);
    }

    public Space getBestSpace(){
        Space[] arrayOffices = getSpaces();
        int n = arrayOffices.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arrayOffices[j].getSquare() > arrayOffices[j+1].getSquare())
                {
                    Space temp = arrayOffices[j];
                    arrayOffices[j] = arrayOffices[j+1];
                    arrayOffices[j+1] = temp;
                }
        return arrayOffices[n - 1];
    }

    public boolean equals(Object object){
        if (object instanceof OfficeFloor){
            OfficeFloor officeFloor = ((OfficeFloor) object);
            if(officeFloor.getCountSpace() == getCountSpace() && officeFloor.getSpaces() == getSpaces())
                return true;
            return  false;
        }
        return false;
    }

    private ElementOfOfficeFloor getElementOfList(int numberElement) throws SpaceIndexOutOfBoundsException {
        if (numberElement < 0 || numberElement > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        }
        ElementOfOfficeFloor current = headOffice;
        for (int i = 0; i < numberElement; i++) {
            current = current.getNext();
        }
        return current;
    }

    private void insertOffice(int numberOffice, Space newOffice) throws SpaceIndexOutOfBoundsException {
        if (numberOffice < 0 || numberOffice > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        }else if (numberOffice == 0) {
            ElementOfOfficeFloor newElem = new ElementOfOfficeFloor(newOffice, headOffice);
            getElementOfList(getCountSpace() - 1).setNext(newElem);
            headOffice = newElem;
        } else if (numberOffice == getCountSpace()) {
            ElementOfOfficeFloor previousElement = getElementOfList(getCountSpace() - 1);
            ElementOfOfficeFloor newElem = new ElementOfOfficeFloor(newOffice, headOffice);
            previousElement.setNext(newElem);
        } else {
            ElementOfOfficeFloor previousElement = getElementOfList(numberOffice - 1);
            ElementOfOfficeFloor nextElement = getElementOfList(numberOffice);
            ElementOfOfficeFloor newElem = new ElementOfOfficeFloor(newOffice, nextElement);
            previousElement.setNext(newElem);
        }
    }

    private void deleteOffice(int numberOffice) throws SpaceIndexOutOfBoundsException {
        if (numberOffice < 0 || numberOffice > getCountSpace()) {
            throw new SpaceIndexOutOfBoundsException("Такого номера не существует!");
        } else if (numberOffice == 0) {
            getElementOfList(getCountSpace() - 1).setNext(headOffice.getNext());
            headOffice = headOffice.getNext();
        } else if (numberOffice == getCountSpace()) {
            getElementOfList(getCountSpace() - 2).setNext(headOffice);
        } else {
            ElementOfOfficeFloor previousElement = getElementOfList(numberOffice - 1);
            ElementOfOfficeFloor nextElement = getElementOfList(numberOffice + 1);
            previousElement.setNext(nextElement);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        ElementOfOfficeFloor temp = headOffice.getNext();
        builder.append(" OfficeFloor " + " " + getCountSpace() + "  ( " + headOffice.getCurrentElement());
            while (temp != headOffice && temp != null) {
                builder.append( "  " + temp.getCurrentElement());
                temp = temp.getNext();
            }
        builder.append(" )");
        return builder.toString();

    }

    public int hashCode(){
        int hashCode = getCountSpace() ^ getSpace(0).hashCode();
        for (int i = 1; i < getCountSpace() ; i++) {
            hashCode = hashCode^getSpace(i).hashCode();
        }
        return hashCode;
    }

    public Object clone(){
        OfficeFloor clone = new OfficeFloor(getCountSpace());
        for (int i = 0; i < getCountSpace(); i++) {
            clone.setSpace(i,(Space) getSpace(i).clone());
        }
        return clone;
        //return CloneHelper.cloneObject(this);
    }

    public Iterator<Space> iterator() {
        return new FloorIterator(this);
    }

    public int compareTo(Floor floor){
        if (getCountSpace() == floor.getCountSpace()){
            return 0;
        } else if(getCountSpace() < floor.getCountSpace()){
            return -1;
        } else return 1;

    }
}
