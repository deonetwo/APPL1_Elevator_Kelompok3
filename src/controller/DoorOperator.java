package controller;
import component.*;

/**
 *
 * @author Fatharani
 */
public class DoorOperator {
    DoorOpeningDevice door;
    
    public DoorOperator (DoorOpeningDevice door){
        this.door = door;
    }
    
    void doorOpened() {
        door.openDoors();
        System.out.println("Doors Opened");
    }
    
    void doorClosed() {
        door.closeDoors();
        System.out.println("Doors Closed");
    }
}