package model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.swing.JSlider;

import org.junit.Test;

import view.SHSGui;

public class TimeSpeedTest {

	@Test
	// Delivery 3 Use Case 3. Change the speed of the simulation
	public void timeSpeedTest() {
		JSlider testSlider = new JSlider();
		Time testTime = Time.getWatch();
		testTime.setIncrement_time_value(10);
		assertEquals(testTime.getIncrement_time_value(), 10);
	}

}
