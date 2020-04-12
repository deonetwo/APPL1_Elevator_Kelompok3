package component;

public class PositionMarkerSensor {
    private FloorRequest position;

    public FloorRequest MarkerDetected(){
        return this.position;
    }

    public void setPosition(FloorRequest fRequest){
        this.position = fRequest;
    }
}    