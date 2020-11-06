package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import model.Doors;
import model.Lights;
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
	private boolean isUserLoggedIn;
	private int timeToAlert;
	private Windows windows;
	private Doors doors;
	private Lights lights;
	private JSpinner startAwayLightTime;
	private JSpinner stopAwayLightTime;
	private String StrStartALTime;
	private String StrStopALTime;
	private long time;

	public SHPController() {
	}

	/**
	 * Parametrized Constructor
	 * 
	 * @param frame
	 */
	public SHPController(SHSGui frame) {
		/** Main GUI **/
		this.frame = frame;
		awayMode = false;
		isUserLoggedIn = true;
		user = new Users();
		/** Control Console **/
		this.console = new Console(frame.getTextAreaConsoleLog());
		this.context = new ContextSimulation();
		this.user = new Users();
		this.windows = new Windows();
		this.doors = new Doors();
		this.lights = new Lights();
		this.startAwayLightTime = frame.getAwayLightsStartTime();
		this.stopAwayLightTime = frame.getAwayLightsStopTime();
		userEvents();
	}

	private void userEvents() {

		/** awayModeBtn event **/
		JToggleButton AwayModeBtn = this.frame.getAwayModeToggleButton();
		AwayModeBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				/** No users at home to enable set away mode ON **/
				ArrayList<Users> userArray = user.getUserList();
				Boolean allUsersOutside = true;
				for (int i = 0; i < userArray.size(); i++) {
					if (userArray.get(i).getLocation() != "Outside")
						allUsersOutside = false;
				}
				if (allUsersOutside == true) {
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
					} else if (state == ItemEvent.DESELECTED) {
						setAwayMode(false);
						console.msg("Away Mode OFF");
					}
				} else {
					int state = itemEvent.getStateChange();

					if (state == ItemEvent.SELECTED) {
						setAwayMode(false);
						console.msg("Away Mode can not be turned on while users are indoor.");
					} else if (state == ItemEvent.DESELECTED) {
						setAwayMode(false);
						console.msg("Away Mode can not be turned on while users are indoor.");
					}
				}
			}
		});

		/** Time input until authorities will be alerted **/
		/*
		 * Users loggedUser = user.getLoggedUser(); int state =
		 * itemEvent.getStateChange(); if (hasPermissions(loggedUser)) { if (state ==
		 * ItemEvent.SELECTED) { setAwayMode(true); console.msg("Away Mode ON"); } else
		 * if (state == ItemEvent.DESELECTED) { setAwayMode(false);
		 * console.msg("Away Mode OFF"); } } else { if (state == ItemEvent.SELECTED &&
		 * isUserLoggedIn)
		 * console.msg("You do not have the permission to execute this command"); } }
		 * 
		 * });
		 */

		JTextField timer = this.frame.getTimeToAlertInput();
		timer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String timerStr = timer.getText();
				timeToAlert = Integer.parseInt(timerStr);
				setTimeToAlert(timeToAlert);
				console.msg("Time to alert authorities has been set to " + getTimeToAlert() + " seconds");
			}

		});

		/** Confirm button for Away mode lights **/
		JButton btnAwayLights = this.frame.getBtnAwayLights();
		btnAwayLights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

//				Date startALTime = (Date) startAwayLightTime.getValue();
//				StrStartALTime = new SimpleDateFormat("HH:mm").format(startALTime);
//				Date stopALTime = (Date) stopAwayLightTime.getValue();
//				StrStopALTime = new SimpleDateFormat("HH:mm").format(stopALTime);
//				
//				setStrStartALTime(StrStartALTime);
//				setStrStopALTime(StrStopALTime);
//				
//				LocalTime t1 = LocalTime.parse(StrStartALTime);
//				LocalTime t2 = LocalTime.parse(StrStopALTime);
//				Duration diff = Duration.between(t2, t1);
//				time = diff.toMinutes()*60000;	
//				
//				setTime(time);

				awayModeLights();
			}
		});
	}

	public void awayModeLights() {
		ArrayList<Lights> lightsList = lights.getLightsList();
//		String currentTime = frame.getTimeValue().getText();	
//		
//		if (currentTime == StrStartALTime) {
		if (frame.getChckbxBedRMLight().isSelected())
			lightsList.get(0).setLights(true);

		if (frame.getChckbxMasterBedRMLight().isSelected())
			lightsList.get(1).setLights(true);

		if (frame.getChckbxBathRMLight().isSelected())
			lightsList.get(2).setLights(true);

		if (frame.getChckbxKitchenLight().isSelected())
			lightsList.get(3).setLights(true);

		if (frame.getChckbxLivingRMLight().isSelected())
			lightsList.get(4).setLights(true);

		if (frame.getChckbxGarageLight().isSelected())
			lightsList.get(5).setLights(true);

		if (frame.getChckbxBackyardLight().isSelected())
			lightsList.get(6).setLights(true);

		if (frame.getChckbxEntranceLight().isSelected())
			lightsList.get(7).setLights(true);

		frame.repaint();

//	        if(frame.getTimeValue().getText() == StrStopALTime) {
//	        	for(int i =0; i < lightsList.size(); i++ ) {
//	        		lightsList.get(i).setLights(false);	
//	        	}
//	        }
//		}
	}

	/**
	 * Getter
	 */
	public Boolean getAwayMode() {
		return awayMode;
	}

	/**
	 * Setter
	 */
	public void setAwayMode(Boolean awayMode) {
		this.awayMode = awayMode;
	}

	/**
	 * Getter
	 */
	public int getTimeToAlert() {
		return timeToAlert;
	}

	/**
	 * Setter
	 */
	public void setTimeToAlert(int timeToAlert) {
		this.timeToAlert = timeToAlert;
	}

	public String getStrStartALTime() {
		return StrStartALTime;
	}

	public void setStrStartALTime(String strStartALTime) {
		StrStartALTime = strStartALTime;
	}

	public String getStrStopALTime() {
		return StrStopALTime;
	}

	public void setStrStopALTime(String strStopALTime) {
		StrStopALTime = strStopALTime;
	}

	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * Determines if the logged-in user has access to these commands
	 * 
	 * @param user
	 * @param location
	 * @param item
	 * @return
	 */
	public boolean hasPermissions(Users user) {
		if (user == null) {
			console.msg("The system does not have a logged-in user");
			isUserLoggedIn = false;
			return false;
		}
		switch (user.getPermission()) {
		case "PARENT":
			isUserLoggedIn = true;
			return true;
		case "CHILDREN":
			isUserLoggedIn = true;
			return true;
		case "GUEST":
			isUserLoggedIn = true;
			return false;
		case "STRANGER":
			isUserLoggedIn = true;
			return false;
		default:
			return false;
		}
	}
}