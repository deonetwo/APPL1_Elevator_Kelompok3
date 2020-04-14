package component;

/**
 *
 * @author Chofief dan Fajrina
 */
public class SummonButton {
    private int floor;
    private String direction; //up or down
    private boolean lightStatus;
    
    /*
    public void Pressed (int floor, String direction){
    //add request to queue
    }
    */
    
    public void TurnLightOn(){
        this.setLightStatus(true);
    }
    

    // Refactor Getter Setter
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public boolean isLightStatus() {
        return lightStatus;
    }
    public void setLightStatus(boolean lightStatus) {
        this.lightStatus = lightStatus;
    }
}
