package model;

import java.util.ArrayList;

public class RoomCounter {
	private String location;
	private int count;
	private static ArrayList<RoomCounter> rooms = new ArrayList<RoomCounter>();
	private static RoomCounter roomCounter;
	private double temperature;
	private int zone;
	private boolean isTempOverridden;

	/** Default Constructor **/
	private RoomCounter() {
	}

	/** Parametrized Constructor **/
	public RoomCounter(String location) {
		this.location = location;
		count = 0;
		rooms.add(this);
		setTemperature(0);
		zone = 1;
		isTempOverridden = false;
	}

	/**
	 * Method will increment the count when someone enters a room
	 */
	public void incrementCounter() {
		count++;
	}

	/**
	 * Method will decrement the count when someone leaves a room
	 */
	public void decrementCounter() {
		count--;
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
	public int getCount() {
		return count;
	}

	/**
	 * Setter
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Getter
	 */
	public static ArrayList<RoomCounter> getRooms() {
		return rooms;
	}

	/**
	 * Setter
	 */
	public static void setRooms(ArrayList<RoomCounter> rooms) {
		RoomCounter.rooms = rooms;
	}

	/**
	 * Getter
	 */
	public static RoomCounter getRoomCounter() {
		if (roomCounter != null)
			return roomCounter;
		else {
			RoomCounter.roomCounter = new RoomCounter();
			return roomCounter;
		}
	}

	/**
	 * Setter
	 * 
	 * @param roomCounter
	 */
	public static void setRoomCounter(RoomCounter roomCounter) {
		RoomCounter.roomCounter = roomCounter;
	}

	/**
	 * Getter
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * Setter
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * Getter
	 */
	public int getZone() {
		return zone;
	}

	/**
	 * Setter
	 */
	public void setZone(int zone) {
		this.zone = zone;
	}

	/**
	 * Getter
	 */
	public boolean isTempOverridden() {
		return isTempOverridden;
	}

	/**
	 * Setter
	 */
	public void setTempOverridden(boolean isTempOverridden) {
		this.isTempOverridden = isTempOverridden;
	}

}
