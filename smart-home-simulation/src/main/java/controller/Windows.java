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

	/**
	 * Getter
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Setter
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * List of Windows
	 * 
	 * @return All Windows
	 */
	public static ArrayList<Windows> getWindowList() {
		return windowList;
	}

	/**
	 * Setter
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	/**
	 * Setter
	 */
	public static void setWindowList(ArrayList<Windows> windowList) {
		Windows.windowList = windowList;
	}
}