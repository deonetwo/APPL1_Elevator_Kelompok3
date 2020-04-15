package component;

/**
 * credit : Dewanto
 */
public class LoadSensor {
        public final int safetyLimit;
	public static final boolean OverWeight = true;
	public static final boolean inSafety = false;

	private int currentWeight = 0;
        
        private LoadBell loadBell;

        public LoadSensor(int safetyLimit, LoadBell loadBell) {
            this.safetyLimit = safetyLimit;
            this.loadBell = loadBell;
        }

	public void addWeight(int theWeight) {
            currentWeight += theWeight;
	}
        
        public void reduceWeight(int theWeight){
            currentWeight -= theWeight;
        }

	public int getWeight() {
            return currentWeight;
	}
        
        public boolean WeightChanged(Passenger pass) {
        addWeight(pass.getWeight());
        if (getWeight() <= safetyLimit) {
            return inSafety;
        } else {
            loadBell.Ring();
            reduceWeight(pass.getWeight());
            System.out.println("Passenger with id " + pass.getId() + " cannot entered the cab.");
            return OverWeight;
        }
    }
}
