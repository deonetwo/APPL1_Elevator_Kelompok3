package component;

/**
 * credit : Mufqi, Rayhan Azka
 *          Chofief dan Fajrina
 */

public abstract class Request {

    /**
     * Implementasi refactoring self encpsulate field
     */
    /**
     * implementasi refactoring pull up field, extract method, 
     * extract subclass, pull up method, pull up constructor body
     */

    private int number;
    private boolean lightState;

    public Request(int number) {
        setFloorNumber(number);
        turnLightOn();
    }

    public int getFloorNumber() {
        return this.number;
    }

    public void setFloorNumber(int number) {
        this.number = number;
    }

    public void turnLightOn() {
        setLightState(true);
    }

    public void turnLightOff() {
        setLightState(false);
    }

    public boolean checkLightState() {
        return this.lightState;
    }
    
    public void setLightState(boolean state) {
        this.lightState = state;
    }

    public void setLightState() {
        setLightState(true);
    }

    public abstract boolean isFloorRequest();
}
