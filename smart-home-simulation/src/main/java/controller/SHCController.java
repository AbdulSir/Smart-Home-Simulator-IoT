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
	private Users user;
	private Console console;
	private static boolean AutoModeState;

	public SHCController() {
	}

	public SHCController(SHSGui frame, Console console) {
		this.frame = frame;
		this.console = console;
		user = new Users();
		AutoModeState = false;
		
		// User Event Handler
		userEvents();
	}

	private void userEvents() {
		frame.getOpenDoorsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doors doors = new Doors();
				String location = frame.getDoorsComboBox().getSelectedItem().toString();
				int index = frame.getDoorsComboBox().getSelectedIndex();
				if (!doors.getDoorList().get(index).isOpen()) {
					doors.getDoorList().get(index).setOpen(true);
					frame.repaint();
					console.msg("The " + location + " door is open");
				} else {
					doors.getDoorList().get(index).setOpen(false);
					frame.repaint();
					console.msg("The " + location + " door is closed");
				}

			}
		});

		frame.getLightsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lights lights = new Lights();
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
			}

		});

		frame.getOpenWindowsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windows windows = new Windows();
				String location = frame.getOpenWindowsComboBox().getSelectedItem().toString();
				int index = frame.getOpenWindowsComboBox().getSelectedIndex();
				if (!windows.getWindowList().get(index).isOpen()) {
					windows.getWindowList().get(index).setOpen(true);
					frame.repaint();
					console.msg("The window in the " + location + " is open");
				} else {
					windows.getWindowList().get(index).setOpen(false);
					frame.repaint();
					console.msg("The window in the " + location + " is closed");
				}
			}
		});

		frame.getAutoModeToggleButton().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				RoomCounter rooms = new RoomCounter();
				Lights lights = new Lights();
				
				if (state == ItemEvent.SELECTED) {
					setAutoModeState(true);
					console.msg("Auto Mode ON");
					for(int i = 0; i < rooms.getRooms().size(); i++) {
						if(rooms.getRooms().get(i).getCount() > 0)
							lights.getLightsList().get(i).setLights(true);
						else if(rooms.getRooms().get(i).getCount() == 0) 
							lights.getLightsList().get(i).setLights(false);
					}
					frame.repaint();
				} else if (state == ItemEvent.DESELECTED) {
					setAutoModeState(false);
					console.msg("Auto Mode OFF");
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
}
