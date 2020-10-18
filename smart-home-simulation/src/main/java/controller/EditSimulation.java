package controller;

import java.awt.Color;
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
	Windows bedroomWindows = new Windows("BedRM");
	Windows MasterBedroomWindows = new Windows("Master BedRM");
	Windows bathroomWindows = new Windows("BathRM");
	Windows kitchenWindows = new Windows("Kitchen");
	Windows livingRoomWindows = new Windows("Living RM");
	Windows garageWindows = new Windows("Garage");
	
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
				context.getContentPane().remove(context.getPanelHouse());
				context.getPanelHouse().setIndex(index);
				context.getPanelHouse().repaint();
				context.getPanelHouse().setBounds(485, 10, 728, 662);
				context.getPanelHouse().setBackground(Color.WHITE);
				context.getContentPane().add(context.getPanelHouse());
				context.getPanelHouse().setLayout(null);	
			}
		});

		/** Block/Unblock Windows **/
		this.context.getBlockWindows().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton bathroom = context.getBathroomRadioButton();
				JRadioButton bedroom = context.getBedroomRadioButton();
				JRadioButton garage = context.getGarageRadioButton();
				JRadioButton kitchen = context.getKitchenRadioButton();
				JRadioButton livingRoom = context.getLivingRoomRadioButton();
				JRadioButton MasterBedroom = context.getMasterBedroomRadioButton();

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
				context.getContentPane().remove(context.getPanelHouse());
				context.getPanelHouse().repaint();
				context.getPanelHouse().setBounds(485, 10, 728, 662);
				context.getPanelHouse().setBackground(Color.WHITE);
				context.getContentPane().add(context.getPanelHouse());
				context.getPanelHouse().setLayout(null);
			}
		});

	}

	public JButton getEditContext() {
		return editContext;
	}

	public void setEditContext(JButton editContext) {
		this.editContext = editContext;
	}

	public ContextSimulation getContext() {
		return context;
	}

	public void setContext(ContextSimulation context) {
		this.context = context;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}
	
}
