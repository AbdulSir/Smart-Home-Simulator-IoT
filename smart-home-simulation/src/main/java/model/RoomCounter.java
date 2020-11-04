package model;

import java.util.ArrayList;

public class RoomCounter {
	private String location;
	private int count;
	private static ArrayList<RoomCounter> rooms = new ArrayList<RoomCounter>();
	
	public RoomCounter() {}
	
	/** Constructor **/
	public RoomCounter(String location) {
		this.location = location;
		count = 0;
		rooms.add(this);
	}
	
	public void incrementCounter() {
		count++;
		setCount(count);
	}

	public void decrementCounter() {
		count--;
		setCount(count);
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
	public ArrayList<RoomCounter> getRooms() {
		return rooms;
	}

	/**
	 * Setter 
	 */
	public static void setRooms(ArrayList<RoomCounter> rooms) {
		RoomCounter.rooms = rooms;
	}
}
