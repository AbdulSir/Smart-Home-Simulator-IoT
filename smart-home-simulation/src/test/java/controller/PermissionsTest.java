package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Users;

public class PermissionsTest {

	@Test
	// Delivery 2 Use Case 1. Grant/Deny Access to commands based on profile's role
	public void test() {
		// creating users with permissions

		Users user1 = new Users("Test1", "PARENT");
		Users user2 = new Users("Test2", "STRANGER");
		Users user3 = new Users("Test3", "CHILDREN");
		Users user4 = new Users("Test4", "GUEST");

		// Checking if users have permissions
		assertEquals("PARENT", user1.getPermission());
		assertEquals("STRANGER", user2.getPermission());
		assertEquals("CHILDREN", user3.getPermission());
		assertEquals("GUEST", user4.getPermission());

	}

}
