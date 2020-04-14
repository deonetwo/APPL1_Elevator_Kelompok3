package controller;

import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Set;

import component.Passenger;
import component.Request;

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
        //it.remove();
        return head;
    }

    public Set<Passenger> getPassengerQueue() {
        return this.passengerQueue;
    }

    //checkForAvailableCabs()   ??????? Buat naon ieu
}