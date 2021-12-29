package models;

public enum DayString{

	LUNES("Lunes",0),MARTES("Martes",1),MIERCOLES("Miercoles",2),JUEVES("Jueves",3),VIERNES("Viernes",4),SABADO("Sabado",5);
	
	private String name;
	private int indicator;
	
	private DayString(String value, int indicator) {
		this.name = value;
		this.indicator = indicator;
	}
	
	public static String[] stringValues() {
		String[] valuesVector = new String[DayString.values().length];
		for (int i = 0; i < DayString.values().length; i++) {
			valuesVector[i] = DayString.values()[i].toString();
		}
		return valuesVector;
	}
	
	public String toString() {
		return name;
	}
	
	public int getIndicator() {
		return indicator;
	}
}
