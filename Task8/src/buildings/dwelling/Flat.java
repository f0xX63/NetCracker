package buildings.dwelling;

import buildings.InvalidRoomsCountException;
import buildings.InvalidSpaceAreaException;
import buildings.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable, Comparable<Space> {

    private int countRoom;
    private double square;

    public Flat() {
        this.countRoom = 2;
        this.square = 50;
    }

    public Flat(double square) throws InvalidSpaceAreaException {
        checkSquare(square);
        this.countRoom = 2;
        this.square = square;
    }

    public Flat(int countRoom, double square) throws InvalidSpaceAreaException, InvalidRoomsCountException {
        checkSquare(square);
        checkCountRoom(countRoom);
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
        checkCountRoom(countRoom);
        this.countRoom = countRoom;
    }

    public void setSquare(double square) throws InvalidSpaceAreaException{
        checkSquare(square);
        this.square = square;
    }

    public boolean equals(Object object){
      if (object instanceof Flat){
          Flat flat = ((Flat) object);
          if(flat.getCountRoom() == countRoom && flat.getSquare()== square)
              return true;
          return  false;
      }
      return false;
    }

    public String toString() {
        return "Flat" + "( " + countRoom + ", " + square + " )";
    }

    public int hashCode(){
        int res = (int)square; //целая часть
        double res2 = square % 1; //дробная часть
        int res3 = (int)(res2 *10000);
        return countRoom^res^res3;
    }

    public Object clone() {
        return new Flat(countRoom, square);
        //return CloneHelper.cloneObject(this);
    }

    private void checkSquare(double newSquare) {
        if (newSquare <= 0 ) {
            throw new InvalidSpaceAreaException("Не верное значение площади!");
        }
    }

    private void checkCountRoom(int newCountRoom) {
        if (newCountRoom <= 0 ) {
            throw new InvalidSpaceAreaException("Не верное количество комнат!");
        }
    }

    public int compareTo(Space space){
        if (getSquare() == space.getSquare()){
            return 0;
        } else if(getSquare() < space.getSquare()){
            return -1;
        } else return 1;
    }
}
