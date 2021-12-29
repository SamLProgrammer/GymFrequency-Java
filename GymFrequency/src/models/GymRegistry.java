package models;

public class GymRegistry {

	private String name;
	private DayString day;

	public GymRegistry(String name, DayString day) {
		this.name = name;
		this.day = day;
	}
	
	public String[] dataGymRegistryString() {
		String[] dataVector = {name, day.toString()};
		return dataVector;
	}

	@Override
	public String toString() {
		return "GymRegistry [name=" + name + ", day=" + day + "]";
	}

	public String getName() {
		return name;
	}

	public DayString getDay() {
		return day;
	}	
}
