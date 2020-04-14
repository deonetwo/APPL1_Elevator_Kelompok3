import controller.ElevatorController;
import java.util.Scanner;

public class App {
  
    public static void main(String[] args) throws Exception {
        int mode;
        ElevatorController ec = new ElevatorController();
        while(true){
            System.out.println("select mode:\n1. Operator\n2. Passenger");
            Scanner scan = new Scanner(System.in);
            mode = scan.nextInt();
            if(mode==1){
                ec.elevator_switch();
            } else {
                ec.floor_request();
            }
        }
        
        
    }
}