package controller;
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
        this.elevatorEngine = eEngine;
        this.directionDisplay = dDisplay;
        this.floorNumberDisplay = fnDisplay;
        this.positionMarkerSensor = pmSensor;
    }

    public void moveToFloor(Passenger passenger, PassengerDispatcher list) {
        this.passenger = passenger;
        calculateNewSpeed();
        while(!isCabAtDestinationFloor()) {
            floorNumberDisplay.show(positionMarkerSensor.MarkerDetected());
            calculateNewDirection();
            elevatorEngine.move(this.speed, this.direction);
            directionDisplay.show(elevatorEngine.getDirection());
            calculateNewPosition();
            list.passengerAction(positionMarkerSensor);
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
