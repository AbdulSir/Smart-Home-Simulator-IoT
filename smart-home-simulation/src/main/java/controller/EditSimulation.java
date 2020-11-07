package controller;

import java.awt.Color;
import java.awt.event.*;
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

	/**
	 * Constructor
	 */
	public EditSimulation(JButton editContext, Users user, Console console, SimulationButton simulationButton, SHSGui frame, SHCController core,
			SHPController security) {
		this.context = new ContextSimulation();
		this.editContext = editContext;
		this.user = user;
		this.console = console;
		this.simulationButton = simulationButton;
		this.frame = frame;
		this.core = core;
		this.security = security;
		windows = new Windows();
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
				Lights lights = new Lights();
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
				if (oldLocation.equalsIgnoreCase(newLocation) && oldLocation.equalsIgnoreCase("Outside"))
					console.msg(userToMove + " is still outside of the house");
				else if (oldLocation.equalsIgnoreCase(newLocation))
					console.msg(userToMove + " is still in the " + newLocation);
				else if (!newLocation.equalsIgnoreCase("Outside"))
					console.msg(userToMove + " has moved from the " + oldLocation + " to the " + newLocation);
				else
					console.msg(userToMove + " has moved from the " + oldLocation + " to outside of the house");

				core.checkLights();

				/** Motion detected during away mode **/
				if (security.getAwayMode() == true && !newLocation.equals("Outside")) {
					console.msg("Motion is detected in " + newLocation);
				}

				/** Alert authorities if motion is detected **/
				final Timer t = new Timer(10, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						int timeToAlert = security.getTimeToAlert();
						if (security.getAwayMode() == true && !newLocation.equals("Outside")) {
							if (timeToAlert != 0) {
								console.msg("Authorities will be alerted");
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
									}
								});
								t.setRepeats(false);
								t.start();
							} else
								console.msg("Authorities alerted");
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
					} else {
						windows.getWindowList().get(index).setBlocked(false);
						console.msg("The window in the " + location + " has been unblocked");
					}
					paint();
				} else {
					if (core.isUserLoggedIn())
						console.msg("You do not have the permission to execute this command");
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
					if (core.isUserLoggedIn())
						console.msg("You do not have the permission to execute this command");
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
					if (core.isUserLoggedIn())
						console.msg("You do not have the permission to execute this command");
				}
			}
		});

		/** Sends all of the users to the outside of the house **/
		this.context.getSendUsersOutisdeBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < user.getUserList().size(); i++) 
					user.getUserList().get(i).setLocation("Outside");
				paint();
				console.msg("All of the users have been moved to the outside of the house");
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
		if(simulationButton.isSimulatorState())
			frame.repaint();
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