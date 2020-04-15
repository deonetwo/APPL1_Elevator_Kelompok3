package controller;
import component.*;
//import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatharani
 */
public class DoorOperator {
    DoorOpeningDevice door;
    //int currentFloor;
    
    public DoorOperator (/*PositionMarkerSensor poition, */DoorOpeningDevice door){
        this.door = door;
        //this.currentFloor = 0;
    }
    
    // void startOperation(PositionMarkerSensor position) {
    //     //int i;
    //      if (currentFloor == position.MarkerDetected().getFloorNumber()){
    //         doorProcess();
    //      }
    //      else {
    //          currentFloor = position.MarkerDetected().getFloorNumber();
    //          doorProcess();
    //     }
    // }
    
    void doorOpened() {
        door.openDoors();
        System.out.println("Doors Opened");
    }
    
    void doorClosed() {
        door.closeDoors();
        System.out.println("Doors Closed");
    }
   
    // void doorProcess(){
    //     try {
    //         doorOpened();
    //         Thread.sleep(5000);
    //         }
    //     catch (InterruptedException ex) {
    //         Logger.getLogger(DoorOperator.class.getName()).log(Level.SEVERE, null, ex);
    //         }
    //     doorClosed();
    // }
}