package controller;

import component.FloorRequest;
import component.MaintenanceSwitch;

/**
 *
 * @author KhoirunnisaPutri
 */
public class SystemManager {
    private FloorRequest floor;
    public SystemManager(FloorRequest floor){
        this.floor=floor;
    }
    public void ShutDown(){
        //tutup pintu
        floor.turnLightOff();
        //matiin lampu summon button 
       //ngestop lift
        
    }
    public void Initialize(){
        floor.turnLightOn();
        //nyalain lampu summon button
    }
    
}
