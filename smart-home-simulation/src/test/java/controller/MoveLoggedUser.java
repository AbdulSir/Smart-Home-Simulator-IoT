package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveLoggedUser {
	//Use Case ID 4
	@Test
	public void test() {
		//Check User Logged in
		Users user1 = new Users("test1");
		user1.setActiveUser(true);
		assertEquals(true, user1.getActiveUser());
		user1.setLocation("BedRoom");
		assertEquals("BedRoom", user1.getLocation());
		user1.setLocation("BathRoom");
		assertEquals("BathRoom", user1.getLocation());
	}

}
