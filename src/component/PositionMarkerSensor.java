package component;

public class PositionMarkerSensor {
    private Request position;

    public Request MarkerDetected(){
        return this.position;
    }

    public void setPosition(Request fRequest){
        this.position = fRequest;
    }
}    