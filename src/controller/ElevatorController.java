package controller;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

//import java.util.LinkedList;
//import java.util.Queue;
import component.*;
import java.util.Scanner;
import java.util.Set;

public class ElevatorController {
    public final int safetyLimit = 1000;
    public Set<Passenger> passengerQueue;
    public PassengerLogger passengerLogger;
    public PassengerDispatcher passengerDispatcher;
    public CabController cabController;
    public CabNavigator cabNavigator;
    public ElevatorEngine elevatorEngine;
    public DirectionDisplay directionDisplay;
    public FloorNumberDisplay floorNumberDisplay;
    public PositionMarkerSensor positionMarkerSensor;

    public ElevatorController() {
        passengerQueue = new LinkedHashSet<Passenger>();
        passengerLogger = new PassengerLogger(passengerQueue);
        passengerDispatcher = new PassengerDispatcher(passengerQueue);
        positionMarkerSensor = new PositionMarkerSensor();
        floorNumberDisplay = new FloorNumberDisplay();
        directionDisplay = new DirectionDisplay();
        elevatorEngine = new ElevatorEngine();
        cabNavigator = new CabNavigator(0, elevatorEngine, directionDisplay, floorNumberDisplay, positionMarkerSensor);
        cabController = new CabController(passengerDispatcher, cabNavigator, safetyLimit);
    }

    public void floor_request() {
    //     Scanner scan;
    //     scan = new Scanner(System.in);
    //     int jpenumpang, fnumber, newPersonWeight;
    //     Request processed;
    //     positionMarkerSensor.setPosition(FloorRequest.pressed(1));

    //     System.out.println("Masukkan jumlah penumpang : ");
    //     jpenumpang = scan.nextInt();
    //     for(int i = 0; i < jpenumpang; i++) {
    //         System.out.println("== Penumpang " + (i+1) + " ==");
    //         System.out.println("Masukkan  tujuan penumpang : ");
    //         fnumber = scan.nextInt();
    //         System.out.println("Masukkan  Berat penumpang : ");
    //         newPersonWeight = scan.nextInt(); 
    //         cabController.addWeight(newPersonWeight);
    //         requestLogger.AddRequestToQueue(FloorRequest.pressed(fnumber));
    //     }
    //     cabNavigator.setPositionMarkerSensor(positionMarkerSensor);
    //  cabController.setCabNavigator(cabNavigator);
    //     cabController.setRequestDispatcher(requestDispatcher);
    //     while(!requestDispatcher.checkQueueForRequest()) {
    //         processed = requestDispatcher.determineNextRequestToProcess();
    //         cabController.processRequest(processed);
    //     }
    }

    
    public void run(){
        Scanner scan;
        scan = new Scanner(System.in);
        int Option;

        //asumsi lantai dimulai dari lantai dasar
        
        System.out.print("Masukkan nomor lantai yang dipanggil: ");
        // number = scan.nextInt();
        // do summon request (lantai = fnumber)

        while(true){
            /*
            while tidak ada yang summon lantai lagi
            apakah ada yang summon lantai?
            jika ada, lantai berapa?
                jika request lantai baru < lantai tujuan awal
                    berhenti di request lantai baru
                    masukan banyaknya penumpang dan berat sebanyak N kali
                    masukan floor request sebanyak N kali
                        floor request masuk ke antrian
                        lakukan penambahan berat
                        lanjutkan ke lantai tujuan sesuai dengan queue (tekecil/terdekat didahulukan)
                jika tidak, menuju lantai tujuan awal
                masukan request lantai baru ke list floor request
            jika tidak, lanjutkan ke lanta tujuan.

            //sudah sampai di lantai tujuan
            pessager dengan tujuan == nomor lantai maka lakukan penghapusan pessager dalam list dan pengurangan berat lift
            masukan banyaknya penumpang dan berat sebanyak N kali
            masukan floor request sebanyak N kali
            floor request masuk ke antrian
            lanjutkan ke lantai tujuan selanjutnya dalam antrian
            */
        }
    }
}