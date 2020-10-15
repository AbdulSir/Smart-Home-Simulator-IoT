package controller;
import java.util.ArrayList;

public class Users {

	private String name;
	private Boolean activeUser;
	private static ArrayList<Users> userList = new ArrayList<Users>();
	private int userNumber;
	private static int counter = 0;
	
	public Users() {
		this.name = null;
		this.activeUser = null;
		this.userNumber = 0;
	}
	public Users(String name) {
		this.name = name;
		this.activeUser = false;
		getUserList().add(this);
		counter++;
		this.userNumber=counter;
		
	}
	// Method to remove active user from all users
	public void removeActiveUsers () {
		for (Users user: getUserList()) {
			user.activeUser = false;
		}
	}
	// Method to show infor about user array
	public void printUserList () {
		for (Users user: getUserList()) {
			System.out.println(user.name);
			System.out.println(user.userNumber);
			System.out.println(user.activeUser);
			System.out.println("-----------");
		}
	}
	public static ArrayList<Users> getUserList() {
		return userList;
	}
	public static void setUserList(ArrayList<Users> userList) {
		Users.userList = userList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
