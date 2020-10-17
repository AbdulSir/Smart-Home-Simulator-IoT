package controller;

import java.util.ArrayList;

public class Windows {
	private String location;
	private boolean blocked;
	private static ArrayList<Windows> windowList = new ArrayList<Windows>();
	
	public Windows() {
		location = "";
		blocked = false;
	}
	
	public Windows(String location) {
		this.location = location;
		blocked = false;
		windowList.add(this);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public static ArrayList<Windows> getWindowList() {
		return windowList;
	}

	public static void setWindowList(ArrayList<Windows> windowList) {
		Windows.windowList = windowList;
	}
}
