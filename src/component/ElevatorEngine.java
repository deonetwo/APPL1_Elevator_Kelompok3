package component;

public class ElevatorEngine {
    int speed;
    String direction;

    public void move(int speed, String direction){
        this.speed = speed;
        this.direction = direction;
    }

    public int getSpeed(){
        return this.speed;
    }

    public String getDirection(){
        return this.direction;
    }
}    