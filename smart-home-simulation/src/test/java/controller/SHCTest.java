package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Doors;
import model.Lights;
import model.Windows;

public class SHCTest {

	@Test
	public void doorTest() {
		Doors door = Doors.getDoor();
		door.setOpen(true);
		assertTrue(door.isOpen());
		door.setOpen(false);
		assertFalse(door.isOpen());
	}
	@Test
	public void windowTest() {
		Windows window = new Windows();
		window.setOpen(true);
		assertTrue(window.isOpen());
		window.setOpen(false);
		assertFalse(window.isOpen());		
	}
	@Test
	public void lightTest() {
		Lights light = new Lights();
		light.setLights(true);
		assertTrue(light.areLightsOn());
		light.setLights(false);
		assertFalse(light.areLightsOn());		
	}
	@Test
	public void autoModeTest() {
		SHCController shc = new SHCController();
		shc.setAutoModeState(true);
		assertTrue(shc.getAutoModeState());
		shc.setAutoModeState(false);
		assertFalse(shc.getAutoModeState());
	}
	
}
