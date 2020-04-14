package component;

import controller.SystemManager;

/**
 *
 * @author User
 */
public class MaintenanceSwitch{
    private boolean isRunning;
    private SystemManager SysManage;
    public MaintenanceSwitch(SystemManager SysManage){
        this.isRunning=false;
        this.SysManage = SysManage;
    }
    public void TurnOn(){
        this.isRunning = true;          
        SysManage.Initialize();
        System.out.println("Lift telah dinyalakan");
    }
    
    public void TurnOff(){
        this.isRunning = false;
        SysManage.ShutDown();
        System.out.println("Lift telah dimatikan");
    }
    
    public boolean getElevatorStat(){
        return isRunning;
    }
}