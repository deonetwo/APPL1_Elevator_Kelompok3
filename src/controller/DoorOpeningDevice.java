/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import component.*;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;

    /**
     * penanggung jawab: Fatharani
     */

public class DoorOpeningDevice {
    DoorOperator door;
    FloorRequest floor;
    boolean isOpen;
    
    public DoorOpeningDevice(DoorOperator door, FloorRequest floor, int currentFloor) {
        this.door = door;
        this.floor =  floor;
    }
   
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
