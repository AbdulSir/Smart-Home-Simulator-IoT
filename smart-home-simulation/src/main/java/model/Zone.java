package model;

import java.util.ArrayList;

public class Zone {
	private static ArrayList<Zone> zoneList = new ArrayList<Zone>();
	private int currentZone;
	private String period;
	private String initialPeriod;
	private String finalPeriod;
	private double desiredTemperature;

	/**
	 * Constructor
	 */
	public Zone() {
	}

	/**
	 * Parametrized Constructor
	 */
	public Zone(int currentZone, String period, String initialPeriod, String finalPeriod, double desiredTemperature) {
		this.currentZone = currentZone;
		this.period = period;
		this.initialPeriod = initialPeriod;
		this.finalPeriod = finalPeriod;
		this.desiredTemperature = desiredTemperature;
		getZoneList().add(this);
	}

	/**
	 * Print statement
	 */
	public void printZoneDetails() {
		for (Zone zone : getZoneList()) {
			System.out.println("CurrentZone is " + (zone.getCurrentZone() + 1));
			System.out.println("Period is " + zone.getPeriod());
			System.out.println("Initial Time Period is " + zone.getInitialPeriod());
			System.out.println("Final Time Period is " + zone.getFinalPeriod());
			System.out.println("Desired Temperature is " + zone.getDesiredTemperature());
			System.out.println("-----------");
		}
	}

	/**
	 * Getter
	 */
	public static ArrayList<Zone> getZoneList() {
		return zoneList;
	}

	/**
	 * Getter
	 */
	public int getCurrentZone() {
		return currentZone;
	}

	/**
	 * Getter
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * Getter
	 */
	public String getInitialPeriod() {
		return initialPeriod;
	}

	/**
	 * Getter
	 */
	public String getFinalPeriod() {
		return finalPeriod;
	}

	/**
	 * Getter
	 */
	public double getDesiredTemperature() {
		return desiredTemperature;
	}

	public void setDesiredTemperature(double desiredTemperature) {
		this.desiredTemperature = desiredTemperature;
	}
}
