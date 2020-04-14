package component;

public class LoadSensor /*extend to cabcontroller ?*/{
	private int safetyLimit;
	
	public LoadSensor(int safeLimit) {
		this.safetyLimit = safeLimit;
	}
	
	public void WeightChanged(int cabID, int newWeight) {
		/* 
		 * LoadSensor.WeightChanged( cabID, newWeight )
			[cabID == this cab AND newWeight > safetyLimit] /
			LoadBell.Ring(), DoorOpeningDevice.OpenDoors()
			Stop Timer to Close Doors Automatically
			
			LoadSensor.WeightChanged( cabID, newWeight )
			[cabID == this cab AND newWeight <= safetyLimit] /
			Restart Timer to Close Doors Automatically
			
			LoadSensor.WeightChanged( cabID, newWeight )
			[cabID == this cab AND newWeight > safetyLimit] /
			LoadBell.Ring()
		 */
	}
}
