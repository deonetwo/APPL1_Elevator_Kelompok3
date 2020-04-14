package controller;

import java.util.Iterator;
import java.util.Set;

import component.*;


public class CabNavigator {
    private int speed;
    private String direction;
    private Passenger passenger;
    private ElevatorEngine elevatorEngine;
    private DirectionDisplay directionDisplay;
    private FloorNumberDisplay floorNumberDisplay;
    private PositionMarkerSensor positionMarkerSensor;

    public CabNavigator(int speed, ElevatorEngine eEngine, DirectionDisplay dDisplay,
                            FloorNumberDisplay fnDisplay, PositionMarkerSensor pmSensor) {
        this.speed = speed;
        //this.direction = direction;
        this.elevatorEngine = eEngine;
        this.directionDisplay = dDisplay;
        this.floorNumberDisplay = fnDisplay;
        this.positionMarkerSensor = pmSensor;
    }

    void moveToFloor(Passenger passenger, PassengerDispatcher list) {
        this.passenger = passenger;
        Iterator<Passenger> it = list.getPassengerQueue().iterator();
        Passenger current;
        calculateNewSpeed();
        while(!isCabAtDestinationFloor()) {
            floorNumberDisplay.show(positionMarkerSensor.MarkerDetected());
            calculateNewDirection();
            elevatorEngine.move(this.speed, this.direction);
            directionDisplay.show(elevatorEngine.getDirection());
            calculateNewPosition();
            System.out.println("Elevator Stopped at Floor " + 
                positionMarkerSensor.MarkerDetected().getFloorNumber());

            while(it.hasNext()) {
                current = it.next();
                if(current.isStatus() == true &&
                    current.getDestination().getFloorNumber() == 
                    positionMarkerSensor.MarkerDetected().getFloorNumber()) {
                    System.out.println("Passenger "+current.getId()+" has arrived at destination");
                    it.remove();
                    
                } else if(current.isStatus() == false && 
                    current.getSourceFloor().getFloorNumber() == 
                    positionMarkerSensor.MarkerDetected().getFloorNumber()) {
                    System.out.println("Passenger "+current.getId()+" has entered the cab");
                    current.setStatus(true);
                }
            }

            System.out.println();
        }
        calculateNewSpeed();
    }

    void calculateNewSpeed() {
        if(this.speed == 0) {
            this.speed += 20;
        }
        else{
            this.speed = 0;
        }
    }

    void calculateNewDirection() {
        if(passenger.getDestination().getFloorNumber() > positionMarkerSensor.MarkerDetected().getFloorNumber()){
           this.direction = "up";
        }
        else if(passenger.getDestination().getFloorNumber() < positionMarkerSensor.MarkerDetected().getFloorNumber()){
            this.direction = "down";
        }
    }

    void calculateNewPosition() {
        Request newFloor;
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
        return passenger.getDestination().getFloorNumber() == positionMarkerSensor.MarkerDetected().getFloorNumber();
    }

    public PositionMarkerSensor getPositionMarkerSensor() {
        return positionMarkerSensor;
    }

    public void setPositionMarkerSensor(PositionMarkerSensor positionMarkerSensor) {
        this.positionMarkerSensor = positionMarkerSensor;
    }
}
