package component;

/**
 * credit : Dewanto
 */
public class LoadSensor {
	public final boolean OverWeight = true;
	public final boolean inSafety = false;

	private int currentWeight = 0;

	public void addWeight(int newWeight) {
		currentWeight += newWeight;
	}

	public int getWeight() {
		return currentWeight;
	}
}
