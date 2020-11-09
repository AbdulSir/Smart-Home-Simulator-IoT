package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class WindowsTest {

	@Test
	//Use Case id: 10
	public void windowsTest() {
		Windows window = Windows.getWindow();;
		window.setBlocked(true);
		assertEquals(true, window.isBlocked());
	}

}
