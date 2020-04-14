package controller;

//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Set;

import component.Passenger;

public class PassengerLogger {
    private Set<Passenger> passengerQueue;

    public PassengerLogger(Set<Passenger> passengerQueue) {
        this.passengerQueue = passengerQueue;
    }

    public void AddPassengerToQueue(Passenger passenger){
        this.passengerQueue.add(passenger);
    }

    public Set<Passenger> getPassengerQueue(){
        return this.passengerQueue;
    }
}