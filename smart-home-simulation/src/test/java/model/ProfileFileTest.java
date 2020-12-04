package model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class ProfileFileTest {

	@Test
	// Delivery 2 Use Case 2. Save the user profiles in a file
	public void test() {
		// Create user
		Users user1 = new Users("test1", "CHILDREN");
		try {
			// File & Object Output Stream
			FileOutputStream fos = new FileOutputStream(new File("JUnitTest.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// Write User Object
			oos.writeObject(user1);

			// Close Stream
			fos.close();
			oos.close();
		}

		catch (FileNotFoundException file_exception) {
			file_exception.printStackTrace();
		}

		catch (IOException io_exception) {
			io_exception.printStackTrace();
		}
		// creating second user
		Users user2 = null;
		// reading object from text file
		try {
			FileInputStream fis = new FileInputStream(new File("JUnitTest.txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			user2 = (Users) ois.readObject();

		}

		catch (IOException io_exception) {
			System.out.println("File not found");
			io_exception.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Checking if user1 and user2 are the same
		assertEquals(user1.getName(), user2.getName());
		assertEquals(user1.getPermission(), user2.getPermission());

	}

}
