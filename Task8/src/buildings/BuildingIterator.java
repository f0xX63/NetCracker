package buildings;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class BuildingIterator implements Iterator<Space> {
    private Building building;
    private int size;
    private int currentPointer = 0;

    public BuildingIterator(Building building) {
        this.building = building;
        this.size = building.getCountFloors();
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
        Space space = building.getSpace(currentPointer++);
        return space;
    }
}