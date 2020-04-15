package controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import component.*;
import java.util.Scanner;
import java.util.Set;

/* 
    credit : Chofief, Dewanto, Mufqi , Rayhan Azka, Khoirunnisa
             Fatharani, Fajrina
*/

public class ElevatorController {
    ElevatorState onIdle;
    ElevatorState goingUp;
    ElevatorState goingDown;
    ElevatorState doorOpened;
    ElevatorState doorClosed;
    ElevatorState overWeight;

    ElevatorState elevatorState;

    public Set<Passenger> passengerQueue;
    public PassengerLogger passengerLogger;
    public PassengerDispatcher passengerDispatcher;
    public ElevatorEngine elevatorEngine;
    public CabNavigator cabNavigator;
    public CabController cabController;
    public DirectionDisplay directionDisplay;
    public FloorNumberDisplay floorNumberDisplay;
    public PositionMarkerSensor positionMarkerSensor;
    public DoorOperator doorOperator;
    public DoorOpeningDevice doorOpeningDevice;
    public static final int SAFETY_LIMIT = 1000;
    private SystemManager systemManager;
    private MaintenanceSwitch maintenanceSwitch;
    public int next_id;

    public ElevatorController() {
        passengerQueue = new LinkedHashSet<Passenger>();
        passengerLogger = new PassengerLogger(passengerQueue);
        passengerDispatcher = new PassengerDispatcher(passengerQueue);
        positionMarkerSensor = new PositionMarkerSensor();
        floorNumberDisplay = new FloorNumberDisplay();
        directionDisplay = new DirectionDisplay();
        elevatorEngine = new ElevatorEngine();
        doorOpeningDevice = new DoorOpeningDevice();
        doorOperator = new DoorOperator(doorOpeningDevice);
        cabNavigator = new CabNavigator(0, elevatorEngine, directionDisplay, floorNumberDisplay, positionMarkerSensor,
                doorOperator);
        cabController = new CabController(passengerDispatcher, cabNavigator, SAFETY_LIMIT);
        systemManager = new SystemManager(positionMarkerSensor, doorOperator);
        maintenanceSwitch = new MaintenanceSwitch(systemManager);
        next_id = 0;
        positionMarkerSensor.setPosition(SummonButton.pressed(1));
    }

    public void addPassengers() {
        int id;
        int weight;
        Request sourceFloor;
        Request destinationFloor;
        Scanner scan;
        int N;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        scan = new Scanner(System.in);

        System.out.println("Input the number of passengers : ");
        N = scan.nextInt();
        for (int i = 0; i < N; i++) {
            next_id++;
            id = next_id;
            System.out.println("== Passenger " + next_id + " ==");
            System.out.println("Enter Passenger Source Floor : ");
            sourceFloor = SummonButton.pressed(scan.nextInt());
            System.out.println("Enter Passenger Destination Floor : ");
            destinationFloor = FloorRequest.pressed(scan.nextInt());
            System.out.println("Enter Passenger Weight : ");
            weight = scan.nextInt();
            if (positionMarkerSensor.MarkerDetected().getFloorNumber() == sourceFloor.getFloorNumber()) {
                Passenger newPassenger = new Passenger(id, weight, sourceFloor, destinationFloor, true);
                if (cabController.getLoadSensor().WeightChanged(newPassenger) != cabController.getLoadSensor().OverWeight) {
                    passengerLogger.AddPassengerToQueue(newPassenger);
                    temp.add(id);
                } else{
                    passengerLogger.AddPassengerToQueue(new Passenger(id, weight, sourceFloor, destinationFloor, false));
                }
            } else {
                passengerLogger.AddPassengerToQueue(new Passenger(id, weight, sourceFloor, destinationFloor, false));
            }
        }
        if (!temp.isEmpty()) {
            doorOperator.doorOpened();
            for (Integer i : temp) {
                System.out.println("Passenger " + i + " has entered the cab");
            }
            doorOperator.doorClosed();
        }
        System.out.println();
    }

    public void run() {
        boolean exit;
        boolean running;
        Scanner scan;
        scan = new Scanner(System.in);
        int option;

        exit = false;

        while (!exit) {
            // mode operator atau penumpang
            running = maintenanceSwitch.getElevatorStat();
            System.out.println("Elevator");
            System.out.println("1. Passenger Mode");
            System.out.println("2. Operator Mode");
            System.out.println("3. Exit");
            System.out.print("Enter option : ");
            option = scan.nextInt();
            switch (option) {
                case 1: {
                    if (running) {
                        while (!exit) {
                            System.out.println("\nQueue for Elevator is empty");
                            System.out.println("1. Initialize new Passenger");
                            System.out.println("2. Back");
                            System.out.print("Enter option : ");
                            option = scan.nextInt();
                            switch (option) {
                                case 1: {
                                    addPassengers();
                                    while (!passengerDispatcher.checkQueueForPassenger()) {
                                        cabController
                                                .processRequest(passengerDispatcher.determineNextPassengerToProcess());
                                    }
                                    break;
                                }
                                case 2: {
                                    exit = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("The elevator is off");
                    }
                    exit = false;
                    break;
                }
                case 2: {
                    int mode;
                    System.out.println("\n====Elevator Switch====");
                    System.out.println("1. Turn On");
                    System.out.println("2. Turn Off");
                    System.out.println("3. back");
                    System.out.print("Input : ");
                    mode = scan.nextInt();
                    if (mode == 1) {
                        maintenanceSwitch.TurnOn();
                    } else if (mode == 2) {
                        maintenanceSwitch.TurnOff();
                    } else if (mode == 3) {
                        //nothing
                    } else {
                        //nothing
                    }
                    break;
                }
                case 3: {
                    exit = true;
                    break;
                }
            }
        }
    }
}