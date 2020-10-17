package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import view.ContextSimulation;
import view.SHSGui;

public class SHSController {
	private SHSGui frame;
	private Console console;
	private Users user;
	private ContextSimulation context = new ContextSimulation();
	
	public SHSController(SHSGui frame) {
		// main ui
		this.frame = frame;
		createEvents();
		Users FirstUser = new Users("Admin"); // Create First User

		// control console
		this.console = new Console(frame.getTextAreaConsoleLog());
		console.msg("Welcome to the Smart Home Simulator");

	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {
		// Toggle Button State Change
		this.frame.getTogglebuttonSimulator().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int state = arg0.getStateChange();

				if (state == ItemEvent.SELECTED)
					console.msg("Simulator ON");
				else if (state == ItemEvent.DESELECTED)
					console.msg("Simulator OFF");

			}
		});
//************************************************Edit the context of a simulation************************************************//
		this.frame.getPressbuttonEditContext().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				context.NewScreen(context);
			}
		});
//************************************************Change the location of a user************************************************//
		this.context.getSetLocation().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = new Users();
				JComboBox comboBoxUsers = context.getComboBoxUsers();
				int index = comboBoxUsers.getSelectedIndex();
				String userToMove = comboBoxUsers.getSelectedItem().toString();
				String oldLocation = user.getUserList().get(index).getLocation();
				user.getUserList().get(index).setLocation(context.getComboBoxLocation().getSelectedItem().toString());
				String newLocation = user.getUserList().get(index).getLocation();
				if(oldLocation.equalsIgnoreCase(newLocation) && oldLocation.equalsIgnoreCase("Outside"))
				console.msg(userToMove + " is still outside of the house");
				else if(oldLocation.equalsIgnoreCase(newLocation))	
					console.msg(userToMove + " is still in the " + newLocation);
				else if(!newLocation.equalsIgnoreCase("Outside"))
					console.msg(userToMove + " has moved from the " + oldLocation + " to the " + newLocation);
				else
					console.msg(userToMove + " has moved from the " + oldLocation + " to outside of the house");
				}
			});
//************************************************Block/Unblock Windows************************************************//
		this.context.getBlockWindows().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton bathroom = context.getBathroomRadioButton(); Windows bathroomWindows = new Windows("Bathroom");
				JRadioButton bedroom = context.getBedroomRadioButton(); Windows bedroomWindows = new Windows("Bedroom");
				JRadioButton garage = context.getGarageRadioButton(); Windows garageWindows = new Windows("Garage");
				JRadioButton kitchen = context.getKitchenRadioButton(); Windows kitchenWindows = new Windows("Kitchen");
				JRadioButton livingRoom = context.getLivingRoomRadioButton(); Windows livingRoomWindows = new Windows("Living Room");
				JRadioButton MasterBedroom = context.getMasterBedroomRadioButton(); Windows MasterBedroomWindows = new Windows("Master Bedroom");

				console.msg("**********Updating status of the windows**********");
				if(bathroom.isSelected()) {bathroomWindows.setBlocked(true); console.msg("Bathroom Window: BLOCKED");}
				else {bathroomWindows.setBlocked(false); console.msg("Bathroom Window: UNBLOCKED");}
				if(bedroom.isSelected()) {bedroomWindows.setBlocked(true); console.msg("Bedroom Window: BLOCKED");} 
				else {bedroomWindows.setBlocked(false); console.msg("Bedroom Window: UNBLOCKED");}
				if(garage.isSelected()) {garageWindows.setBlocked(true); console.msg("Garage Window: BLOCKED");} 
				else {garageWindows.setBlocked(false); console.msg("Garage Window: UNBLOCKED");}
				if(kitchen.isSelected()) {kitchenWindows.setBlocked(true); console.msg("Kitchen Window: BLOCKED");} 
				else {kitchenWindows.setBlocked(false); console.msg("Kitchen Window: UNBLOCKED");}
				if(livingRoom.isSelected()) {livingRoomWindows.setBlocked(true); console.msg("Living Room Window: BLOCKED");} 
				else {livingRoomWindows.setBlocked(false); console.msg("Living Room Window: UNBLOCKED");}
				if(MasterBedroom.isSelected()) {MasterBedroomWindows.setBlocked(true); console.msg("Master Bedroom Window: BLOCKED");}
				else {MasterBedroomWindows.setBlocked(false); console.msg("Master Bedroom Window: UNBLOCKED");}
				console.msg("**********Update completed**********");
			}
		});
//************************************************Event that changes user logged in************************************************//			
		final JComboBox comboBoxRole = this.frame.getJComboRole();
		user = new Users();
		comboBoxRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.removeActiveUsers();
				String userToMakeActive = comboBoxRole.getSelectedItem().toString();
				ArrayList<Users> userList = user.getUserList();
				for (Users user : userList) {
					if (user.getName().equalsIgnoreCase(userToMakeActive)) {
						user.setActiveUser(true);
						console.msg(user.getName() + " is now logged in");
						break;
					} else {
						continue;
					}
				}

			}
		});
//************************************************Add User functionality ************************************************//			
		JButton addNewUserButton = this.frame.getnewUserButton();
		final JTextField enterNewUsername = this.frame.getNewUserName();
		addNewUserButton.addMouseListener(new MouseAdapter() {
			// new user button click event
			public void mouseClicked(MouseEvent e) {
				String NewUsername = enterNewUsername.getText();
				Users New = new Users(NewUsername);
				console.msg(NewUsername + " has been added");
			}
		});

//************************************************Delete User functionality ************************************************//			
		JButton deleteUserButton = this.frame.getDeleteUserButton();
		final JComboBox comboBoxDeleteUser = this.frame.getDeleteUserBox();
		deleteUserButton.addMouseListener(new MouseAdapter() {
			// Delete User function
			public void mouseClicked(MouseEvent e) {
				String userToDelete = comboBoxDeleteUser.getSelectedItem().toString();
				ArrayList<Users> userList = user.getUserList();
				for (Users user : userList) {
					if (user.getName().equalsIgnoreCase(userToDelete)) {
						userList.remove(user);
						console.msg(userToDelete + " Deleted");
						break;
					} else {
						continue;
					}
				}
			}
		});

		// Will update User pop up menu every time the user opens the menu
		PopupMenuListener userDeletedListener = new PopupMenuListener() {
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
					comboBoxDeleteUser.setModel(new DefaultComboBoxModel(userNameArray));
				}
			}
		};
		comboBoxDeleteUser.addPopupMenuListener(userDeletedListener);

	}
}