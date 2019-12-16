package buildings.threads;

import java.util.concurrent.Semaphore;

public class SequentialSemaphore extends Semaphore {
    private boolean isClean;
    private boolean isRepair;

    public void acquire(String threadName) throws InterruptedException {
        if (threadName == "Cleaner")
        {
            while (!isRepair) { Thread.sleep(10); }
            super.acquire();
        }
        if (threadName == "Repairer")
        {
            while (!isClean) { Thread.sleep(10);}
            super.acquire();
        }
    }

    public void release(String threadName) {
        if (threadName == "Cleaner")
        {
            isClean = true;
            isRepair = false;
            super.release();
        }
        if (threadName == "Repairer")
        {
            isRepair = true;
            isClean = false;
            super.release();
        }
    }

    public SequentialSemaphore() {
        super(1);
        isClean = true;
        isRepair = false;
    }


}
