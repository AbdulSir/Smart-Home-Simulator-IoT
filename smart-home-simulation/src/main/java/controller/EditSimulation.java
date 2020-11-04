package controller;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
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
	private SHSGui frame;

	/**
	 * Constructor
	 */
	public EditSimulation(JButton editContext, Users user, Console console, SHSGui frame) {
		this.context = new ContextSimulation();
		this.editContext = editContext;
		this.user = user;
		this.console = console;
		this.frame = frame;
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
				RoomCounter rooms = new RoomCounter();
				SHCController core = new SHCController();
				Lights lights = new Lights();
				JComboBox comboBoxUsers = context.getComboBoxUsers();
				int index = comboBoxUsers.getSelectedIndex();
				String userToMove = comboBoxUsers.getSelectedItem().toString();
				String oldLocation = user.getUserList().get(index).getLocation();
				user.getUserList().get(index).setLocation(context.getComboBoxLocation().getSelectedItem().toString());
				String newLocation = user.getUserList().get(index).getLocation();
				int newRoomIndex = 0, oldRoomIndex = 0;
				for (int i = 0; i < rooms.getRooms().size(); i++) {
					if(oldLocation.equals(newLocation))
						break;
					if (rooms.getRooms().get(i).getLocation().equals(oldLocation)) {
						int count = rooms.getRooms().get(i).getCount() - 1;
						rooms.getRooms().get(i).setCount(count);
						oldRoomIndex = i;
					} else if(rooms.getRooms().get(i).getLocation().equals(newLocation)) {
						int count = rooms.getRooms().get(i).getCount() + 1;
						rooms.getRooms().get(i).setCount(count);
						newRoomIndex = i;
					}
				}
				if (oldLocation.equalsIgnoreCase(newLocation) && oldLocation.equalsIgnoreCase("Outside"))
					console.msg(userToMove + " is still outside of the house");
				else if (oldLocation.equalsIgnoreCase(newLocation))
					console.msg(userToMove + " is still in the " + newLocation);
				else if (!newLocation.equalsIgnoreCase("Outside"))
					console.msg(userToMove + " has moved from the " + oldLocation + " to the " + newLocation);
				else
					console.msg(userToMove + " has moved from the " + oldLocation + " to outside of the house");

				if(core.getAutoModeState())
					lights.getLightsList().get(newRoomIndex).setLights(true);
				if(core.getAutoModeState() && rooms.getRooms().get(oldRoomIndex).getCount() == 0)
					lights.getLightsList().get(oldRoomIndex).setLights(false);
				frame.repaint();
			}
		});

		/** Block/Unblock Window **/
		this.context.getBlockButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windows windows = new Windows();
				String location = context.getComboBoxWindowLocation().getSelectedItem().toString();
				int index = context.getComboBoxWindowLocation().getSelectedIndex();
				if (!windows.getWindowList().get(index).isBlocked()) {
					windows.getWindowList().get(index).setBlocked(true);
					frame.repaint();
					console.msg("The window in the " + location + " has been blocked");
				} else {
					windows.getWindowList().get(index).setBlocked(false);
					frame.repaint();
					console.msg("The window in the " + location + " has been unblocked");
				}
			}
		});

		/** Block all Windows **/
		this.context.getBlockAllButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windows windows = new Windows();
				for (int i = 0; i < windows.getWindowList().size(); i++) {
					windows.getWindowList().get(i).setBlocked(true);
				}
				frame.repaint();
			}
		});
		
		/** Unblock all Windows **/
		this.context.getUnblockAllButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windows windows = new Windows();
				for (int i = 0; i < windows.getWindowList().size(); i++) {
					windows.getWindowList().get(i).setBlocked(false);
				}
				frame.repaint();
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