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
	private SimulationButton simulationButton;
	private EditSimulation editSimulation;

	public SHSController(SHSGui frame) {
		// main ui
		this.frame = frame;

		// Create First User
		Users FirstUser = new Users("Admin");

		// control console
		this.console = new Console(frame.getTextAreaConsoleLog());
		console.msg("Welcome to the Smart Home Simulator");

		// simulation button
		this.simulationButton = new SimulationButton(frame.getTogglebuttonSimulator(), console);

		// edit simuatlion
		this.editSimulation = new EditSimulation(frame.getPressbuttonEditContext(), user, console);
		
		// event handler
		createEvents();

	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {

		/** Event that changes user logged in **/
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

		/** Add User functionality **/
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

		/** Delete User functionality **/
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