package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreatingJsonFile {

	/**
	 * Generate a .json file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JSONArray rooms = new JSONArray();
		rooms.add("BedRM");
		rooms.add("Master BedRM");
		rooms.add("BathRM");
		rooms.add("Kitchen");
		rooms.add("Living RM");
		rooms.add("Garage");
		JSONObject container = new JSONObject();
		container.put("Rooms", rooms);

		try {
			PrintWriter pw = new PrintWriter("myJSON.json");
			pw.write(container.toString());
			pw.flush();
			pw.close();

		}

		catch (IOException e) {
			e.getMessage();
		}

	}
}