package model;

import java.util.ArrayList;

public class Windows {
	private String location;
	private boolean blocked;
	private boolean open;
	private static ArrayList<Windows> windowList = new ArrayList<Windows>();
	private static Windows window;
	/**
	 * Default Constructor
	 */
	private Windows() {
		location = "";
		blocked = false;
	}


	/**
	 * Parametrized Constructor
	 */
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

	/**
	 * Getter
	 */
	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * Getter
	 */
	public boolean isOpen() {
		return open;
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
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	/**
	 * Setter
	 */
	public static void setWindowList(ArrayList<Windows> windowList) {
		Windows.windowList = windowList;
	}
	public static Windows getWindow() {
		if (window != null)
			return window;
		else {
			Windows.window = new Windows();
			return window;
		}
	}
}