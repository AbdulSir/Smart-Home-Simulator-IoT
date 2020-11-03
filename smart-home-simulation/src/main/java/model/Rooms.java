package model;

import java.util.ArrayList;

public class Rooms {
	private String location;
	private int count;
	private static ArrayList<Rooms> rooms = new ArrayList<Rooms>();
	
	public Rooms() {}
	
	/** Constructor **/
	public Rooms(String location) {
		this.location = location;
		count = 0;
		rooms.add(this);
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
	public ArrayList<Rooms> getRooms() {
		return rooms;
	}

	/**
	 * Setter 
	 */
	public static void setRooms(ArrayList<Rooms> rooms) {
		Rooms.rooms = rooms;
	}
}
