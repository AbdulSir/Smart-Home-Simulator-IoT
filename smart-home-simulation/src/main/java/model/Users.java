package model;

import java.util.ArrayList;

public class Users {

	private String name;
	private Boolean activeUser;
	private static ArrayList<Users> userList = new ArrayList<Users>();
	private int userNumber;
	private static int counter = 0;
	private String location;

	/**
	 * Class Constructor
	 */
	public Users() {
		this.name = null;
		this.activeUser = null;
		this.userNumber = 0;
		this.location = null;
	}

	/**
	 * Class Constructor specifying number of objects to create
	 */
	public Users(String name) {
		this.name = name;
		this.activeUser = false;
		this.location = "Entrance";
		getUserList().add(this);
		counter++;
		this.userNumber = counter;
	}

	/**
	 * Method to make all users inactive
	 */
	public void removeActiveUsers() {
		for (Users user : getUserList()) {
			user.activeUser = false;
		}
	}

	/**
	 * Method to print user info
	 */
	public void printUserList() {
		for (Users user : getUserList()) {
			System.out.println(user.name);
			System.out.println(user.userNumber);
			System.out.println(user.activeUser);
			System.out.println("-----------");
		}
	}	
	
	/**
	 * 
	 * Returns string array of userList
	 */
	public String[] getUserStringArray() {
		String[] userNameArray = new String[userList.size()];
		for (int i = 0; i < userNameArray.length; i++) {
			userNameArray[i] = userList.get(i).getName();
		}
		return userNameArray;
	}

	/**
	 * Getter userList
	 */
	public ArrayList<Users> getUserList() {
		return userList;
	}

	/**
	 * Setter userList
	 */
	public static void setUserList(ArrayList<Users> userList) {
		Users.userList = userList;
	}

	/**
	 * Getter name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Setter location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Getter activeUser
	 */
	public Boolean getActiveUser() {
		return activeUser;
	}

	/**
	 * Setter activeUser
	 */
	public void setActiveUser(Boolean activeUser) {
		this.activeUser = activeUser;
	}

	/**
	 * Getter userNumber
	 */
	public int getUserNumber() {
		return userNumber;
	}

	/**
	 * Setter userNumber
	 */
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
}