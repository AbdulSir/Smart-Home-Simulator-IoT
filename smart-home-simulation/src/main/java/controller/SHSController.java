package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.SHSGui;
import view.CreateUser;
import view.DeleteUser;

public class SHSController {
	private SHSGui frame;
	private Console console;

	public SHSController(SHSGui frame) {
		// main ui
		this.frame = frame;
		createEvents();
		Users FirstUser = new Users("Admin"); //Create First User

		// control console
		this.console = new Console(frame.getTextAreaConsoleLog());
		console.msg("Welcome to the Smart Home Simulator");

	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {
		//Open Add User button window when menu item is pressed
		this.frame.getUserMenuAddUser().addActionListener(new ActionListener() {
			CreateUser createUser = new CreateUser();
			public void actionPerformed(ActionEvent arg0) {
				if (createUser.isVisible()) {
					System.out.println("already open!");
				} else {
					createUser.setVisible(true);
				}
			}
		});
		// Open Delete Menu window when menu item is pressed 
		this.frame.getUserMenuDeleteUser().addActionListener(new ActionListener() {
			DeleteUser deleteUser = new DeleteUser();
			public void actionPerformed(ActionEvent arg0) {
				if (deleteUser.isVisible()) {
					System.out.println("already open!");
				} else {
					deleteUser.setVisible(true);
				}
			}
		});
		
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
	}
}