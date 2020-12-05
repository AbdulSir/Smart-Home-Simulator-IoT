package model;

import java.util.ArrayList;

public class RoomCounter {
	private String location;
	private int count;
	private static ArrayList<RoomCounter> rooms = new ArrayList<RoomCounter>();
	private static RoomCounter roomCounter;
	private static RoomCounter BedRM;	
	private static RoomCounter MasterBedRM;
	private static RoomCounter BathRM;
	private static RoomCounter Kitchen;
	private static RoomCounter LivingRM;
	private static RoomCounter Garage;	
	
	/** Default Constructor **/
	private RoomCounter() {}
	
	/** Parametrized Constructor **/
	public RoomCounter(String location) {
		this.location = location;
		count = 0;
		rooms.add(this);
		BedRM = rooms.get(0);
		MasterBedRM = rooms.get(1);
		BathRM = rooms.get(2);
		Kitchen = rooms.get(3);
		LivingRM = rooms.get(4);
		Garage = rooms.get(5);
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
	public static RoomCounter getRoomCounter() {
		if (roomCounter != null)
			return roomCounter;
		else {
			RoomCounter.roomCounter = new RoomCounter();
			return roomCounter;
		}
	}
	
	public static RoomCounter getBedRM() {
		return BedRM;
	}

	public static void setBedRM(RoomCounter bedRM) {
		BedRM = bedRM;
	}

	public static RoomCounter getMasterBedRM() {
		return MasterBedRM;
	}

	public static void setMasterBedRM(RoomCounter masterBedRM) {
		MasterBedRM = masterBedRM;
	}

	public static RoomCounter getBathRM() {
		return BathRM;
	}

	public static void setBathRM(RoomCounter bathRM) {
		BathRM = bathRM;
	}

	public static RoomCounter getKitchen() {
		return Kitchen;
	}

	public static void setKitchen(RoomCounter kitchen) {
		Kitchen = kitchen;
	}

	public static RoomCounter getLivingRM() {
		return LivingRM;
	}

	public static void setLivingRM(RoomCounter livingRM) {
		LivingRM = livingRM;
	}

	public static RoomCounter getGarage() {
		return Garage;
	}

	public static void setGarage(RoomCounter garage) {
		Garage = garage;
	}

	public static void setRoomCounter(RoomCounter roomCounter) {
		RoomCounter.roomCounter = roomCounter;
	}


}
