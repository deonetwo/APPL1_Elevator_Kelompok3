package controller;

import component.LoadSensor;
import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Set;

import component.Passenger;
import component.PositionMarkerSensor;
//import component.Request;

/**
 * credit : Mufqi, Rayhan Azka
 */

public class PassengerDispatcher {
    private Set<Passenger> passengerQueue;
    private LoadSensor loadSensor;

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
                loadSensor.addWeight(-current.getWeight());
                it.remove();
                
            } else if(current.isStatus() == false && 
                current.getSourceFloor().getFloorNumber() == 
                positionMarkerSensor.MarkerDetected().getFloorNumber()) {
                if(loadSensor.WeightChanged(current) != loadSensor.OverWeight){
                    System.out.println("Passenger "+ current.getId() + " has entered the cab");
                    current.setStatus(true);
                }
            }
        }
        //System.out.println();
    }

    public Set<Passenger> getPassengerQueue() {
        return this.passengerQueue;
    }

    public void setLoadSensor(LoadSensor loadSensor){
        this.loadSensor = loadSensor;
    }
    //checkForAvailableCabs()   ??????? Buat naon ieu
}