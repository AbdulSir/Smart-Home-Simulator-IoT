package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JToggleButton;

import model.Doors;
import model.Users;
import model.Windows;
import view.ContextSimulation;
import view.SHSGui;


public class SHPController {
	private SHSGui frame;
	private Console console;
	private Boolean awayMode;
	private Users user;
	private ContextSimulation context;
	private int timeToAlert;
	private Windows windows;
	private Doors doors;
	

	public SHPController() {
	}

	public SHPController(SHSGui frame) {
		/** Main GUI **/
		this.frame = frame;
		awayMode = false;
		/** Control Console **/
		this.console = new Console(frame.getTextAreaConsoleLog());
		this.context = new ContextSimulation();
		this.user = new Users();
		this.windows = new Windows();
		this.doors = new Doors();
		userEvents();
	}

	private void userEvents() {
		/** awayModeBtn event **/
		JToggleButton AwayModeBtn = this.frame.getAwayModeToggleButton();
		AwayModeBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				/** No users at home to enable set away mode ON**/
				ArrayList<Users> userArray = user.getUserList();
				Boolean allUsersOutside = true;
				for (int i = 0; i < userArray.size() ; i++) {
					if(userArray.get(i).getLocation() != "Outside") 
						allUsersOutside = false;
				}						
				if(allUsersOutside == true) {
					int state = itemEvent.getStateChange();
	
					if (state == ItemEvent.SELECTED) {
						setAwayMode(true);
						console.msg("Away Mode ON");
						/** Close all windows and lock all doors **/
						for (int i = 0; i < windows.getWindowList().size(); i++) {
							windows.getWindowList().get(i).setOpen(false);
						}
						for (int i = 0; i < doors.getDoorList().size(); i++) {
							doors.getDoorList().get(i).setOpen(false);
						}			
						frame.repaint();
					}
					else if (state == ItemEvent.DESELECTED) {
						setAwayMode(false);
						console.msg("Away Mode OFF");
					}
				}else {
					int state = itemEvent.getStateChange();
					
					if (state == ItemEvent.SELECTED) {
						setAwayMode(false);
						console.msg("Away Mode can not be turned on while users are indoor.");
					}
					else if (state == ItemEvent.DESELECTED) {
						setAwayMode(false);
						console.msg("Away Mode can not be turned on while users are indoor.");
					}
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