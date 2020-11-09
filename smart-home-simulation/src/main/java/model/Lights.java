package model;

import java.util.ArrayList;

import controller.SimulationButton;

public class Lights {
	private String location;
	private boolean on;
	private static Lights light;
	private static ArrayList<Lights> lightsList = new ArrayList<Lights>();

	/**
	 * Default Constructor
	 */
	private Lights() {
		location = "";
		on = false;
	}

	/**
	 * Parametrized Constructor
	 */
	public Lights(String location) {
		this.location = location;
		on = false;
		lightsList.add(this);
	}

	/**
	 * Getter
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Setter
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Getter
	 */
	public boolean areLightsOn() {
		return on;
	}

	/**
	 * List of Lights
	 * 
	 * @return All Lights
	 */
	public ArrayList<Lights> getLightsList() {
		return lightsList;
	}

	/**
	 * Setter
	 */
	public void setLights(boolean on) {
		this.on = on;
	}

	/**
	 * Setter
	 */
	public static void setLightsList(ArrayList<Lights> lightsList) {
		Lights.lightsList = lightsList;
	}

	public static Lights getLight() {
		if (light != null)
			return light;
		else {
			Lights.light = new Lights();
			return light;
		}
	}
}
