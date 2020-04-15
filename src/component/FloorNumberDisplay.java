package component;

/**
 * credit : Mufqi
 */
public class FloorNumberDisplay {
    public FloorNumberDisplay() {
    }

    public void show(Request floor) {
        System.out.println("Elevator is in floor " + floor.getFloorNumber());
    }
}