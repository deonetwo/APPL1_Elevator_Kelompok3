package component;

/**
 *
 * @author Chofief dan Fajrina
 */
 
 
public class SummonButton extends Request {
    /**
     * implementasi refactoring pull up field, extract method, 
     * extract subclass, pull up method, pull up constructor body
     */
    private SummonButton(int number) {
        super(number);
    }

    public boolean isFloorRequest(){
        return false;
    }

    public static SummonButton pressed(int number) {
        return new SummonButton(number);
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
