package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemperatureTest {
	// Use case ID 9
	@Test
	public void insideTemperatureTest() {
		Temperature temp = new Temperature();
		temp.setInsideTemp(15);
		assertEquals(15, temp.getInsideTemp());
	}
	public void outsideTemperatureTest() {
		Temperature temp = new Temperature();
		temp.setOutsideTemp(15);
		assertEquals(15, temp.getOutsideTemp());
	}
}
