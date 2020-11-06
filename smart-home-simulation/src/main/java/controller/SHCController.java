package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.Doors;
import model.Lights;
import model.RoomCounter;
import model.Users;
import model.Windows;
import view.SHSGui;

public class SHCController {
	private SHSGui frame;
	private Console console;
	private SHPController securityController;
	private Users user;
	private Lights lights;
	private Doors doors;
	private RoomCounter rooms;
	private static boolean AutoModeState;
	private boolean isUserLoggedIn;

	public SHCController() {
	}

	/**
	 * 
	 * @param frame
	 * @param console
	 */
	public SHCController(SHSGui frame, SHPController securityController) {
		this.frame = frame;
		this.console = new Console(frame.getTextAreaConsoleLog());
		/** SHP Controller **/
		this.securityController = securityController;
		user = new Users();
		lights = new Lights();
		doors = new Doors();
		rooms = new RoomCounter();
		AutoModeState = false;
		isUserLoggedIn = true;

		// User Event Handler
		userEvents();
	}

	private void userEvents() {

		/**
		 * Open/Close doors
		 */
		frame.getOpenDoorsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String location = frame.getDoorsComboBox().getSelectedItem().toString();
				int index = frame.getDoorsComboBox().getSelectedIndex();
				Users loggedUser = user.getLoggedUser();
				if (hasPermissions(loggedUser, location, "Doors")) {
					if (!doors.getDoorList().get(index).isLocked()) {
						if (!doors.getDoorList().get(index).isOpen()) {
							doors.getDoorList().get(index).setOpen(true);
							frame.repaint();
							console.msg("The " + location + " door is open");
						} else {
							doors.getDoorList().get(index).setOpen(false);
							frame.repaint();
							console.msg("The " + location + " door is closed");
						}
					} else
						console.msg("The door in the " + location + " is locked so it cannot be opened");
				} else {
					if (isUserLoggedIn)
						console.msg("You do not have the permission to execute this command");
				}
			}
		});

		/**
		 * Turn on/off lights
		 */
		frame.getLightsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!getAutoModeState()) {
					String location = frame.getLightsComboBox().getSelectedItem().toString();
					int index = frame.getLightsComboBox().getSelectedIndex();
					Users loggedUser = user.getLoggedUser();
					if (hasPermissions(loggedUser, location, "Lights")) {
						if (!lights.getLightsList().get(index).areLightsOn()) {
							lights.getLightsList().get(index).setLights(true);
							frame.repaint();
							console.msg("The light in the " + location + " is on");
						} else {
							lights.getLightsList().get(index).setLights(false);
							frame.repaint();
							console.msg("The light in the " + location + " is off");
						}
					} else {
						if (isUserLoggedIn)
							console.msg("You do not have the permission to execute this command");
					}
				} else
					console.msg("The ON/OFF button for the lights is disabled when Auto Mode is activated");
			}

		});

		/**
		 * Open/Close windows
		 */
		frame.getOpenWindowsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windows windows = new Windows();
				String location = frame.getOpenWindowsComboBox().getSelectedItem().toString();
				int index = frame.getOpenWindowsComboBox().getSelectedIndex();
				Users loggedUser = user.getLoggedUser();
				if (hasPermissions(loggedUser, location, "Windows")) {
					if (!windows.getWindowList().get(index).isBlocked()) {
						if (!windows.getWindowList().get(index).isOpen()) {
							windows.getWindowList().get(index).setOpen(true);
							frame.repaint();
							console.msg("The window in the " + location + " is open");
						} else {
							windows.getWindowList().get(index).setOpen(false);
							frame.repaint();
							console.msg("The window in the " + location + " is closed");
						}
					} else {
						if (windows.getWindowList().get(index).isOpen())
							console.msg(
									"The window in the " + location + " cannot be closed because its path is blocked");
						else
							console.msg(
									"The window in the " + location + " cannot be opened because its path is blocked");
					}
				} else {
					if (isUserLoggedIn)
						console.msg("You do not have the permission to execute this command");
				}
			}
		});

		/**
		 * Activate/Deactivate Auto Mode
		 */
		frame.getAutoModeToggleButton().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				Users loggedUser = user.getLoggedUser();
				if (hasPermissions(loggedUser, user.getLocation(), "Lights")) {
					if (state == ItemEvent.SELECTED) {
						setAutoModeState(true);
						console.msg("Auto Mode ON");
						checkLights();
						frame.repaint();
					} else if (state == ItemEvent.DESELECTED) {
						setAutoModeState(false);
						console.msg("Auto Mode OFF");
					}
				} else {
					if (state == ItemEvent.SELECTED && isUserLoggedIn)
						console.msg("You do not have the permission to execute this command");
				}
			}
		});

		/**
		 * Lock/Unlock doors
		 */
		frame.getLockDoorsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String location = frame.getDoorsComboBox().getSelectedItem().toString();
				int index = frame.getDoorsComboBox().getSelectedIndex();
				Users loggedUser = user.getLoggedUser();
				if (hasPermissions(loggedUser, location, "Doors")) {
					if (!doors.getDoorList().get(index).isOpen() && !doors.getDoorList().get(index).isLocked()) {
						doors.getDoorList().get(index).setLocked(true);
						frame.repaint();
						console.msg("The door in the " + location + " has been locked");
					} else if (doors.getDoorList().get(index).isLocked()) {
						doors.getDoorList().get(index).setLocked(false);
						frame.repaint();
						console.msg("The door in the " + location + " has been unlocked");
					} else if (doors.getDoorList().get(index).isOpen() && !doors.getDoorList().get(index).isLocked()) {
						console.msg("The door in the " + location + " cannot be locked because its open");
					}
				} else {
					if (isUserLoggedIn)
						console.msg("You do not have the permission to execute this command");
				}
			}
		});
	}

	/**
	 * Getter
	 */
	public boolean getAutoModeState() {
		return AutoModeState;
	}

	/**
	 * Setter
	 */
	public static void setAutoModeState(boolean autoModeState) {
		AutoModeState = autoModeState;
	}

	/**
	 * Getter
	 */
	public boolean isUserLoggedIn() {
		return isUserLoggedIn;
	}

	/**
	 * Setter
	 */
	public void setUserLoggedIn(boolean isUserLoggedIn) {
		this.isUserLoggedIn = isUserLoggedIn;
	}

	/**
	 * When Auto Mode is activated, this method will check all of the rooms and turn
	 * on the lights if its occupied. It will turn the lights off, if a room is
	 * unoccupied.
	 */
	public void checkLights() {
		if (getAutoModeState()) {
			for (int i = 0; i < rooms.getRooms().size(); i++) {
				if (rooms.getRooms().get(i).getCount() > 0)
					lights.getLightsList().get(i).setLights(true);
				else if (rooms.getRooms().get(i).getCount() == 0)
					lights.getLightsList().get(i).setLights(false);
			}
		}
	}

	/**
	 * Determines if the logged-in user has access to these commands
	 * 
	 * @param user
	 * @param location
	 * @param item
	 * @return
	 */
	public boolean hasPermissions(Users user, String location, String item) {
		if (user == null) {
			console.msg("The system does not have a logged-in user");
			isUserLoggedIn = false;
			return false;
		}
		switch (user.getPermission()) {
		case "PARENT":
			isUserLoggedIn = true;
			return true;
		case "CHILDREN":
			isUserLoggedIn = true;
			if (user.getLocation().equals(location) && (item.equals("Windows") || item.equals("Lights"))
					&& !securityController.getAwayMode())
				return true;
			else
				return false;
		case "GUEST":
			isUserLoggedIn = true;
			if (user.getLocation().equals(location) && (item.equals("Windows") || item.equals("Lights"))
					&& !securityController.getAwayMode())
				return true;
			else
				return false;
		case "STRANGER":
			isUserLoggedIn = true;
			return false;
		default:
			return false;
		}
	}
}
