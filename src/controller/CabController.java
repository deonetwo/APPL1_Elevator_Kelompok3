package controller;

import component.*;

/**
 * credit : Dewanto, Mufqi, Rayhan Azka
 */

public class CabController {
    private PassengerDispatcher passengerDispatcher;
    private CabNavigator cabNavigator;
    private LoadSensor loadSensor;
    private LoadBell loadBell;

    public CabController(PassengerDispatcher passengerDispatcher, CabNavigator cabNavigator, int safetyLimit) {
        this.passengerDispatcher = passengerDispatcher;
        this.cabNavigator = cabNavigator;
        this.loadSensor = new LoadSensor(safetyLimit, new LoadBell());
        passengerDispatcher.setLoadSensor(loadSensor);
    }

    public void processRequest(Passenger pass) {
        cabNavigator.moveToFloor(pass, passengerDispatcher);
    }

    public void turnLightOff(Request floor) {
        floor.turnLightOff();
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
}