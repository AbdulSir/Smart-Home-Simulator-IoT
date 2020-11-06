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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


import model.Doors;
import model.Lights;
import model.Time;
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
	private Lights lights;
	private JSpinner startAwayLightTime;
	private JSpinner stopAwayLightTime;
	private Time time;

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
		this.lights = new Lights();
		this.startAwayLightTime = frame.getAwayLightsStartTime();
		this.stopAwayLightTime = frame.getAwayLightsStopTime();
		this.time = new Time();
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
		
		/** Time input until authorities will be alerted **/
		JTextField timer = this.frame.getTimeToAlertInput();
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String timerStr = timer.getText();
				timeToAlert = Integer.parseInt(timerStr);
				setTimeToAlert(timeToAlert);
				console.msg("Time to alert authorities has been set to " + getTimeToAlert() + " seconds");
			}
		});
		
		/** Confirm button for Away mode lights**/
		JButton btnAwayLights = this.frame.getBtnAwayLights();
		btnAwayLights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Date startALTime = (Date) startAwayLightTime.getValue();				
				Date stopALTime = (Date) stopAwayLightTime.getValue();
				Date currentTime = time.getTime();
				ArrayList<Lights> lightsList = lights.getLightsList();
				
				Timer clockTimer = new Timer();
				clockTimer.schedule(new TimerTask() {
					public void run() {

							if (//currentTime != null && 
									startALTime != null) {
								if ((currentTime).compareTo(startALTime) == 0) 

							    if(frame.getChckbxBedRMLight().isSelected())
							       	lightsList.get(0).setLights(true);	
							        
						        if(frame.getChckbxMasterBedRMLight().isSelected())
							       	lightsList.get(1).setLights(true);	
						        
						        if(frame.getChckbxBathRMLight().isSelected())
						        	lightsList.get(2).setLights(true);	
							        
						        if(frame.getChckbxKitchenLight().isSelected())
						        	lightsList.get(3).setLights(true);	
							        
						        if(frame.getChckbxLivingRMLight().isSelected())
						        	lightsList.get(4).setLights(true);	
							        
						        if(frame.getChckbxGarageLight().isSelected())
						        	lightsList.get(5).setLights(true);	
							        
						       if(frame.getChckbxBackyardLight().isSelected())
						        	lightsList.get(6).setLights(true);	
							        
						        if(frame.getChckbxEntranceLight().isSelected())
						        	lightsList.get(7).setLights(true);	
							        
						        frame.repaint();
							}
					}
				}, 50,50);
				
				clockTimer.schedule(new TimerTask() {
					public void run() {

							if (//currentTime != null && 
									stopALTime != null) {
								if ((currentTime).compareTo(stopALTime) == 0) 
									
								    if(frame.getChckbxBedRMLight().isSelected())
								       	lightsList.get(0).setLights(false);	
								        
							        if(frame.getChckbxMasterBedRMLight().isSelected())
								       	lightsList.get(1).setLights(false);	
							        
							        if(frame.getChckbxBathRMLight().isSelected())
							        	lightsList.get(2).setLights(false);	
								        
							        if(frame.getChckbxKitchenLight().isSelected())
							        	lightsList.get(3).setLights(false);	
								        
							        if(frame.getChckbxLivingRMLight().isSelected())
							        	lightsList.get(4).setLights(false);	
								        
							        if(frame.getChckbxGarageLight().isSelected())
							        	lightsList.get(5).setLights(false);	
								        
							       if(frame.getChckbxBackyardLight().isSelected())
							        	lightsList.get(6).setLights(false);	
								        
							        if(frame.getChckbxEntranceLight().isSelected())
							        	lightsList.get(7).setLights(false);	
								        
							        frame.repaint();
							}
					}
				}, 50,50);
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