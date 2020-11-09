package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import view.SHSGui;

public class SHPTest {

	@Test
	// Delivery 2 Use Case 8. Set an Away Mode
	public void awayModeTest() {
		SHPController shp = new SHPController();
		shp.setAwayMode(true);
		assertTrue(shp.getAwayMode());
		shp.setAwayMode(false);
		assertFalse(shp.getAwayMode());
	}

	@Test
	// Delivery 2 Use Case 9. Notify users when motion detectors are triggered
	public void authorityTimerTest() {
		SHPController shp = new SHPController();
		shp.setTimeToAlert(10);
		assertEquals(shp.getTimeToAlert(), 10);
	}

	@Test
	// Delivery 2 Use Case 10. Set waiting time before contacting authorities
	public void setWaitTimeTest() {
		SHPController shp = new SHPController();
		shp.setTimeToAlert(15);
		assertEquals(shp.getTimeToAlert(), 15);
	}

}
