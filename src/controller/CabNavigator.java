package controller;

import java.util.Iterator;

import component.*;

/**
 * credit : Mufqi, Rayhan Azka
 */

public class CabNavigator {
    private int speed;
    private String direction;
    private Passenger passenger;
    private ElevatorEngine elevatorEngine;
    private DirectionDisplay directionDisplay;
    private FloorNumberDisplay floorNumberDisplay;
    private PositionMarkerSensor positionMarkerSensor;
    private DoorOperator doorOperator;

    public CabNavigator(int speed, ElevatorEngine eEngine, DirectionDisplay dDisplay, FloorNumberDisplay fnDisplay,
            PositionMarkerSensor pmSensor, DoorOperator dOperator) {
        this.speed = speed;
        this.elevatorEngine = eEngine;
        this.directionDisplay = dDisplay;
        this.floorNumberDisplay = fnDisplay;
        this.positionMarkerSensor = pmSensor;
        this.doorOperator = dOperator;
    }

    public void moveToFloor(Passenger passenger, PassengerDispatcher list) {
        this.passenger = passenger;
        Iterator<Passenger> it;
        Passenger current;
        while (!isCabAtDestinationFloor()) {
            calculateNewSpeed();
            floorNumberDisplay.show(positionMarkerSensor.MarkerDetected());
            calculateNewDirection();
            elevatorEngine.move(this.speed, this.direction);
            directionDisplay.show(elevatorEngine.getDirection());
            calculateNewPosition();

            it = list.getPassengerQueue().iterator();
            while (it.hasNext()) {
                current = it.next();
                if (current.isStatus() == true && current.getDestination().getFloorNumber() == positionMarkerSensor
                        .MarkerDetected().getFloorNumber()) {
                    calculateNewSpeed();
                    System.out.println(
                            "Elevator Stopped at Floor " + positionMarkerSensor.MarkerDetected().getFloorNumber());
                    doorOperator.doorOpened();
                    list.passengerAction(positionMarkerSensor);
                    doorOperator.doorClosed();
                    System.out.print("\n");
                    break;
                } else if ((current.isStatus() == false && current.getSourceFloor()
                        .getFloorNumber() == positionMarkerSensor.MarkerDetected().getFloorNumber())) {
                    calculateNewSpeed();
                    System.out.println(
                            "Elevator Stopped at Floor " + positionMarkerSensor.MarkerDetected().getFloorNumber());
                    doorOperator.doorOpened();
                    list.passengerAction(positionMarkerSensor);
                    doorOperator.doorClosed();
                    System.out.print("\n");
                    break;
                }
            }
        }
    }

    public void calculateNewSpeed() {
        if (this.speed == 0) {
            this.speed += 20;
        } else {
            this.speed = 0;
        }
    }

    void calculateNewDirection() {
        int floorForCabToMove;
        if (passenger.isStatus() == false) {
            floorForCabToMove = passenger.getSourceFloor().getFloorNumber();
        } else {
            floorForCabToMove = passenger.getDestination().getFloorNumber();
        }

        if (floorForCabToMove > positionMarkerSensor.MarkerDetected().getFloorNumber()) {
            this.direction = "up";
        } else if (floorForCabToMove < positionMarkerSensor.MarkerDetected().getFloorNumber()) {
            this.direction = "down";
        }
    }

    void calculateNewPosition() {
        Request newFloor;
        if (this.direction.equals("up")) {
            newFloor = FloorRequest.pressed(positionMarkerSensor.MarkerDetected().getFloorNumber() + 1);
            positionMarkerSensor.setPosition(newFloor);
        } else if (this.direction.equals("down")) {
            newFloor = FloorRequest.pressed(positionMarkerSensor.MarkerDetected().getFloorNumber() - 1);
            positionMarkerSensor.setPosition(newFloor);
        }
    }

    public boolean isCabAtDestinationFloor() {
        int floorForCabToMove;
        if (passenger.isStatus() == false) {
            floorForCabToMove = passenger.getSourceFloor().getFloorNumber();
        } else {
            floorForCabToMove = passenger.getDestination().getFloorNumber();
        }
        return floorForCabToMove == positionMarkerSensor.MarkerDetected().getFloorNumber();
    }

    public void setPositionMarkerSensor(PositionMarkerSensor positionMarkerSensor) {
        this.positionMarkerSensor = positionMarkerSensor;
    }
}
