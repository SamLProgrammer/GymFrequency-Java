package models;

import java.util.ArrayList;

public class Gym {
	
	public static final int MATRIX_HORIZONTAL_LENGTH = 2;
	
	
	private ArrayList<GymRegistry> registersList;
	
	public Gym() {
		registersList = new ArrayList<>();
		initBurnedData();
	}
	
	public void initBurnedData() {
		GymRegistry r1 = createGymRegistry("Daniela Vargas", DayString.MIERCOLES);
		GymRegistry r2 = createGymRegistry("Felix Dodoria", DayString.LUNES);
		GymRegistry r3 = createGymRegistry("Leonidas Ramirez", DayString.SABADO);
		GymRegistry r4 = createGymRegistry("Carlos Roncancio", DayString.LUNES);
		GymRegistry r5 = createGymRegistry("Alejandro Porras", DayString.MARTES);
		GymRegistry r6 = createGymRegistry("Santiago Becerra", DayString.JUEVES);
		GymRegistry r7 = createGymRegistry("Katherine Ayala", DayString.MIERCOLES);
		GymRegistry r8 = createGymRegistry("Arturo Roa", DayString.MIERCOLES);
		GymRegistry r9 = createGymRegistry("Juliana Ochoa", DayString.JUEVES);
		addGymRegistry(r1);
		addGymRegistry(r2);
		addGymRegistry(r3);
		addGymRegistry(r4);
		addGymRegistry(r5);
		addGymRegistry(r6);
		addGymRegistry(r7);
		addGymRegistry(r8);
		addGymRegistry(r9);
		
	}
	
	public void removeRegistry(int index) {
		registersList.remove(index);
	}
	
	
	public void listToString() {
		for (GymRegistry gymRegistry : registersList) {
			System.out.println(gymRegistry.toString());
		}
	}
	
	public void addGymRegistry(GymRegistry gymRegistry) {
		registersList.add(gymRegistry);
	}
	
	public GymRegistry createGymRegistry(String name, DayString dayString) {
		return new GymRegistry(name,dayString);
	}
	
	public int amountPerDay(String dayName) {
		int counter = 0;
		for (GymRegistry gymRegistry : registersList) {
			if(dayName.equalsIgnoreCase(gymRegistry.getDay().toString())) {
				counter++;
			}
		}
		return counter;
	}
	
	public double[] amountPerDayVector() {
		double[] valuesVector = new double[DayString.values().length];
		for (int i = 0; i < valuesVector.length; i++) {
			valuesVector[i] = amountPerDay(DayString.values()[i].toString());
		}
		return valuesVector;
	}
	
	public String[][] registerDataMatrix() {
		String[][] matrix = new String[registersList.size()][MATRIX_HORIZONTAL_LENGTH];
		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = registersList.get(i).dataGymRegistryString();
		}
		return matrix;
	}
	
	public int[] weekValues() {
		DayString[] daysVector = DayString.values();
		int[] weekValues = new int[daysVector.length];
		System.out.println("length:" + daysVector.length);
		for (int i = 0; i < daysVector.length; i++) {
			weekValues[i] = amountPerDay(daysVector[i].toString());
		}
		for (int i = 0; i < weekValues.length; i++) {
			System.out.println(weekValues[i]);			
		}
		return weekValues;
	}
	
}
