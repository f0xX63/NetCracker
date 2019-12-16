package buildings;
import java.util.Comparator;

public class FloorCriterion implements Comparator<Floor> {

    @Override //чем меньше общая площадь, тем больше этаж, поэтому 1 и -1 поменяла местами
    public int compare(Floor o1, Floor o2) {
        if (o1.getTotalSquareOnFloor() == o2.getTotalSquareOnFloor()){
            return 0;
        } else if(o1.getTotalSquareOnFloor() < o2.getTotalSquareOnFloor()){
            return 1;
        } else return -1;
    }
}
