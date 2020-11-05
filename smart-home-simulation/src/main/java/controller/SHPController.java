package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import model.Users;
import view.ContextSimulation;
import view.SHSGui;

public class SHPController {
	private SHSGui frame;
	private Console console;
	private Boolean awayMode;
	private Users user;
	private ContextSimulation context;
	private int timeToAlert;

	public SHPController() {
	}

	public SHPController(SHSGui frame) {
		/** Main GUI **/
		this.frame = frame;
		awayMode = false;
		user = new Users();
		/** Control Console **/
		this.console = new Console(frame.getTextAreaConsoleLog());
		this.context = new ContextSimulation();
		userEvents();
	}

	private void userEvents() {

		/** awayModeBtn event **/
		JToggleButton AwayModeBtn = this.frame.getAwayModeToggleButton();
		AwayModeBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				Users loggedUser = user.getLoggedUser();
				int state = itemEvent.getStateChange();
				if (hasPermissions(loggedUser)) {
					if (state == ItemEvent.SELECTED) {
						setAwayMode(true);
						console.msg("Away Mode ON");
					} else if (state == ItemEvent.DESELECTED) {
						setAwayMode(false);
						console.msg("Away Mode OFF");
					}
				} else {
					if (state == ItemEvent.SELECTED)
						console.msg("You do not have the permission to execute this command");
				}
			}

		});

		JTextField timer = this.frame.getTimeToAlertInput();
		timer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String timerStr = timer.getText();
				timeToAlert = Integer.parseInt(timerStr);
				setTimeToAlert(timeToAlert);
				console.msg("Time to alert authorities has been set to " + getTimeToAlert() + " seconds");
			}

		});
	}

	public Boolean getAwayMode() {
		return awayMode;
	}

	public void setAwayMode(Boolean awayMode) {
		this.awayMode = awayMode;
	}

	public int getTimeToAlert() {
		return timeToAlert;
	}

	public void setTimeToAlert(int timeToAlert) {
		this.timeToAlert = timeToAlert;
	}

	public boolean hasPermissions(Users user) {
		if (user == null)
			return false;
		switch (user.getPermission()) {
		case "PARENT":
			return true;
		case "CHILDREN":
			return true;
		case "GUEST":
			return false;
		case "STRANGER":
			return false;
		default:
			return false;
		}
	}
}