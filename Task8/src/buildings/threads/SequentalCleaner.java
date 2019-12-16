package buildings.threads;

import buildings.Floor;

public class SequentalCleaner implements Runnable {
    private static final String ThreadName = "Cleaner";
    private Floor floor;
    private SequentialSemaphore semaphore;

    public SequentalCleaner(Floor floor, SequentialSemaphore semaphore){
        this.semaphore = semaphore;
        this.floor = floor;
    }

    @Override
    public void run() {
        try {
            int length = floor.getCountSpace();
            for (int i = 0; i < length ; i++) {
                try {
                    semaphore.acquire(ThreadName);
                    System.out.println("Cleaning room number " + i + " with total area " + floor.getSpace(i).getSquare()+ " square meters");
                } finally {
                    semaphore.release(ThreadName);
                }
            }
        }catch (Exception e) {
            System.out.println("Cleaning break! Error!");
        }
        finally {
            System.out.println("Thread finish!");
        }
    }
}
