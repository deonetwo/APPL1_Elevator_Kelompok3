package component;

public class LoadSensor{
	private int currentWeight = 0;

	public void addWeight(int newWeight){
		currentWeight += newWeight;
	}

	public int getWeight(){
		return currentWeight;
	}
}
