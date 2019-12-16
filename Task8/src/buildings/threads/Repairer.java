package buildings.threads;

import buildings.Floor;
import buildings.Space;

public class Repairer extends Thread {

    private Floor floor;

    public Repairer(Floor floor){
        this.floor = floor;
    }

    @Override
    public void run() {
        try {
            int length = floor.getCountSpace();
            for (int i = 0; i < length ; i++) {
                System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getSquare()+ " square meters");
            }
        }catch (Exception e) {
            System.out.println("Repairing break! Error!");
        }
        finally {
            System.out.println("Thread finish!");
       }
    }
}
