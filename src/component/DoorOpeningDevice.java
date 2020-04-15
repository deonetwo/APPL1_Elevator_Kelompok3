package component;
    /**
     * penanggung jawab: Fatharani
     */

public class DoorOpeningDevice {
    
    private boolean isOpen;
    

    /**
     * Implementasi refactoring Encapslate Field
     */
    public void setDoor(boolean status){
        this.isOpen = status;
    }
    public void openDoors() {
        setDoor(true);
    }
    public void closeDoors() {
        setDoor(false);
    }

    public boolean isIsOpen() {
        return this.isOpen;
    }
}