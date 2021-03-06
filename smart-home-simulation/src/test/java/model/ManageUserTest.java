package model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner;

import org.junit.Test;

import view.SHSGui;

public class ManageUserTest {
	// Use Case ID 2
	@Test
	public void addUserTest() {
		// Add user
		Users user = new Users("Test", "Parent");
		boolean userListContains = user.getUserList().contains(user);
		assertEquals(true, userListContains);

	}

	@Test
	public void deleteUserTest() {
		Users user = new Users("Test", "Parent");
		boolean userListContains = user.getUserList().contains(user);
		assertEquals(true, userListContains);
		user.getUserList().remove(user);
		userListContains = user.getUserList().contains(user);
		assertEquals(false, userListContains);
	}
}
