package controller;

import component.*;


public class CabNavigator {
    private int speed;
    private String direction;
    private FloorRequest floorRequest;
    private ElevatorEngine elevatorEngine;
    private DirectionDisplay directionDisplay;
    private FloorNumberDisplay floorNumberDisplay;
    private PositionMarkerSensor positionMarkerSensor;
    //private boolean jalanga;

    public CabNavigator(int speed, ElevatorEngine eEngine, DirectionDisplay dDisplay,
                            FloorNumberDisplay fnDisplay, PositionMarkerSensor pmSensor) {
        this.speed = speed;
        //this.direction = direction;
        this.elevatorEngine = eEngine;
        this.directionDisplay = dDisplay;
        this.floorNumberDisplay = fnDisplay;
        this.positionMarkerSensor = pmSensor;
    }

    void moveToFloor(FloorRequest floor) {
        this.floorRequest = floor;
        //positionMarkerSensor.setPosition(floorRequest);
        calculateNewSpeed();
        while(!isCabAtDestinationFloor()){
            floorNumberDisplay.show(positionMarkerSensor.MarkerDetected());
            calculateNewDirection();
            //calculateNewSpeed();
            elevatorEngine.move(this.speed, this.direction);
            directionDisplay.show(elevatorEngine.getDirection());
            calculateNewPosition();
        }
        calculateNewSpeed();
    }

    void calculateNewSpeed() {
        if(this.speed == 0) {
            this.speed += 20;
        } else {
            this.speed = 0;
        }
    }

    void calculateNewDirection() {
        if(floorRequest.getFloorNumber()>positionMarkerSensor.MarkerDetected().getFloorNumber()){
            this.direction = "up";
        }
        else if(floorRequest.getFloorNumber()<positionMarkerSensor.MarkerDetected().getFloorNumber()){
            this.direction = "down";
        }
    }

    void calculateNewPosition() {
        FloorRequest newFloor;
        if(this.direction.equals("up")){
            newFloor = FloorRequest.pressed(positionMarkerSensor.MarkerDetected().getFloorNumber() + 1);
            positionMarkerSensor.setPosition(newFloor);
        }
        else if(this.direction.equals("down")){
            newFloor = FloorRequest.pressed(positionMarkerSensor.MarkerDetected().getFloorNumber() - 1);
            positionMarkerSensor.setPosition(newFloor);
        }
    }

    boolean isCabAtDestinationFloor() {
        return floorRequest.getFloorNumber() == positionMarkerSensor.MarkerDetected().getFloorNumber();
    }
}
