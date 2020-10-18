package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import view.ContextSimulation;

public class EditSimulation {

	private JButton editContext;
	private ContextSimulation context;
	private Users user;
	private Console console;

	/**
	 * Constructor
	 */
	public EditSimulation(JButton editContext, Users user, Console console) {
		this.context = new ContextSimulation();
		this.editContext = editContext;
		this.user = user;
		this.console = console;

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
				user = new Users();
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
			}
		});

		/** Block/Unblock Windows **/
		this.context.getBlockWindows().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton bathroom = context.getBathroomRadioButton();
				Windows bathroomWindows = new Windows("Bathroom");
				JRadioButton bedroom = context.getBedroomRadioButton();
				Windows bedroomWindows = new Windows("Bedroom");
				JRadioButton garage = context.getGarageRadioButton();
				Windows garageWindows = new Windows("Garage");
				JRadioButton kitchen = context.getKitchenRadioButton();
				Windows kitchenWindows = new Windows("Kitchen");
				JRadioButton livingRoom = context.getLivingRoomRadioButton();
				Windows livingRoomWindows = new Windows("Living Room");
				JRadioButton MasterBedroom = context.getMasterBedroomRadioButton();
				Windows MasterBedroomWindows = new Windows("Master Bedroom");

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
			}
		});

	}
}
