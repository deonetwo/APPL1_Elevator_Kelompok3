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

        // onIdle = new OnIdle(this);
        // goingUp = new GoingUp(this);
        // goingDown = new GoingDown(this);
        // doorOpened = new DoorOpened(this);
        // doorClosed = new DoorClosed(this);
        // int jpenumpang, fnumber, newPersonWeight;
        // Request processed;
        // Request processed;
        // Request processed;
        // Request processed;
        // Request processed;
        // positionMarkerSensor.setPosition(FloorRequest.pressed(1));

        // System.out.println("Masukkan jumlah penumpang : ");
        // jpenumpang = scan.nextInt();
        // for(int i = 0; i < jpenumpang; i++) {
        // System.out.println("== Penumpang " + (i+1) + " ==");
        // System.out.println("Masukkan tujuan penumpang : ");
        // fnumber = scan.nextInt();
        // t_ System.out.println("Masukkan Berat penumpang : ");
        // t_ new;PersonWeight = scan.nextInt();
        // cabController.addWeight(newPersonWeight);
        // requestLogger.AddRequestToQueue(FloorRequest.pressed(fnumber));
        // }
        // cabNavigator.setPositionMarkerSensor(positionMarkerSensor);
        // cabContrhtoller.setCabNavigator(cabNavigator);
        // int ieigd;
        // int w cabController.setRequestDispatcher(requestDispatcher);
        // while(!requ
        // estDispatcher.checkQueueForRequest()) {
        // processed = requestDispatcher.determineNextRequestToProcess();
        // cabController.processRequest(processed);
        // }
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
                                        // cabController.addWeight(passengerDispatcher.determineNextPassengerToProcess().getWeight());
                                        // cabController.WeightChanged(loadSensor.getWeight());
                                        cabController
                                                .processRequest(passengerDispatcher.determineNextPassengerToProcess());
                                        // cabNavigator.moveToFloor(passengerDispatcher.determineNextPassengerToProcess(),
                                        // passengerDispatcher);
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

        // while(true) {
        /*
         * while tidak ada yang summon lantai lagi apakah ada yang summon lantai? jika
         * ada, lantai berapa? jika request lantai baru < lantai tujuan awal berhenti di
         * request lantai baru masukan banyaknya penumpang dan berat sebanyak N kali
         * masukan floor request sebanyak N kali floor request masuk ke antrian lakukan
         * penambahan berat lanjutkan ke lantai tujuan sesuai dengan queue
         * (tekecil/terdekat didahulukan) jika tidak, menuju lantai tujuan awal masukan
         * request lantai baru ke list floor request jika tidak, lanjutkan ke lanta
         * tujuan.
         * 
         * //sudah sampai di lantai tujuan
         */
        // }
    }
}