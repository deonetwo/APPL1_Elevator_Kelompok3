package controller;
import java.util.LinkedHashSet;
//import java.util.LinkedList;
//import java.util.Queue;
import component.*;
import java.util.Scanner;
import java.util.Set;

public class ElevatorController {
    public Set<FloorRequest> requestQueue;
    public FloorRequest floorRequest;
    public FloorRequestLogger floorRequestLogger;
    public RequestDispatcher requestDispatcher;
    public CabController cabController;
    public CabNavigator cabNavigator;
    public ElevatorEngine elevatorEngine;
    public DirectionDisplay directionDisplay;
    public FloorNumberDisplay floorNumberDisplay;
    public PositionMarkerSensor positionMarkerSensor;
    public LoadSensor loadSensor;
    public LoadBell loadBell;
    private Scanner scan;
    private SystemManager systemManager;
    private MaintenanceSwitch maintenanceSwitch;

    public ElevatorController() {
        requestQueue = new LinkedHashSet<FloorRequest>();
        floorRequestLogger = new FloorRequestLogger(requestQueue);
        requestDispatcher = new RequestDispatcher(requestQueue);
        scan = new Scanner(System.in);
        positionMarkerSensor = new PositionMarkerSensor();
        floorNumberDisplay = new FloorNumberDisplay();
        directionDisplay = new DirectionDisplay();
        elevatorEngine = new ElevatorEngine();
        systemManager = new SystemManager();
        maintenanceSwitch = new MaintenanceSwitch(systemManager);  

    }

    public void floor_request() {
        int jpenumpang, fnumber, berat, ac_berat = 0;
        FloorRequest processed;
        boolean isRunning = maintenanceSwitch.getElevatorStat();
        if(isRunning){
             System.out.println("Masukkan jumlah penumpang : ");
            jpenumpang = scan.nextInt();
            for(int i = 0; i < jpenumpang; i++) {
                System.out.println("== Penumpang " + (i+1) + " ==");
                System.out.println("Masukkan  tujuan penumpang : ");
                fnumber = scan.nextInt();
                System.out.println("Masukkan  Berat penumpang : ");
                berat = scan.nextInt(); 
                ac_berat += berat;
                floorRequestLogger.AddFloorRequestToQueue(FloorRequest.pressed(fnumber));
            }
        positionMarkerSensor.setPosition(FloorRequest.pressed(1));
        cabNavigator = new CabNavigator(0, elevatorEngine, directionDisplay, floorNumberDisplay, positionMarkerSensor);
        cabController = new CabController(requestDispatcher, ac_berat, cabNavigator);
        while(!requestDispatcher.checkQueueForRequest()) {
            processed = requestDispatcher.determineNextRequestToProcess();
            cabController.processRequest(processed);
        }
        } else {
            System.out.println("Lift dalam keadaan mati");
        }
       
    }
    
    public void elevator_switch(){
        int mode;
        System.out.println("Pilih mode:\n1. Aktifkan lift\n2. Matikan lift");
        mode = scan.nextInt();
        if(mode==1){
            maintenanceSwitch.TurnOn();
        } else {
            maintenanceSwitch.TurnOff();
        }
    }

}
