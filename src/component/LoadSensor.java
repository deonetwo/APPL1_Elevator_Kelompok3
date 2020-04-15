package component;

/**
 * credit : Dewanto
 */
public class LoadSensor {
	public final int safetyLimit;
	public final boolean OverWeight = true;
	public final boolean inSafety = false;

	private int currentWeight = 0;

	private LoadBell loadBell;

        public LoadSensor(int safetyLimit, LoadBell loadBell) {
            this.safetyLimit = safetyLimit;
            this.loadBell = loadBell;
        }

    public LoadSensor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	public void addWeight(int newWeight) {
		currentWeight += newWeight;
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
            addWeight(-(pass.getWeight()));
            System.out.println("Passenger with id " + pass.getId() + " cannot entered the cab.");
            return OverWeight;
        }
    }
}
