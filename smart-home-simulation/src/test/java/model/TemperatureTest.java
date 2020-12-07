package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemperatureTest {
	
	// Use case ID 9
	@Test
	public void insideTemperatureTest() {
		Temperature temp = new Temperature();
		temp.setInsideTemp(15.00);
		assertTrue(15.00 == Temperature.getInsideTemp());
	}
	
	@Test
	public void outsideTemperatureTest() {
		Temperature temp = new Temperature();
		temp.setOutsideTemp(15.00);
		assertTrue(15.00 == Temperature.getOutsideTemp());
	}
}
