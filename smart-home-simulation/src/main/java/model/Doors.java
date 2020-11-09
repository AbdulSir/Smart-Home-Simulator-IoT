package model;

import java.util.ArrayList;

import controller.SimulationButton;

public class Doors {
	private String location;
	private boolean open;
	private boolean locked;
	private static ArrayList<Doors> doorList = new ArrayList<Doors>();
	private static Doors door;

	/**
	 * Default Constructor
	 */
	private Doors() {
		location = "";
		open = false;
		locked = false;
	}

	/**
	 * Parametrized Constructor
	 */
	public Doors(String location) {
		this.location = location;
		open = false;
		if(location.equals("Entrance") || location.equals("Backyard") || location.equals("Garage"))
			locked = true;
		else
			locked = false;
		doorList.add(this);
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
	 * Setter
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * List of Doors
	 * 
	 * @return All Doors
	 */
	public ArrayList<Doors> getDoorList() {
		return doorList;
	}

	/**
	 * Setter
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * Setter
	 */
	public static void setDoorList(ArrayList<Doors> doorList) {
		Doors.doorList = doorList;
	}

	/**
	 * Getter
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Setter
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public static Doors getDoor() {
		if (door != null)
			return door;
		else {
			Doors.door = new Doors();
			return door;
		}
	}
	
}