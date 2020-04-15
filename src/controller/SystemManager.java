
package controller;

import component.PositionMarkerSensor;
import component.SummonButton;
//import component.MaintenanceSwitch;

/**
 *
 * Penanggung jawab : khoirunnisa
 */
public class SystemManager {
    private PositionMarkerSensor position;
    private DoorOperator doorOperator;

    public SystemManager(PositionMarkerSensor position, DoorOperator doorOperator) {
        this.position = position;
        this.doorOperator = doorOperator;
    }

    public void ShutDown() {
        position.setPosition(SummonButton.pressed(1));
        doorOperator.doorClosed();
    }

    public void Initialize() {

    }
}
