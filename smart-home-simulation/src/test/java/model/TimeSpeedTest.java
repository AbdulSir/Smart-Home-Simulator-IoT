package model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.swing.JSlider;

import org.junit.Test;

import view.SHSGui;

public class TimeSpeedTest {

	@Test
	public void test() {
		JSlider testSlider = new JSlider();
		Time testTime = new Time();
		testTime.setIncrement_time_value(10);
		assertEquals(testTime.getIncrement_time_value(),10);
	}
//	public void test2() {
//		SHSGui frame = new SHSGui();
//		String timeToString = new SimpleDateFormat("HH:mm").format(new java.util.Date());
//		frame.getTimeValue().setText(timeToString);
//	}
}
