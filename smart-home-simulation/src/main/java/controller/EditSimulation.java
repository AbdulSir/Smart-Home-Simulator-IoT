package controller;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

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
				if (frame.getSHPcontroller().getAwayMode() == true && newLocation != "Outside") {
					console.msg ("Intruder Alert!");
				}
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
		this.context.getBlockButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windows windows = new Windows();
				String location = context.getComboBoxWindowLocation().getSelectedItem().toString();
				for (int i = 0; i < windows.getWindowList().size(); i++) {
					if(windows.getWindowList().get(i).getLocation().equals(location)) {
						if(!windows.getWindowList().get(i).isBlocked()) {
							windows.getWindowList().get(i).setBlocked(true);
							frame.repaint();
							console.msg("The window in the " + location + " has been blocked");
						} else 
							console.msg("The window in the " + location + " is already blocked");
						break;
					}
				}
			}
		});

		this.context.getUnblockButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windows windows = new Windows();
				String location = context.getComboBoxWindowLocation().getSelectedItem().toString();
				for (int i = 0; i < windows.getWindowList().size(); i++) {
					if(windows.getWindowList().get(i).getLocation().equals(location)) {
						if(windows.getWindowList().get(i).isBlocked()) {
							windows.getWindowList().get(i).setBlocked(false);
							frame.repaint();
							console.msg("The window in the " + location + " has been unblocked");
						} else 
							console.msg("The window in the " + location + " is already unblocked");
						break;
					}
				}
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