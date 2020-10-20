package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogInTest {

	@Test
	//Use Case ID: 4
	public void userLoggedIn() {
		//Check User Logged in
		Users user1 = new Users("test1");
		Users user2 = new Users("test2");
		user1.setActiveUser(true);
		assertEquals(true, user1.getActiveUser());
		assertEquals(false, user2.getActiveUser());
		//set User Location
		user1.setLocation("BedRoom");
		assertEquals("BedRoom", user1.getLocation());
	}

}
