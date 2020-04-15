package component;
//import component.*;
// import java.util.TimerTask;

// import java.util.logging.Level;
// import java.util.logging.Logger;

    /**
     * penanggung jawab: Fatharani
     */

public class DoorOpeningDevice {
    // DoorOperator door;
    // FloorRequest floor;
    boolean isOpen;
    
    // public DoorOpeningDevice(DoorOperator door, FloorRequest floor, int currentFloor) {
    //     this.door = door;
    //     this.floor =  floor;
    // }
   
    public void setDoor(boolean status){
        this.isOpen = status;
    }
    public void openDoors() {
        setDoor(true);
    }
    public void closeDoors() {
        setDoor(false);
    }
}