package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.SHPController;
import controller.SHSController;
import controller.SimulationButton;

public class HouseLayout extends JPanel {
	private BufferedImage closedWindow;
	private BufferedImage openedWindow;
	private BufferedImage closedDoor;
	private BufferedImage openedDoor;
	private BufferedImage lockedDoor;
	private BufferedImage lightOff;
	private BufferedImage lightOn;
	private BufferedImage userImage;
	private BufferedImage blocked;
	private BufferedImage awayModeIcon;
	private BufferedImage ac;
	private BufferedImage heater;
	private SHPController security;
	private ReadingJsonFile rjFile;
	private static HouseLayout houseLayout;
	
	
	public HouseLayout(ReadingJsonFile rjFile, SHPController security) {
		this.setSize(1000, 1000);
		this.rjFile = rjFile;
		this.security = security;
	}

	/**
	 * Draw Rooms
	 * 
	 * @param g
	 */
	public void drawRooms(Graphics g) {
		ArrayList<Room> rooms = Room.getRooms();
		try {
			int offSet = 0;
			int countOutside = 0;
			int countEntrance = 0;
			int countRoom = 0;
			userImage = ImageIO.read(getClass().getResource("/resources/user-2-icon.png"));
			ac = ImageIO.read(getClass().getResource("/resources/AC.png"));
			heater = ImageIO.read(getClass().getResource("/resources/Heater.png"));
			Users users = Users.getUser();
			ArrayList<Users> usersArray = users.getUserList();
			for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
				if (i == 0) {
					g.drawRect(0, 0, 400, 100);
					g.drawString("Outside", 25, 20);
					int x = 25, y = 30;
					for (int j = 0; j < usersArray.size(); j++) {
						if (usersArray.get(j).getLocation().equals("Outside")) {
							if (countOutside == 0) {
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.BLUE);
								if (usersArray.get(j).getUserNumber() < 10)
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 18);
								else
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 4, y + 18);
							}
							if (countOutside > 0) {
								x += 30;
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.BLUE);
								if (usersArray.get(j).getUserNumber() < 10)
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 18);
								else
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 4, y + 18);
							}
							countOutside++;
						}
					}
					g.setColor(Color.BLACK);
					g.translate(0, 100);
				}
				if (i < 4) {
					drawRoom(g, rjFile.getRoomArray().get(i).toString());
					if (rooms.get(i).isAc())
						g.drawImage(ac, 25, 0, 25, 25, null);
					if (rooms.get(i).isHeater())
						g.drawImage(heater, 25, 0, 25, 25, null);
					g.drawString("Temp: " + Math.round((rooms.get(rooms.size()-1).getCurrentRoomTemperature())*100.0)/100.0 + "\u00B0C ",10,65);
					int x = 40, y = 70;
					for (int j = 0; j < usersArray.size(); j++) {
						if (rjFile.getRoomArray().get(i).toString().equals(usersArray.get(j).getLocation())) {
							if (countRoom == 0) {
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.BLUE);
								if (usersArray.get(j).getUserNumber() < 10)
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 18);
								else
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 4, y + 18);
							}
							if (countRoom > 0) {
								if (x + 30 <= 125)
									x += 30;
								else if (y - 30 >= 0)
									y -= 30;
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.BLUE);
								if (usersArray.get(j).getUserNumber() < 10)
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 18);
								else
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 4, y + 18);
							}
							countRoom++;
						}
					}
					// draw rooms on the left
					countRoom = 0;
					g.setColor(Color.BLACK);
					g.translate(0, 100);
				}
				if (i == 4) {
					g.translate(150, -400);
					g.drawRect(0, 0, 100, 400);
					g.drawString("Entrance", 40, 200);
					g.drawString("Temp.: " + Math.round((rooms.get(rooms.size()-1).getCurrentRoomTemperature())*100.0)/100.0 + "\u00B0C ",10,370);
					int x = 5, y = 20;
					for (int j = 0; j < usersArray.size(); j++) {
						if (("Entrance").equals(usersArray.get(j).getLocation())) {
							if (countEntrance == 0) {
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.BLUE);
								if (usersArray.get(j).getUserNumber() < 10)
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 18);
								else
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 4, y + 18);
							}
							if (countEntrance > 0) {
								y += 30;
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.BLUE);
								if (usersArray.get(j).getUserNumber() < 10)
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 18);
								else
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 4, y + 18);
							}
							countEntrance++;
						}
					}
					g.setColor(Color.BLACK);
					g.translate(100, 0);
				}
				if (i >= 4) {
					drawRoom(g, rjFile.getRoomArray().get(i).toString());
					drawRoom(g, rjFile.getRoomArray().get(i).toString());
					if (rooms.get(i).isAc())
						g.drawImage(ac, 25, 0, 25, 25, null);
					if (rooms.get(i).isHeater())
						g.drawImage(heater, 25, 0, 25, 25, null);
					g.drawString("Temp.: " + Math.round((rooms.get(rooms.size()-1).getCurrentRoomTemperature())*100.0)/100.0 + "\u00B0C ",10,65);
					int x = 40, y = 70;
					for (int j = 0; j < usersArray.size(); j++) {
						if (rjFile.getRoomArray().get(i).toString().equals(usersArray.get(j).getLocation())) {
							if (countRoom == 0) {
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.BLUE);
								if (usersArray.get(j).getUserNumber() < 10)
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 18);
								else
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 4, y + 18);
							}
							if (countRoom > 0) {
								if (x + 30 <= 125)
									x += 30;
								else if (y - 30 >= 0)
									y -= 30;
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.BLUE);
								if (usersArray.get(j).getUserNumber() < 10)
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 18);
								else
									g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 4, y + 18);
							}
							countRoom++;
						}
					}
					countRoom = 0;
					g.setColor(Color.BLACK);
					g.translate(0, 100);
					offSet = offSet - 100;
				}
			}
			g.translate(-250, offSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draw Window
	 * 
	 * @param g
	 */
	public void drawWindows(Graphics g) {
		Windows windows = Windows.getWindow();
		try {
			int offSet = 0;
			closedWindow = ImageIO.read(getClass().getResource("/resources/closedWindow.png"));
			openedWindow = ImageIO.read(getClass().getResourceAsStream("/resources/openedWindow.png"));
			blocked = ImageIO.read(getClass().getResourceAsStream("/resources/blocked.png"));
			ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
			for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
				if (i < 4) {
					if (windows.getWindowList().get(i).isOpen())
						g.drawImage(openedWindow, 0, 0, 25, 25, null);
					else
						g.drawImage(closedWindow, 0, 0, 25, 25, null);
					if (windows.getWindowList().get(i).isBlocked())
						g.drawImage(blocked, 0, 25, 25, 25, null);
					g.translate(0, 100);
				}
				if (i == 4)
					g.translate(250, -400);
				if (i >= 4) {
					if (windows.getWindowList().get(i).isOpen())
						g.drawImage(openedWindow, 125, 0, 25, 25, null);
					else
						g.drawImage(closedWindow, 125, 0, 25, 25, null);
					if (windows.getWindowList().get(i).isBlocked())
						g.drawImage(blocked, 125, 25, 25, 25, null);
					g.translate(0, 100);
					offSet = offSet - 100;
				}
			}
			g.translate(-250, offSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draw Doors
	 * 
	 * @param g
	 */

	public void drawDoors(Graphics g) {
		Doors doors = Doors.getDoor();
		try {
			int offSet = 0;
			openedDoor = ImageIO.read(getClass().getResourceAsStream("/resources/openedDoor.png"));
			closedDoor = ImageIO.read(getClass().getResourceAsStream("/resources/closedDoor.png"));
			lockedDoor = ImageIO.read(getClass().getResourceAsStream("/resources/lock.png"));
			ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
			if (doors.getDoorList().get(doors.getDoorList().size() - 1).isOpen())
				g.drawImage(openedDoor, 215, 0, 25, 25, null);
			else
				g.drawImage(closedDoor, 215, 0, 25, 25, null);
			if (doors.getDoorList().get(doors.getDoorList().size() - 1).isLocked())
				g.drawImage(lockedDoor, 215, 25, 25, 25, null);
			for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
				if (i < 4) {
					if (doors.getDoorList().get(i).isOpen())
						g.drawImage(openedDoor, 125, 0, 25, 25, null);
					else
						g.drawImage(closedDoor, 125, 0, 25, 25, null);
					if (doors.getDoorList().get(i).isLocked())
						g.drawImage(lockedDoor, 125, 25, 25, 25, null);
					g.translate(0, 100);
				}
				if (i == 4)
					g.translate(250, -400);
				if (i >= 4) {
					if (doors.getDoorList().get(i).isOpen())
						g.drawImage(openedDoor, 0, 0, 25, 25, null);
					else
						g.drawImage(closedDoor, 0, 0, 25, 25, null);
					if (doors.getDoorList().get(i).isLocked())
						g.drawImage(lockedDoor, 0, 25, 25, 25, null);
					g.translate(0, 100);
					offSet = offSet - 100;
				}
			}
			g.translate(-250, offSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draw Light
	 * 
	 * @param g
	 */
	public void drawLights(Graphics g) {
		Lights lights = Lights.getLight();
		try {
			int offSet = 0;
			lightOff = ImageIO.read(getClass().getResourceAsStream("/resources/lightOff.png"));
			lightOn = ImageIO.read(getClass().getResourceAsStream("/resources/lightOn.png"));
			ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
			if (lights.getLightsList().get(lights.getLightsList().size() - 1).areLightsOn())
				g.drawImage(lightOn, 215, 375, 25, 25, null);
			else
				g.drawImage(lightOff, 215, 375, 25, 25, null);
			for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
				if (i < 4) {
					if (lights.getLightsList().get(i).areLightsOn())
						g.drawImage(lightOn, 0, 75, 25, 25, null);
					else
						g.drawImage(lightOff, 0, 75, 25, 25, null);
					g.translate(0, 100);
				}
				if (i == 4) {
					g.translate(250, -400);

				}
				if (i >= 4) {
					if (lights.getLightsList().get(i).areLightsOn())
						g.drawImage(lightOn, 0, 75, 25, 25, null);
					else
						g.drawImage(lightOff, 0, 75, 25, 25, null);
					g.translate(0, 100);
					offSet = offSet - 100;
				}
			}
			g.translate(-250, offSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draw Room
	 * 
	 * @param g
	 * @param s
	 */
	public void drawRoom(Graphics g, String s) {
		g.drawRect(0, 0, 150, 100);
		g.drawString(s, 40, 50);
	}

	// This method draws the AwayMode icon when away mode is activated
	public void drawAwayModeIcon(Graphics g) {
		try {
			if (security.getAwayMode() == true) {
				awayModeIcon = ImageIO.read(getClass().getResourceAsStream("/resources/AwayModeIcon.png"));
				g.drawImage(awayModeIcon, 215, 50, 25, 25, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Display
	 */
	public void paint(Graphics g) {
		drawRooms(g);
		drawWindows(g);
		drawDoors(g);
		drawLights(g);
		drawAwayModeIcon(g);
	}

	public static HouseLayout getHouseLayout() {
		if (houseLayout != null)
			return houseLayout;
		else {
			HouseLayout.houseLayout = new HouseLayout(SHSController.getSHSController().getRjFile(), SHPController.getSHPController());
			return houseLayout;
		}
	}
}
