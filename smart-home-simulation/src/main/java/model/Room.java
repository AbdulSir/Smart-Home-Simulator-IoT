package model;

import java.util.ArrayList;

public class Room {
	private String location;
	private int count;
	private static ArrayList<Room> rooms = new ArrayList<Room>();
	private static Room roomCounter;
	private double temperature;
	private int zone;

	/** Default Constructor **/
	private Room() {
	}

	/** Parametrized Constructor **/
	public Room(String location) {
		this.location = location;
		count = 0;
		rooms.add(this);
		setTemperature(0);
		zone = 1;
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
	public static ArrayList<Room> getRooms() {
		return rooms;
	}

	/**
	 * Setter
	 */
	public static void setRooms(ArrayList<Room> rooms) {
		Room.rooms = rooms;
	}

	/**
	 * Getter
	 */
	public static Room getRoomCounter() {
		if (roomCounter != null)
			return roomCounter;
		else {
			Room.roomCounter = new Room();
			return roomCounter;
		}
	}

	/**
	 * Setter
	 * 
	 * @param roomCounter
	 */
	public static void setRoomCounter(Room roomCounter) {
		Room.roomCounter = roomCounter;
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

}
