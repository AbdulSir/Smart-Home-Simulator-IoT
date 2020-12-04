package controller;

import java.awt.Color;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;

import model.Lights;
import model.RoomCounter;
import model.Users;
import model.Windows;
import view.ContextSimulation;
import view.SHSGui;

public class EditSimulation {

	private JButton editContext;
	private ContextSimulation context;
	private Users user;
	private Console console;
	private SimulationButton simulationButton;
	private SHSGui frame;
	private Windows windows;
	private SHCController core;
	private SHPController security;
	private SHSController controller;
	private SHHController heat;
	private RoomCounter rooms;

	/**
	 * Constructor
	 */
	public EditSimulation(JButton editContext, Users user, Console console, SimulationButton simulationButton,
			SHSGui frame, SHCController core, SHPController security, SHSController controller, SHHController heat) {
		this.context = new ContextSimulation();
		this.editContext = editContext;
		this.user = user;
		this.console = console;
		this.simulationButton = simulationButton;
		this.frame = frame;
		this.core = core;
		this.security = security;
		windows = Windows.getWindow();
		rooms = RoomCounter.getRoomCounter();
		this.controller = controller;
		this.heat = heat;

		// event handler
		createEvents();
	}

	/**
	 * Event Handler
	 */
	private void createEvents() {

		/** Edit the context of a simulation **/
		editContext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				context.NewScreen(context);
			}
		});

		/** Change the location of a user **/
		this.context.getSetLocation().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lights lights = Lights.getLight();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				JComboBox comboBoxUsers = context.getComboBoxUsers();
				int index = comboBoxUsers.getSelectedIndex();
				String userToMove = comboBoxUsers.getSelectedItem().toString();
				String oldLocation = user.getUserList().get(index).getLocation();
				user.getUserList().get(index).setLocation(context.getComboBoxLocation().getSelectedItem().toString());
				String newLocation = user.getUserList().get(index).getLocation();
				int newRoomIndex = 0, oldRoomIndex = 0;
				for (int i = 0; i < rooms.getRooms().size(); i++) {
					if (oldLocation.equals(newLocation))
						break;
					if (rooms.getRooms().get(i).getLocation().equals(oldLocation) && !oldLocation.equals("Outside"))
						rooms.getRooms().get(i).decrementCounter();
					else if (rooms.getRooms().get(i).getLocation().equals(newLocation)
							&& !newLocation.equals("Outside"))
						rooms.getRooms().get(i).incrementCounter();

				}
				if (oldLocation.equalsIgnoreCase(newLocation) && oldLocation.equalsIgnoreCase("Outside")) {
					console.msg(userToMove + " is still outside of the house");
					controller.appendToLog(userToMove + " is still outside of the house");
				} else if (oldLocation.equalsIgnoreCase(newLocation)) {
					console.msg(userToMove + " is still in the " + newLocation);
					controller.appendToLog(userToMove + " is still in the " + newLocation);
				} else if (!newLocation.equalsIgnoreCase("Outside")) {
					console.msg(userToMove + " has moved from the " + oldLocation + " to the " + newLocation);
					controller
							.appendToLog(userToMove + " has moved from the " + oldLocation + " to the " + newLocation);
				} else {
					console.msg(userToMove + " has moved from the " + oldLocation + " to outside of the house");
					controller.appendToLog(
							userToMove + " has moved from the " + oldLocation + " to outside of the house");
				}

				core.checkLights();

				/** Motion detected during away mode **/
				if (security.getAwayMode() == true && !newLocation.equals("Outside")) {
					console.msg("Motion is detected in " + newLocation);
					core.appendToLog("[" + formatter.format(date) + "] " + "Motion is detected in " + newLocation);
				}

				/** Alert authorities if motion is detected **/
				final Timer t = new Timer(10, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						int timeToAlert = security.getTimeToAlert();
						if (security.getAwayMode() == true && !newLocation.equals("Outside")) {
							if (timeToAlert != 0) {
								console.msg("Authorities will be alerted");
								core.appendToLog("[" + formatter.format(date) + "] " + "Authorities will be alerted");
								final Timer t = new Timer(10, new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										int timeToAlert = security.getTimeToAlert();
										while (timeToAlert != 0) {
											timeToAlert--;
											try {
												Thread.sleep(1000);
											} catch (InterruptedException ie) {
												Thread.currentThread().interrupt();
											}
										}
										console.msg("Authorities alerted");
										core.appendToLog("[" + formatter.format(date) + "] " + "Authorities alerted");
									}
								});
								t.setRepeats(false);
								t.start();
							} else {
								console.msg("Authorities alerted");
								core.appendToLog("[" + formatter.format(date) + "] " + "Authorities alerted");
							}
						}
					}
				});
				t.setRepeats(false);
				t.start();

				paint();
			}
		});

		/** Block/Unblock Window **/
		this.context.getBlockButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String location = context.getComboBoxWindowLocation().getSelectedItem().toString();
				int index = context.getComboBoxWindowLocation().getSelectedIndex();
				Users loggedUser = user.getLoggedUser();
				if (core.hasPermissions(loggedUser, location, "Windows")) {
					if (!windows.getWindowList().get(index).isBlocked()) {
						windows.getWindowList().get(index).setBlocked(true);
						console.msg("The window in the " + location + " has been blocked");
						controller.appendToLog("The window in the " + location + " has been blocked");
					} else {
						windows.getWindowList().get(index).setBlocked(false);
						console.msg("The window in the " + location + " has been unblocked");
						controller.appendToLog("The window in the " + location + " has been unblocked");
					}
					paint();
				} else {
					if (core.isUserLoggedIn()) {
						if (!loggedUser.getLocation().equals(location)
								&& !loggedUser.getPermission().equals("STRANGER")) {
							console.msg("You do not have the permission to execute this command. Reason: Location");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Location");
						} else if (security.getAwayMode() && !loggedUser.getPermission().equals("STRANGER")) {
							console.msg(
									"You do not have the permission to execute this command. Reason: Away Mode is activated");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Away Mode is activated");
						} else {
							console.msg(
									"You do not have the permission to execute this command. Reason: Permission status of user");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Permission status of user");
						}
					}
				}
			}
		});

		/** Block all Windows **/
		this.context.getBlockAllButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users loggedUser = user.getLoggedUser();
				if (core.hasPermissions(loggedUser, "ALL", "Windows")) {
					for (int i = 0; i < windows.getWindowList().size(); i++) {
						windows.getWindowList().get(i).setBlocked(true);
					}
					paint();
				} else {
					if (core.isUserLoggedIn()) {
						if (!loggedUser.getLocation().equals("ALL") && !loggedUser.getPermission().equals("STRANGER")) {
							console.msg("You do not have the permission to execute this command. Reason: Location");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Location");
						} else if (security.getAwayMode() && !loggedUser.getPermission().equals("STRANGER")) {
							console.msg(
									"You do not have the permission to execute this command. Reason: Away Mode is activated");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Away Mode is activated");
						} else {
							console.msg(
									"You do not have the permission to execute this command. Reason: Permission status of user");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Permission status of user");
						}
					}
				}
			}
		});

		/** Unblock all Windows **/
		this.context.getUnblockAllButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users loggedUser = user.getLoggedUser();
				if (core.hasPermissions(loggedUser, "ALL", "Windows")) {
					for (int i = 0; i < windows.getWindowList().size(); i++) {
						windows.getWindowList().get(i).setBlocked(false);
					}
					paint();
				} else {
					if (core.isUserLoggedIn()) {
						if (!loggedUser.getLocation().equals("ALL") && !loggedUser.getPermission().equals("STRANGER")) {
							console.msg("You do not have the permission to execute this command. Reason: Location");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Location");
						} else if (security.getAwayMode() && !loggedUser.getPermission().equals("STRANGER")) {
							console.msg(
									"You do not have the permission to execute this command. Reason: Away Mode is activated");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Away Mode is activated");
						} else {
							console.msg(
									"You do not have the permission to execute this command. Reason: Permission status of user");
							controller.appendToLog(
									"You do not have the permission to execute this command. Reason: Permission status of user");
						}
					}
				}
			}
		});

		/** Sends all of the users to the outside of the house **/
		this.context.getSendUsersOutisdeBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < user.getUserList().size(); i++) {
					for (int j = 0; j < rooms.getRooms().size(); j++) {
						if (user.getUserList().get(i).getLocation().equals(rooms.getRooms().get(j).getLocation())) {
							rooms.getRooms().get(j).decrementCounter();
							break;
						}
					}
					user.getUserList().get(i).setLocation("Outside");
				}
				paint();
				console.msg("All of the users have been moved to the outside of the house");
				controller.appendToLog("All of the users have been moved to the outside of the house");
			}
		});

		this.context.getSetSeasonsBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String winterStart = context.getComboBoxWinterStart().getSelectedItem().toString();
				String winterEnd = context.getComboBoxWinterEnd().getSelectedItem().toString();
				String summerStart = context.getComboBoxSummerStart().getSelectedItem().toString();
				String summerEnd = context.getComboBoxSummerEnd().getSelectedItem().toString();
				int winterStartMonth = getNumericalMonth(winterStart);
				int winterEndMonth = getNumericalMonth(winterEnd);
				int summerStartMonth = getNumericalMonth(summerStart);
				int summerEndMonth = getNumericalMonth(summerEnd);
				ArrayList<Integer> winter = createSeason(winterStartMonth,winterEndMonth);
				ArrayList<Integer> summer = createSeason(summerStartMonth,summerEndMonth);
				if (!hasOverlap(winter, summer)) {
					heat.setSummerSeason(getStringSeason(summer));
					heat.setWinterSeason(getStringSeason(winter));
					console.msg("Winter months have been set from " + winterStart + " to " + winterEnd);
					console.msg("Summer months have been set from " + summerStart + " to " + summerEnd);
				} else {
					console.msg("The winter and summer months are not valid because they overlap each other");
				}
			}
		});

		/** User ComboBox **/
		PopupMenuListener userListListener = new PopupMenuListener() {
			boolean initialized = false;

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if (!initialized) {
					String[] userNameArray = new String[user.getUserList().size()];
					userNameArray = user.getUserStringArray();
					context.getComboBoxUsers().setModel(new DefaultComboBoxModel(userNameArray));
				}
			}
		};
		context.getComboBoxUsers().addPopupMenuListener(userListListener);
	}

	/**
	 * Repaints frame if the simulator is on
	 */
	private void paint() {
		if (simulationButton.isSimulatorState())
			frame.repaint();
	}

	private int getNumericalMonth(String month) {
		switch (month) {
		case "January":
			return 1;
		case "February":
			return 2;
		case "March":
			return 3;
		case "April":
			return 4;
		case "May":
			return 5;
		case "June":
			return 6;
		case "July":
			return 7;
		case "August":
			return 8;
		case "September":
			return 9;
		case "October":
			return 10;
		case "November":
			return 11;
		case "December":
			return 12;
		default:
			return 0;
		}
	}
	
	private ArrayList<String> getStringSeason(ArrayList<Integer> season) {
		ArrayList<String> seasonString = new ArrayList<String>(); 
		for (int a : season) {
			switch(a) {
			case 1:
				seasonString.add("January");
				break;
			case 2:
				seasonString.add("February");
				break;
			case 3:
				seasonString.add("March");
				break;
			case 4:
				seasonString.add("April");
				break;
			case 5:
				seasonString.add("May");
				break;
			case 6:
				seasonString.add("June");
				break;
			case 7:
				seasonString.add("July");
				break;
			case 8:
				seasonString.add("August");
				break;
			case 9:
				seasonString.add("September");
				break;
			case 10:
				seasonString.add("October");
				break;
			case 11:
				seasonString.add("November");
				break;
			case 12:
				seasonString.add("December");
				break;
			default:
				break;
			}
		}
		return seasonString;
	}

	private ArrayList<Integer> createSeason(int start, int end) {
		ArrayList<Integer> season = new ArrayList<Integer>();
		int a = start;
		while (!season.contains(start) || !season.contains(end)) {
			season.add(a);
			if(a == 12)
				a = 1;
			else
				a++;
		}
		return season;
	}
	
	private boolean hasOverlap(ArrayList<Integer> winter, ArrayList<Integer> summer) {
		for (int i = 0; i < winter.size(); i++) {
			for (int j = 0; j < summer.size(); j++) {
				if(winter.get(i) == summer.get(j))
					return true;
			}
		}
		return false;
	}
	/**
	 * Getter
	 */
	public JButton getEditContext() {
		return editContext;
	}

	/**
	 * Setter
	 */
	public void setEditContext(JButton editContext) {
		this.editContext = editContext;
	}

	/**
	 * Getter
	 */
	public ContextSimulation getContext() {
		return context;
	}

	/**
	 * Setter
	 */
	public void setContext(ContextSimulation context) {
		this.context = context;
	}

	/**
	 * Getter
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * Setter
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * Getter
	 */
	public Console getConsole() {
		return console;
	}

	/**
	 * Setter
	 */
	public void setConsole(Console console) {
		this.console = console;
	}

}