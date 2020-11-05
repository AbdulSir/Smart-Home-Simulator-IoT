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
				int state = itemEvent.getStateChange();

				if (state == ItemEvent.SELECTED) {
					setAwayMode(true);
					console.msg("Away Mode ON");
				}
				else if (state == ItemEvent.DESELECTED) {
					setAwayMode(false);
					console.msg("Away Mode OFF");
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
}