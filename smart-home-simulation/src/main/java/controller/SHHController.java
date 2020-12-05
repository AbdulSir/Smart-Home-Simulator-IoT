package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import model.RoomCounter;
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
//	private ArrayList<RoomCounter> rooms;
	
	public SHHController() {}
	
	public SHHController(SHSGui frame) {
		this.frame = frame;
		this.console = Console.getConsole();
		this.user = Users.getUser();
//		this.rooms = RoomCounter.getRooms();
	}
	
	/**
	 * arbitrarily group rooms in a zone
	 * zone1 contains BedRM and Master BedRM
	 * zone2 contains LivingRM and Kitchen
	 * zone3 contains BathRM and Garage
	 */
	private RoomCounter[] zone1 = {RoomCounter.getBedRM(), RoomCounter.getMasterBedRM()};
	private RoomCounter[] zone2 = {RoomCounter.getLivingRM(), RoomCounter.getKitchen()};
	private RoomCounter[] zone3 = {RoomCounter.getBathRM(), RoomCounter.getGarage()};
	
	
	private void UserEvents() {
//		final JComboBox zoneComboBox = this.frame.getZoneComboBox();
//		zoneComboBox.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				String currentZoneSelected = zoneComboBox.getSelectedItem().toString();
//				switch(currentZoneSelected) {
//				case "ZONE 1":
//					frame.getDisplayZoneLabel().setText(currentZoneSelected + "" + zone1.toString());
//					break;
//				case "ZONE 2":
//					frame.getDisplayZoneLabel().setText(currentZoneSelected + "" + zone2.toString());
//					break;
//				case "ZONE 3":
//					frame.getDisplayZoneLabel().setText(currentZoneSelected + "" + zone3.toString());
//					break;
//				default:
//					frame.getDisplayZoneLabel().setText("");
//					break;	
//				}
//				frame.repaint();
//			}
//		});
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
