package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Doors;
import model.Lights;
import model.Windows;

public class SHCTest {

	@Test
	// Delivery 2 Use Case 4. Open/Close doors
	public void doorTest() {
		Doors door = Doors.getDoor();
		door.setOpen(true);
		assertTrue(door.isOpen());
		door.setOpen(false);
		assertFalse(door.isOpen());
	}

	@Test
	// Delivery 2 Use Case 5. Turn lights On/Off
	public void lightTest() {
		Lights light = Lights.getLight();
		light.setLights(true);
		assertTrue(light.areLightsOn());
		light.setLights(false);
		assertFalse(light.areLightsOn());
	}

	@Test
	// Delivery 2 Use Case 6. Open/Close Windows
	public void windowTest() {
		Windows window = Windows.getWindow();
		window.setOpen(true);
		assertTrue(window.isOpen());
		window.setOpen(false);
		assertFalse(window.isOpen());
	}

	@Test
	// Delivery 2 Use Case 7. Set an Auto Mode
	public void autoModeTest() {
		SHCController shc = new SHCController();
		shc.setAutoModeState(true);
		assertTrue(shc.getAutoModeState());
		shc.setAutoModeState(false);
		assertFalse(shc.getAutoModeState());
	}

}
