package model;

import java.util.ArrayList;

public class Lights {
	private String location;
	private boolean on;
	private static ArrayList<Lights> lightsList = new ArrayList<Lights>();

	public Lights() {
		location = "";
		on = false;
	}

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
}
