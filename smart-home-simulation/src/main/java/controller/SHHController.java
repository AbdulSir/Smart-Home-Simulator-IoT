package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Temperature;
import model.Users;
import view.SHSGui;

public class SHHController {
	private SHSGui frame;
	private Console console;
	private Users user;
	private Temperature temperature;
	private ArrayList<String> winterMonths;
	private ArrayList<String> summerMonths;
	private static SHHController shhController;
	
	public SHHController() {}
	
	public SHHController(SHSGui frame) {
		this.frame = frame;
		this.console = Console.getConsole();
		user = Users.getUser();
	}
	
	/**
	 * Getter 
	 */
	public ArrayList<String> getWinterSeason() {
		return winterMonths;
	}

	/**
	 * Setter 
	 */
	public void setWinterSeason(ArrayList<String> winterMonths) {
		this.winterMonths = winterMonths;
	}
	/**
	 * Getter 
	 */
	public ArrayList<String> getSummerSeason() {
		return summerMonths;
	}

	/**
	 * Setter 
	 */
	public void setSummerSeason(ArrayList<String> summerMonths) {
		this.summerMonths = summerMonths;
	}
	
	public static SHHController getSHHController() {
		if (shhController != null)
			return shhController;
		else {
			SHHController.shhController = new SHHController(SHSGui.getSHS());
			return shhController;
		}
		
	}
	
}
