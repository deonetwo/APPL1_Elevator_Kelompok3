package component;

/*
    credit : Mufqi
*/
public class DirectionDisplay {

    public DirectionDisplay() {}

    /**
     * Implementasi refactoring extract method
     */
    public void show(String direction) {
        System.out.println("Elevator is going " + direction);
    }
}