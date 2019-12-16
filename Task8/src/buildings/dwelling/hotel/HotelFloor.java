package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.FloorIterator;
import buildings.Space;
import buildings.SpaceIndexOutOfBoundsException;
import buildings.dwelling.DwellingFloor;

import java.util.Iterator;

public class HotelFloor extends DwellingFloor {
    private static final int defaultCountStars = 1;
    private static int countStars;

    public HotelFloor(int countSpace) throws SpaceIndexOutOfBoundsException{
        super(countSpace);
        countStars = defaultCountStars;
    }

    public HotelFloor(Space[] flats) {
        super(flats);
        countStars = defaultCountStars;
    }
    public int getCountStars() {
        return countStars;
    }

    public void setCountStars( int countStars){
        this.countStars = countStars;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("HotelFloor " + " " + "(" + getCountStars() + " " + getCountSpace() + " ");
        for (int i = 0; i < getCountSpace() ; i++) {
            builder.append(getSpace(i).toString());
        }
        builder.append(" )");
        return builder.toString();
    }

    public boolean equals(Object object) {
        if (object instanceof HotelFloor) {
            HotelFloor hotelFloor = ((HotelFloor) object);
            if (hotelFloor.getCountSpace() == getCountSpace() && hotelFloor.getSpaces() == getSpaces())
                return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = getCountSpace() ^ getCountStars() ^ getSpace(0).hashCode();
        for (int i = 1; i < getCountSpace(); i++) {
            hashCode = hashCode ^ getCountStars() ^ getSpace(i).hashCode();
        }
        return hashCode;
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
