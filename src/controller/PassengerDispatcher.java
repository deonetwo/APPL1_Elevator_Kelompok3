package controller;

import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Set;

import component.Passenger;
import component.PositionMarkerSensor;
//import component.Request;

public class PassengerDispatcher {
    private Set<Passenger> passengerQueue;

    public PassengerDispatcher(Set<Passenger> passengerQueue) {
        this.passengerQueue = passengerQueue;
    }

    public boolean checkQueueForPassenger() {
        return (passengerQueue.isEmpty()) ? true : false;
    }

    public Passenger determineNextPassengerToProcess() {
        Iterator<Passenger> it = passengerQueue.iterator();
        Passenger head = it.next();
        return head;
    }

    public void passengerAction(PositionMarkerSensor positionMarkerSensor) {
        Iterator<Passenger> it = passengerQueue.iterator();
        Passenger current;

        while(it.hasNext()) {
            current = it.next();
            if(current.isStatus() == true &&
                current.getDestination().getFloorNumber() == 
                positionMarkerSensor.MarkerDetected().getFloorNumber()) {
                System.out.println("Passenger "+ current.getId() + " has arrived at destination");
                it.remove();
                
            } else if(current.isStatus() == false && 
                current.getSourceFloor().getFloorNumber() == 
                positionMarkerSensor.MarkerDetected().getFloorNumber()) {
                System.out.println("Passenger "+ current.getId() + " has entered the cab");
                current.setStatus(true);
            }
        }
        //System.out.println();
    }

    public Set<Passenger> getPassengerQueue() {
        return this.passengerQueue;
    }

    //checkForAvailableCabs()   ??????? Buat naon ieu
}