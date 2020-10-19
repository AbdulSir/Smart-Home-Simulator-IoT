package controller;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import view.ContextSimulation;
import view.SHSGui;

public class EditSimulation {

	private JButton editContext;
	private ContextSimulation context;
	private Users user;
	private Console console;
	Windows bedroomWindows = new Windows("BedRM");
	Windows MasterBedroomWindows = new Windows("Master BedRM");
	Windows bathroomWindows = new Windows("BathRM");
	Windows kitchenWindows = new Windows("Kitchen");
	Windows livingRoomWindows = new Windows("Living RM");
	Windows garageWindows = new Windows("Garage");
	private SHSGui frame;
	private String[] rooms;

	/**
	 * Constructor
	 */
	public EditSimulation(JButton editContext, Users user, Console console, SHSGui frame) {
		this.context = new ContextSimulation();
		this.editContext = editContext;
		this.user = user;
		this.console = console;
		this.frame = frame;
		 //event handler
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
				JComboBox comboBoxUsers = context.getComboBoxUsers();
				int index = comboBoxUsers.getSelectedIndex();
				String userToMove = comboBoxUsers.getSelectedItem().toString();
				String oldLocation = user.getUserList().get(index).getLocation();
				user.getUserList().get(index).setLocation(context.getComboBoxLocation().getSelectedItem().toString());
				String newLocation = user.getUserList().get(index).getLocation();
				if (oldLocation.equalsIgnoreCase(newLocation) && oldLocation.equalsIgnoreCase("Outside"))
					console.msg(userToMove + " is still outside of the house");
				else if (oldLocation.equalsIgnoreCase(newLocation))
					console.msg(userToMove + " is still in the " + newLocation);
				else if (!newLocation.equalsIgnoreCase("Outside"))
					console.msg(userToMove + " has moved from the " + oldLocation + " to the " + newLocation);
				else
					console.msg(userToMove + " has moved from the " + oldLocation + " to outside of the house");
				frame.repaint();
			}
		});

		/** Block/Unblock Windows **/
		this.context.getBlockWindows().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox bathroom = context.getBathroomCheckBox();
				JCheckBox bedroom = context.getBedroomCheckBox();
				JCheckBox garage = context.getGarageCheckBox();
				JCheckBox kitchen = context.getKitchenCheckBox();
				JCheckBox livingRoom = context.getLivingRoomCheckBox();
				JCheckBox MasterBedroom = context.getMasterBedroomCheckBox();

				console.msg("**********Updating status of the windows**********");

				if (bathroom.isSelected()) {
					bathroomWindows.setBlocked(true);
					console.msg("Bathroom Window: BLOCKED");
				} else {
					bathroomWindows.setBlocked(false);
					console.msg("Bathroom Window: UNBLOCKED");
				}
				if (bedroom.isSelected()) {
					bedroomWindows.setBlocked(true);
					console.msg("Bedroom Window: BLOCKED");
				} else {
					bedroomWindows.setBlocked(false);
					console.msg("Bedroom Window: UNBLOCKED");
				}
				if (garage.isSelected()) {
					garageWindows.setBlocked(true);
					console.msg("Garage Window: BLOCKED");
				} else {
					garageWindows.setBlocked(false);
					console.msg("Garage Window: UNBLOCKED");
				}
				if (kitchen.isSelected()) {
					kitchenWindows.setBlocked(true);
					console.msg("Kitchen Window: BLOCKED");
				} else {
					kitchenWindows.setBlocked(false);
					console.msg("Kitchen Window: UNBLOCKED");
				}
				if (livingRoom.isSelected()) {
					livingRoomWindows.setBlocked(true);
					console.msg("Living Room Window: BLOCKED");
				} else {
					livingRoomWindows.setBlocked(false);
					console.msg("Living Room Window: UNBLOCKED");
				}
				if (MasterBedroom.isSelected()) {
					MasterBedroomWindows.setBlocked(true);
					console.msg("Master Bedroom Window: BLOCKED");
				} else {
					MasterBedroomWindows.setBlocked(false);
					console.msg("Master Bedroom Window: UNBLOCKED");
				}
				console.msg("**********Update completed**********");
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
					for (int i = 0; i < userNameArray.length; i++) {
						userNameArray[i] = user.getUserList().get(i).getName();
					}
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