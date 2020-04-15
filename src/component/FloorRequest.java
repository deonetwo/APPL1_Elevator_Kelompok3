package component;

/**
 * credit : Mufqi, Rayhan Azka
 */
public class FloorRequest extends Request {

    /**
     * implementasi refactoring pull up field, extract method, 
     * extract subclass, pull up method, pull up constructor body
     */
    private FloorRequest(int number) {
        super(number);
    }

    public boolean isFloorRequest() {
        return true;
    }

    public static FloorRequest pressed(int number) {
        return new FloorRequest(number);
    }

    @Override
    public int hashCode() {
        return getFloorNumber();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof FloorRequest))
            return false;
        if (obj == this)
            return true;
        return this.getFloorNumber() == ((FloorRequest) obj).getFloorNumber();
    }
}
