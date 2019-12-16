package buildings.threads;

import buildings.Floor;

public class SequentalRepairer implements Runnable {
    private static final String ThreadName = "Repairer";
    private Floor floor;
    private SequentialSemaphore semaphore;
    public SequentalRepairer(Floor floor, SequentialSemaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            int length = floor.getCountSpace();
            for (int i = 0; i < length ; i++) {
                try {
                    semaphore.acquire(ThreadName);
                    System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getSquare()+ " square meters");
                } finally {
                    semaphore.release(ThreadName);
                }
            }
        }catch (Exception e) {
            System.out.println("Repairing break! Error!");
        }
        finally {
            System.out.println("Thread finish!");
        }
    }
}
