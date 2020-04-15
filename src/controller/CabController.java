package controller;

import component.*;

public class CabController {
    private PassengerDispatcher passengerDispatcher;
    private int safetyLimit;
    private CabNavigator cabNavigator;
    public LoadSensor loadSensor;
    public LoadBell loadBell;

    public CabController(PassengerDispatcher passengerDispatcher,
        CabNavigator cabNavigator, int safetyLimit) {
        this.passengerDispatcher = passengerDispatcher;
        this.cabNavigator = cabNavigator;
        this.safetyLimit = safetyLimit;
        this.loadSensor = new LoadSensor();
        this.loadBell = new LoadBell();
    }

    public void processRequest(Passenger floor) {
        cabNavigator.moveToFloor(floor, passengerDispatcher);
    }

    public void startOperation() {

    }

    public void turnLightOff(Request floor) {
        floor.turnLightOff();
    }

    public void addWeight(int weight) {
        loadSensor.addWeight(weight);
    }

    public void WeightChanged(int newWeight) {
        if(loadSensor.getWeight() <= safetyLimit){
            startOperation();
        }
        else{
            loadBell.Ring();
        }
    }

    public PassengerDispatcher getRequestDispatcher() {
        return passengerDispatcher;
    }

    public void setRequestDispatcher(PassengerDispatcher requestDispatcher) {
        this.passengerDispatcher = requestDispatcher;
    }

    public CabNavigator getCabNavigator() {
        return cabNavigator;
    }

    public void setCabNavigator(CabNavigator cabNavigator) {
        this.cabNavigator = cabNavigator;
    }
}
