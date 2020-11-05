package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.Doors;
import model.Lights;
import model.RoomCounter;
import model.Windows;
import view.SHSGui;

public class SHCController {
	private SHSGui frame;
	private Console console;
	private Lights lights;
	private Doors doors;
	private RoomCounter rooms;
	private static boolean AutoModeState;

	public SHCController() {
	}

	public SHCController(SHSGui frame, Console console) {
		this.frame = frame;
		this.console = console;
		lights = new Lights();
		doors = new Doors();
		rooms = new RoomCounter();
		AutoModeState = false;

		// User Event Handler
		userEvents();
	}

	private void userEvents() {
		frame.getOpenDoorsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String location = frame.getDoorsComboBox().getSelectedItem().toString();
				int index = frame.getDoorsComboBox().getSelectedIndex();
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
				} else {
					console.msg("The door in the " + location + " is locked so it cannot be opened");
				}
			}
		});

		frame.getLightsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!getAutoModeState()) {
					String location = frame.getLightsComboBox().getSelectedItem().toString();
					int index = frame.getLightsComboBox().getSelectedIndex();
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
					console.msg("The ON/OFF button for the lights is disabled when Auto Mode is activated");
				}
			}

		});

		frame.getOpenWindowsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windows windows = new Windows();
				String location = frame.getOpenWindowsComboBox().getSelectedItem().toString();
				int index = frame.getOpenWindowsComboBox().getSelectedIndex();
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
						console.msg("The window in the " + location + " cannot be closed because its path is blocked");
					else
						console.msg("The window in the " + location + " cannot be opened because its path is blocked");
				}
			}
		});

		frame.getAutoModeToggleButton().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();

				if (state == ItemEvent.SELECTED) {
					setAutoModeState(true);
					console.msg("Auto Mode ON");
					checkLights();
					frame.repaint();
				} else if (state == ItemEvent.DESELECTED) {
					setAutoModeState(false);
					console.msg("Auto Mode OFF");
				}
			}
		});

		frame.getLockDoorsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String location = frame.getDoorsComboBox().getSelectedItem().toString();
				int index = frame.getDoorsComboBox().getSelectedIndex();
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
}
