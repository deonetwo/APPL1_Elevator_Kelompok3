package controller;
/**
 * credit : Dewanto
 */
public interface ElevatorState {
    
    /*
    onIdle
    goingUp
    goingDown
    doorOpened
    doorClosed
    overWeight
    */
    void summonRequest(int whichFloor);
    void floorRequest(int whichFloor);
    void weightChanged(int newWeight);
}