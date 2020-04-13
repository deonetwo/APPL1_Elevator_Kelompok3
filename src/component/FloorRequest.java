package component;

public class FloorRequest {
    
    private int number;
    private boolean lightState;

    private FloorRequest(int number) {
        setFloorNumber(number);
        turnLightOn();
    }

    public static FloorRequest pressed(int number) {
        return new FloorRequest(number);
    }

    private void setLightState(boolean state) {
        this.lightState = state;
    }

    private void turnLightOn() {
        setLightState(true);
    }

    public void turnLightOff() {
        setLightState(false);
    }
    
    public int getFloorNumber() {
        return this.number;
    }

    private void setFloorNumber(int number) {
        this.number = number;
    }

    public boolean checkLightState() {
        return this.lightState;
    }

    @Override
    public int hashCode() {
        return getFloorNumber();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof FloorRequest))
            return false;
        if(obj == this)
            return true;
        return this.getFloorNumber() == ((FloorRequest) obj).getFloorNumber();
    }
}
