/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import component.FloorRequest;
import component.MaintenanceSwitch;

/**
 *
 * @author User
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
        //floor.turnLightOn();
        //nyalain lampu summon button
    }
    
}
