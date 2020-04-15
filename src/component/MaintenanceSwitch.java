package component;

import controller.SystemManager;

/**
 *
 * Penanggung jawab: KhoirunnisaPutri
 */
public class MaintenanceSwitch{
    private boolean isRunning=false;
    private SystemManager SysManage;
    public MaintenanceSwitch(SystemManager SysManage){
        this.isRunning=false;
        this.SysManage = SysManage;
    }
    public void TurnOn(){
        this.isRunning = true;          
        SysManage.Initialize();
        System.out.println("The elevator has been turned on");
    }
    
    public void TurnOff(){
        this.isRunning = false;
        SysManage.ShutDown();
        System.out.println("The elevator has been turned off");
    }
    
    public boolean getElevatorStat(){
        return isRunning;
    }
}
