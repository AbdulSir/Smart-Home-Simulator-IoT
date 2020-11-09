package model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TimeTest {

	@Test
	//Use Case ID 3
	public void timeTest() {
		Time timeTest = Time.getWatch();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,17);
		cal.set(Calendar.MINUTE,30);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.DATE, 12);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.YEAR, 1995);
		date = cal.getTime();
		timeTest.setDate(date);
		assertEquals(timeTest.getDate(),date);
		
	}
	public void dateTest() {
		Time timeTest = Time.getWatch();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 12);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.YEAR, 1995);
		date = cal.getTime();
		timeTest.setDate(date);
		assertEquals(timeTest.getDate(),date);
	}

}
