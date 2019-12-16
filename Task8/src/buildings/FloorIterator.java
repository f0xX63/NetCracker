package buildings;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class FloorIterator implements Iterator<Space> {
    private Floor floor;
    private int size;
    private int currentPointer = 0;
    public FloorIterator(Floor floor){
        this.floor = floor;
        this.size = floor.getCountSpace();
    }

    @Override
    public boolean hasNext() {
        return (currentPointer < size);
    }

    @Override
    public Space next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Space space = floor.getSpace(currentPointer++);
        return space;
    }

}
