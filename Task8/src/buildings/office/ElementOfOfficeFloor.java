package buildings.office;

import buildings.Space;

import java.io.Serializable;

public class ElementOfOfficeFloor implements Serializable {
    private Space NodeOffice;
    private ElementOfOfficeFloor nextOffice;

    public ElementOfOfficeFloor(Space NodeOffice, ElementOfOfficeFloor next){
        this.NodeOffice = NodeOffice;
        this.nextOffice = next;
    }

    public Space getCurrentElement(){
        return NodeOffice;
    }

    public void setCurrentElement(Space e){
        NodeOffice = e;
    }

    public ElementOfOfficeFloor getNext() {
        return nextOffice;
    }

    public void setNext(ElementOfOfficeFloor n) {
        nextOffice = n;
    }

    @Override
    public String toString() {
        return NodeOffice.toString();
    }
}


