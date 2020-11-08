package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import view.SHSGui;

public class SHPTest {

	@Test
	public void awayModeTest() {
		SHPController shp = new SHPController();
		shp.setAwayMode(true);
		assertTrue(shp.getAwayMode());
		shp.setAwayMode(false);
		assertFalse(shp.getAwayMode());
	}
	@Test
	public void authorityTimer() {
		SHPController shp = new SHPController();
		shp.setTimeToAlert(10);
		assertEquals(shp.getTimeToAlert(),10);
	}

}
