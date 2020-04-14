package component;

//import java.util.Set;

public class Passenger {
	private int id;
    private int weight;
    private Request sourceFloor;
	private Request destination;
	private boolean status;

    public Passenger(int id, int weight, Request sourceFloor, Request destination, boolean status) {
		this.id = id;
        this.weight = weight;
        this.sourceFloor = sourceFloor;
		this.destination = destination;
		this.status = status;
    }

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Request getSourceFloor() {
		return sourceFloor;
	}

	public void setSourceFloor(Request sourceFloor) {
		this.sourceFloor = sourceFloor;
	}

	public Request getDestination() {
		return destination;
	}

	public void setDestination(Request destination) {
		this.destination = destination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
}            