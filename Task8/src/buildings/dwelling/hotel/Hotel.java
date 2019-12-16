package buildings.dwelling.hotel;

import buildings.*;
import buildings.dwelling.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Hotel extends Dwelling {

    public Hotel(int countFloors, int[] countFlatsOnFloor) throws FloorIndexOutOfBoundsException {
        super(countFloors, countFlatsOnFloor);
    }

    public Hotel(Floor[] floors) {
        super(floors);
    }

    public int getCountStars(){
        int currentCountStars = 0;
        for (int i = 0; i < getCountFloors() ; i++) {
            Floor getFloor = getFloor(i);
            if (getFloor instanceof HotelFloor){
                HotelFloor hotelFloor = (HotelFloor) getFloor(i);
                if(hotelFloor.getCountStars() > currentCountStars)
                currentCountStars = hotelFloor.getCountStars();
            }
        }
        return currentCountStars;
    }

    public Space getBestSpace(){
//        double[] indicator = new double[getCountSpaces()];
//        HashMap<Integer, Double> numberAndIndicator = new HashMap<>();

        int countFloors = getCountFloors();
        int index = 0;
        while (!(getFloor(index) instanceof HotelFloor) && index < countFloors)
        {
            index++;
        }
        if (index == countFloors)
        {
            // нет этажей отелей
        }
        Space bestSpace = getFloor(index).getSpace(0);
        double bestKoef = bestSpace.getSquare() * getCoeff(((HotelFloor)getFloor(index)).getCountStars());
        for (int i = 0; i < countFloors ; i++) {
            Floor floor = getFloor(i);
            if (floor instanceof HotelFloor)
            {
                int countStar = ((HotelFloor) floor).getCountStars();
                double koef = getCoeff(countStar);
                int countSpaceOnFloor = floor.getCountSpace();
                for (int j = 0; j < countSpaceOnFloor; j++) {
                    Space currentSpace = floor.getSpace(j);
                    double currentKoef = currentSpace.getSquare() * koef;
                    if (bestKoef < currentKoef)
                    {
                        bestKoef = currentKoef;
                        bestSpace = currentSpace;
                    }
                }
            }
        }

        return  bestSpace;
    }


    private  double getCoeff(int countStars){
        double coeff = 0;
        switch (countStars) {
            case (1):
                coeff = 0.25;
                return coeff;
            case (2):
                coeff = 0.5;
                return coeff;
            case (3):
                coeff = 1;
                return coeff;
            case (4):
                return 1.25;
            case (5):
                coeff = 1.5;
                return coeff;
            default:
                throw new IllegalArgumentException();
        }

    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = getCountFloors()-1; i >= 0; i--) {
            builder.append("Hotel " + " ( " + getCountStars() + " " + getCountFloors() + " ( " + getFloor(i).toString() + " ) " );
        }
        return builder.toString();
    }

    public boolean equals(Object object){
        if (object instanceof Hotel){
            Hotel hotel = ((Hotel) object);
            if(hotel.getCountFloors() == getCountFloors() && hotel.getArrayFloor() == getArrayFloor())
                return true;
            return  false;
        }
        return false;
    }

    public int hashCode(){
        int hashCode = getCountFloors() ^ getFloor(0).hashCode();
        for (int i = 1; i < getCountFloors() ; i++) {
            hashCode = hashCode^getFloor(i).hashCode();
        }
        return hashCode;
    }

    public Iterator<Space> iterator() {
        return new BuildingIterator(this);
    }
}
