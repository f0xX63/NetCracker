package buildings.office;

import buildings.Floor;

import java.io.Serializable;

public class ElementOfOfficeBuilding implements Serializable {
    private Floor NodeFloor;
    private ElementOfOfficeBuilding nextFloor;
    private ElementOfOfficeBuilding prevFloor;

    public ElementOfOfficeBuilding(Floor NodeOffice, ElementOfOfficeBuilding next, ElementOfOfficeBuilding prev){
        this.NodeFloor = NodeOffice;
        this.nextFloor = next;
        this.prevFloor = prev;
    }

    public Floor getCurrentElement(){
        return NodeFloor;
    }

    public void setCurrentElement(Floor e){
        NodeFloor = e;
    }

    public ElementOfOfficeBuilding getNext() {
        return nextFloor;
    }

    public void setNext(ElementOfOfficeBuilding n) {
        nextFloor = n;
    }

    public ElementOfOfficeBuilding getPrev() {
        return prevFloor;
    }

    public void setPrev(ElementOfOfficeBuilding prevFloor) {
        this.prevFloor = prevFloor;
    }

    @Override
    public String toString() {
        return NodeFloor.toString();
    }

}
