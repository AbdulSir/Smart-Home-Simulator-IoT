package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZoneTest {

	@Test
	// Use Case ID: 1
	public void setRoomZoneTest() {
		Room room = new Room();
		room.setZone(2);
		assertEquals(2, room.getZone());
		room.setZone(3);
		assertEquals(3, room.getZone());
	}
	// Use Case ID: 2
	public void setRoomTemperatureTest() {
		Zone zone = new Zone();
		zone.setDesiredTemperature(10.0);
		assertEquals(10.0,zone.getDesiredTemperature(),0.001);
	}
	// Use Case ID: 3
	public void diplayRoomTemperatureTest() {
		Room room = new Room();
		room.setCurrentRoomTemperature(10);
		assertEquals(10, room.getCurrentRoomTemperature(),0.001);
	}
}
