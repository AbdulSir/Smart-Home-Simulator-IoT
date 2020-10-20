package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlacePeopleTest {

	@Test
	// Use case ID 8
	public void test() {
		Users user1 = new Users("test1");
		Users user2 = new Users("test2");
		user1.setActiveUser(true);
		assertEquals(true, user1.getActiveUser());
		assertEquals(false, user2.getActiveUser());
		String user1InitialLocation = user1.getLocation();
		String user2InitialLocation = user2.getLocation();
		user1.setLocation("location1");
		user2.setLocation("location2");
		
		assertFalse(user1.getLocation().equals(user1InitialLocation));
		assertFalse(user2.getLocation().equals(user2InitialLocation));
	}

}
