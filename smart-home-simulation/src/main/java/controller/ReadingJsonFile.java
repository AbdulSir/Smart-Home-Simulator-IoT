package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadingJsonFile {

	private JSONArray roomArray;

	public ReadingJsonFile(String fileName) {
		JSONParser parser = new JSONParser();	
		try (FileReader reader = new FileReader(fileName))
		{
			Object obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;
			roomArray = (JSONArray) jsonObject.get("Rooms");
	        reader.close();
		}
		catch (FileNotFoundException e) {
			e.getMessage();
		}
		catch (IOException e) {
			e.getMessage();
		}

		catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
	}

	public JSONArray getRoomArray() {
		return roomArray;
	}

	public void setRoomArray(JSONArray roomArray) {
		this.roomArray = roomArray;
	}

}