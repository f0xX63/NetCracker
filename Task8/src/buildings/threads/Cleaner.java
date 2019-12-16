package buildings.threads;

import buildings.Floor;

public class Cleaner extends Thread {
    private Floor floor;

    public Cleaner(Floor floor){
        this.floor = floor;
    }

    @Override
    public void run() {
        try {
            int length = floor.getCountSpace();
            for (int i = 0; i < length ; i++) {
                System.out.println("Cleaning room number " + i + " with total area " + floor.getSpace(i).getSquare()+ " square meters");
            }
        }catch (Exception e) {
            System.out.println("Cleaning break! Error!");
        }
        finally {
            System.out.println("Thread finish!");
        }
    }
}
