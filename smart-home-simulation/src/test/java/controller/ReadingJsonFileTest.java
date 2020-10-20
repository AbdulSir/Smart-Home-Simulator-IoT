package controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ReadingJsonFileTest {

	// Use Case ID: 1
	@Test
	public void readingHouseLayoutTest() {
		File file = new File("myJSON.json");
		assertEquals(true,file.exists());
		ReadingJsonFile rjf = new ReadingJsonFile("myJSON.json");
		assertFalse(rjf.getRoomArray().equals(null));
	}

}
