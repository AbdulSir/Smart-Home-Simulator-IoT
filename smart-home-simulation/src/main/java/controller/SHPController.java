package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
	private boolean isUserLoggedIn;
	private int timeToAlert;
	private Windows windows;
	private Doors doors;
	private Lights lights;
	private JSpinner startAwayLightTime;
	private JSpinner stopAwayLightTime;
	private Time time;
	private String StrStartALTime;
	private String StrStopALTime;
	private SimulationButton simulationButton;
	private PrintWriter pw;
	private static SHPController shpController;
	private SHPController() {
	}

	/**
	 * Parametrized Constructor
	 * 
	 * @param frame
	 */
	private SHPController(SHSGui frame) {
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
		this.time = new Time();
		
		try {
			pw = new PrintWriter(new FileOutputStream("SHPControllerLog.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// User Event Handler
		userEvents();
	}

	private void userEvents() {

		/** awayModeBtn event **/
		JToggleButton AwayModeBtn = this.frame.getAwayModeToggleButton();
		AwayModeBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				Users loggedUser = user.getLoggedUser();
				int state = itemEvent.getStateChange();
				
				/** No users at home to enable set away mode **/
				ArrayList<Users> userArray = user.getUserList();				
				Boolean allUsersOutside = true;
				if (hasPermissions(loggedUser)) {
					for (int i = 0; i < userArray.size() ; i++) {
						if(!(userArray.get(i).getLocation()).equals("Outside")) {						
							allUsersOutside = false;
							break;
						}
					}	
					if(allUsersOutside == true) {			
							if (state == ItemEvent.SELECTED) {
								setAwayMode(true);
								console.msg("Away Mode ON");
								appendToLog("Away Mode ON");
								/** Close all windows and lock all doors **/
								for (int i = 0; i < windows.getWindowList().size(); i++) {
									windows.getWindowList().get(i).setOpen(false);
								}
								for (int i = 0; i < doors.getDoorList().size(); i++) {
									doors.getDoorList().get(i).setOpen(false);
									doors.getDoorList().get(i).setLocked(true);
								}			
								paint();
							}
							else if (state == ItemEvent.DESELECTED) {
								setAwayMode(false);
								console.msg("Away Mode OFF");
								appendToLog("Away Mode OFF");
							}
					}else {
						setAwayMode(false);
						if (state == ItemEvent.SELECTED) {
							console.msg("Away Mode cannot be activated while users are indoor.");
							appendToLog("Away Mode cannot be activated while users are indoor.");
						}
					}
				}else {
					if (state == ItemEvent.SELECTED && isUserLoggedIn) {
						console.msg("You do not have the permission to execute this command");
						appendToLog("You do not have the permission to execute this command");
					}
				}
			}					
		});
		
		/** Time input until authorities will be alerted **/
		JTextField timer = this.frame.getTimeToAlertInput();
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(awayMode == true) {
					String timerStr = timer.getText();
					timeToAlert = Integer.parseInt(timerStr);
					setTimeToAlert(timeToAlert);
					console.msg("Time to alert authorities has been set to " + getTimeToAlert() + " seconds");
					appendToLog("Time to alert authorities has been set to " + getTimeToAlert() + " seconds");
				} else { 
					console.msg("Away mode is currently OFF");
					appendToLog("Away mode is currently OFF");
				}
			}

		});

		/** Confirm button for Away mode lights **/
		JButton btnAwayLights = this.frame.getBtnAwayLights();
		btnAwayLights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(awayMode == true) {
					Date startALTime = (Date) startAwayLightTime.getValue();
					String formattedStartALTime = new SimpleDateFormat("HH:mm").format(startALTime);
					Date stopALTime = (Date) stopAwayLightTime.getValue();
					String formattedStopALTime = new SimpleDateFormat("HH:mm").format(stopALTime);
	
					ArrayList<Lights> lightsList = lights.getLightsList();
					
					for(int i = 0; i < lightsList.size(); i++) {
						lightsList.get(i).setLights(false);
					}
					console.msg("The times for the Away Mode lights' setting have been set");
					appendToLog("The times for the Away Mode lights' setting has been set");
					
					Timer clockTimer = new Timer();
					clockTimer.schedule(new TimerTask() {
						public void run() {
							Date currentTime = time.getTime();
							String formattedCurrentTime = new SimpleDateFormat("HH:mm").format(currentTime);
								if (currentTime != null && startALTime != null) {
									if ((formattedCurrentTime).compareTo(formattedStartALTime) > 0 && (formattedCurrentTime).compareTo(formattedStopALTime) < 0) { 
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
									        
								        paint();							     
									}
								}
	
								if (currentTime != null && stopALTime != null) {
									if ((formattedCurrentTime).compareTo(formattedStopALTime) > 0) {
										
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
										    				
									        clockTimer.cancel();
									        paint();
									}
	
								}
						}
					}, 50,50);		
				} else { 
					console.msg("Away mode is currently OFF");
					appendToLog("Away mode is currently OFF");
				}
				
			}
		});
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

	/**
	 * Getter
	 */
	public SimulationButton getSimulationButton() {
		return simulationButton;
	}

	/**
	 * Setter
	 */
	public void setSimulationButton(SimulationButton simulationButton) {
		this.simulationButton = simulationButton;
	}

	/**
	 * Getter
	 */
	public PrintWriter getPrintWriter() {
		return pw;
	}

	/**
	 * Setter
	 */
	public void setPrintWriter(PrintWriter pw) {
		this.pw = pw;
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
			appendToLog("The system does not have a logged-in user");
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

	/**
	 * Repaints frame if the simulator is on
	 */
	private void paint() {
		if(simulationButton.isSimulatorState())
			frame.repaint();
	}
	
	/**
	 * Append all of the console messages to the corresponding log file
	 */
	private void appendToLog(String text) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		pw.write("[" + formatter.format(date) + "] " + text + "\n");
	}

	public static SHPController getShpController() {
		if (shpController != null)
			return shpController;
		else {
			SHPController.shpController = new SHPController(SHSGui.getShs());
			return shpController;
		}
	}
}