package controller;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JSpinner;

import org.junit.Test;

import view.SHSGui;

public class ManageUserTest {

	@Test
	public void addUserTest() {
		// Add user
		Users user = new Users("Test");
		boolean userListContains = user.getUserList().contains(user);
		assertEquals(true,userListContains);
		
	}
	@Test
	public void deleteUserTest() {
		Users user = new Users("Test");
		boolean userListContains = user.getUserList().contains(user);
		assertEquals(true,userListContains);
		user.getUserList().remove(user);
		userListContains = user.getUserList().contains(user);
		assertEquals(false,userListContains);
	}
}
