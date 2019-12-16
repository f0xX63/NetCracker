package buildings.office;


import buildings.InvalidRoomsCountException;
import buildings.InvalidSpaceAreaException;
import buildings.Space;

import java.io.*;

public class Office implements Space, Serializable, Comparable<Space> {
    private int countRoom;
    private double square;

    public Office() {
        this.countRoom = 1;
        this.square = 250;
    }

    public Office(double square) throws InvalidSpaceAreaException {
        if (square <= 0 ) {
            throw new InvalidSpaceAreaException("Не верное значение площади!");
        }
        this.countRoom = 1;
        this.square = square;
    }

    public Office(int countRoom, double square) throws InvalidSpaceAreaException, InvalidRoomsCountException {
        if (square <= 0 ) {
            throw new InvalidSpaceAreaException("Не верное значение площади!");
        }
        if (countRoom <= 0 ) {
            throw new InvalidSpaceAreaException("Не верное количество комнат!");
        }
        this.countRoom = countRoom;
        this.square = square;
    }

    public int getCountRoom() {
        return countRoom;
    }

    public double getSquare() {
        return square;
    }

    public void setCountRoom(int countRoom) throws InvalidRoomsCountException {
        if (countRoom <= 0 ) {
            throw new InvalidSpaceAreaException("Не верное количество комнат!");
        }
        this.countRoom = countRoom;

    }

    public void setSquare(double square) throws InvalidSpaceAreaException {
        if (square <= 0 ) {
            throw new InvalidSpaceAreaException("Не верное значение площади!");
        }
        this.square = square;
    }

    public boolean equals(Object object){
        if (object instanceof Office){
            Office office = ((Office) object);
            if(office.getCountRoom() == countRoom && office.getSquare()== square)
                return true;
            return  false;
        }
        return false;
    }


    public Object clone(){
        return new Office(countRoom, square);
        //return CloneHelper.cloneObject(this);
    }

    public int hashCode(){
        int res = (int)square; //целая часть
        double res2 = square % 1; //дробная часть
        int res3 = (int)(res2 *10000);
        return countRoom^res^res3;
    }

    @Override
    public String toString() {
        return "Office" + "( " + countRoom + ", " + square + " )";
    }

    public int compareTo(Space space){
        if (getSquare() == space.getSquare()){
            return 0;
        } else if(getSquare() < space.getSquare()){
            return -1;
        } else return 1;
    }
}

