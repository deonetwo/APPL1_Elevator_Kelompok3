package controller;

import component.*;

/**
 * credit : Dewanto, Mufqi, Rayhan Azka
 */

public class CabController {
    private PassengerDispatcher passengerDispatcher;
    private int safetyLimit;
    private CabNavigator cabNavigator;
    private LoadSensor loadSensor;
    private LoadBell loadBell;

    public CabController(PassengerDispatcher passengerDispatcher, CabNavigator cabNavigator, int safetyLimit) {
        this.passengerDispatcher = passengerDispatcher;
        this.cabNavigator = cabNavigator;
        this.safetyLimit = safetyLimit;
        this.loadSensor = new LoadSensor();
        this.loadBell = new LoadBell();
    }

    public void processRequest(Passenger pass) {
        cabNavigator.moveToFloor(pass, passengerDispatcher);
        loadSensor.addWeight(-(pass.getWeight())); // nanti direfactor bentar..
    }

    public void turnLightOff(Request floor) {
        floor.turnLightOff();
    }

    public void addWeight(int weight) {
        loadSensor.addWeight(weight);
    }

    public boolean WeightChanged(Passenger pass) {
        loadSensor.addWeight(pass.getWeight());
        if (loadSensor.getWeight() <= safetyLimit) {
            return loadSensor.inSafety;
        } else {
            loadBell.Ring();
            loadSensor.addWeight(-(pass.getWeight()));
            System.out.println("Passenger with id " + pass.getId() + " cannot entered the cab.");
            return loadSensor.OverWeight;
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

    public LoadSensor getLoadSensor() {
        return this.loadSensor;
    }

    public LoadBell getLoadBell() {
        return this.loadBell;
    }
}
