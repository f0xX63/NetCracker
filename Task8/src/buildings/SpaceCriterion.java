package buildings;
import java.util.Comparator;

public class SpaceCriterion implements Comparator<Space>{

    @Override //чем меньше комнат, тем больше помещение, поэтому 1 и -1 поменяла местами
    public int compare(Space o1, Space o2) {
        if (o1.getCountRoom() == o2.getCountRoom()){
            return 0;
        } else if(o1.getCountRoom() < o2.getCountRoom()){
            return 1;
        } else return -1;
    }
}
