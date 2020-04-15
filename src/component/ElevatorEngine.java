package component;

/**
 * credit : Mufqi, Rayhan Azka
 */
public class ElevatorEngine {
    /**
     * Implementasi refactoring encapsulate field
     */

    private int speed;
    private String direction;

    public void move(int speed, String direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public int getSpeed() {
        return this.speed;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}