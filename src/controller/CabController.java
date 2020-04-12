package controller;

import component.FloorRequest;

public class CabController {
    private RequestDispatcher requestDispatcher;
    //private FloorRequest floorRequest;
    private int weight;
    private CabNavigator cabNavigator;

    public CabController(RequestDispatcher requestDispatcher,
         int weight, CabNavigator cabNavigator) {
        this.requestDispatcher = requestDispatcher;
        //this.floorRequest = floorRequest;
        this.weight = weight;
        this.cabNavigator = cabNavigator;    
    }

    public void processRequest(FloorRequest floor) {
        cabNavigator.moveToFloor(floor);
    }

    public void startOperation(){

    }

    public void turnLightOff(FloorRequest floor){
        floor.turnLightOff();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    
}

