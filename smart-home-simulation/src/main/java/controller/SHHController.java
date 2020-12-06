package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import model.ReadingJsonFile;
import model.Room;
import model.Temperature;
import model.Time;
import model.Users;
import model.Windows;
import model.Zone;
import view.SHSGui;

public class SHHController {
	private SHSGui frame;
	private Console console;
	private Users user;
	private Temperature temperature;
	private ArrayList<Integer> winterMonths;
	private ArrayList<Integer> summerMonths;
	private static SHHController shhController;
	private ArrayList<Room> rooms;
	private Time time;
	private double desiredTemp;
	private Zone zone;
	private SimulationButton simulationButton;
	private ArrayList<Windows> window;
	private double defaultSummerTemp;
	private double defaultWinterTemp;
	private boolean isUserLoggedIn;
	private int counter = 0;
	private double upperThreshold;
	private double lowerThreshold;

	public SHHController() {
	}

	public SHHController(SHSGui frame) {
		this.frame = frame;
		this.console = Console.getConsole();
		this.user = Users.getUser();
		rooms = Room.getRooms();
		this.time = time.getWatch();
		this.zone = new Zone();
		this.temperature = Temperature.getTemperature();
		this.window = Windows.getWindowList();
		userEvents();
	}

	private void userEvents() {

		/**
		 * Select Zone event
		 */
		final JComboBox zoneComboBox = this.frame.getZoneComboBox();
		zoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users loggedUser = user.getLoggedUser();
				if (hasPermissions(loggedUser, "N/A")) {
					String currentZoneSelected = zoneComboBox.getSelectedItem().toString();

					/**
					 * Display rooms in a zone in SHH
					 */
					switch (currentZoneSelected) {
					case "ZONE 1":
						frame.getDisplayZoneLabel().setText(getRoomZone(1));
						break;
					case "ZONE 2":
						frame.getDisplayZoneLabel().setText(getRoomZone(2));
						break;
					case "ZONE 3":
						frame.getDisplayZoneLabel().setText(getRoomZone(3));
						break;
					default:
						frame.getDisplayZoneLabel().setText("");
						break;
					}
				} else if (isUserLoggedIn) {
					console.msg(
							"You do not have the permission to execute this command. Reason: Permission status of user");
				}
			}
		});

		/**
		 * Select room to add to zone event
		 */
		JButton btnAddRoomToZone = this.frame.getBtnAddRoomToZone();
		JComboBox roomToZoneComboBox = this.frame.getRoomToZoneComboBox();
		btnAddRoomToZone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users loggedUser = user.getLoggedUser();
				if (hasPermissions(loggedUser, "N/A")) {
					String currentZoneSelected = zoneComboBox.getSelectedItem().toString();
					int currentRoomSelected = roomToZoneComboBox.getSelectedIndex();

					switch (currentZoneSelected) {
					case "ZONE 1":
						rooms.get(currentRoomSelected).setZone(1);
						break;
					case "ZONE 2":
						rooms.get(currentRoomSelected).setZone(2);
						break;
					case "ZONE 3":
						rooms.get(currentRoomSelected).setZone(3);
						break;
					}
				} else if (isUserLoggedIn) {
					console.msg(
							"You do not have the permission to execute this command. Reason: Permission status of user");
				}
			}
		});

		/**
		 * Adding desired temperature for a period of time
		 */
		JButton btnAcceptPeriod = this.frame.getBtnAcceptPeriod();
		JComboBox periodComboBox = this.frame.getPeriodComboBox();
		JSpinner initalPeriod = this.frame.getInitialPeriodJSpinner();
		JSpinner finalPeriod = this.frame.getFinalPeriodJSpinner();

		JTextField desiredTempTextField = this.frame.getDesiredTempTextField();

//		desiredTempTextField.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				String desiredTempStr = desiredTempTextField.getText();
//				setDesiredTemp(Integer.parseInt(desiredTempStr));
//			}
//		});

		btnAcceptPeriod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users loggedUser = user.getLoggedUser();
				if (hasPermissions(loggedUser, "N/A")) {
					int currentZoneSelected = zoneComboBox.getSelectedIndex();
					String currentPeriodSelected = periodComboBox.getSelectedItem().toString();

					Date initalPeriodTime = (Date) initalPeriod.getValue();
					String formattedInitialPeriodTime = new SimpleDateFormat("HH:mm:ss").format(initalPeriodTime);

					Date finalPeriodTime = (Date) finalPeriod.getValue();
					String formattedfinalPeriodTime = new SimpleDateFormat("HH:mm:ss").format(finalPeriodTime);

					String desiredTempStr = desiredTempTextField.getText();
					int desiredTempInt = (Integer.parseInt(desiredTempStr));

//				for (int a = 0; a < rooms.size(); a++) {
//					System.out.println("Room "+rooms.get(a).getLocation()+" "+rooms.get(a).getZone()+" CurrentZone Selected "+currentZoneSelected);
//					if (rooms.get(a).getZone() == (currentZoneSelected+1)) {
//						rooms.get(a).setDesiredRoomTemperature(desiredTempInt);
//						System.out.println(rooms.get(a).getDesiredRoomTemperature());
//					}
//				}
					Zone zone = new Zone((currentZoneSelected + 1), currentPeriodSelected, formattedInitialPeriodTime,
							formattedfinalPeriodTime, desiredTempInt);
//				if (currentTime != null && finalPeriodTime != null) {
//				if ((formattedCurrentTime).compareTo(formattedfinalPeriodTime) > 0) {
//					
//				}
//			}
				} else if (isUserLoggedIn) {
					console.msg(
							"You do not have the permission to execute this command. Reason: Permission status of user");
				}
			}

		});
		JButton shhApplyBtn = this.frame.getShhApplyBtn();
		shhApplyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Apply all zone temperatures to each room
				for (Zone zone : zone.getZoneList()) {
					System.out.println("we are in " + zone.getCurrentZone());
					for (int i = 0; i < rooms.size(); i++) {
						System.out.println(rooms.get(i).getLocation() + " is in Zone " + rooms.get(i).getZone());
						if ((zone.getCurrentZone() == rooms.get(i).getZone())) {
							if (!rooms.get(i).isTempOverridden()) {
								rooms.get(i).setDesiredRoomTemperature(zone.getDesiredTemperature());
							}
						}
						System.out.println("DESIRED TEMPERATURE FOR ROOM " + rooms.get(i).getLocation() + " IS "
								+ rooms.get(i).getDesiredRoomTemperature());
					}
				}

				Timer clockTimer = new Timer();
				clockTimer.schedule(new TimerTask() {
					public void run() {
						for (Zone zone : zone.getZoneList()) {
							Date currentTime = time.getTime();
							String formattedCurrentTime = new SimpleDateFormat("HH:mm:ss").format(currentTime);

							if (currentTime != null && zone.getInitialPeriod() != null) {
								if ((formattedCurrentTime).compareTo(zone.getInitialPeriod()) > 0
										&& (formattedCurrentTime).compareTo(zone.getFinalPeriod()) < 0) {
									// all rooms of selected zone will have desired temperature for this period of
									// time

									// This function here increments the temperature to the desired value
									// Right now it only work if you need to increase the temperature, not if you
									// want to decrement it

									for (int a = 0; a < rooms.size(); a++) {
										if (rooms.get(a).getCurrentRoomTemperature() < rooms.get(a)
												.getDesiredRoomTemperature()) {
											final Integer innerA = new Integer(a);

											clockTimer.scheduleAtFixedRate(new TimerTask() {
												public void run() {
													// Your code

													DecimalFormat form = new DecimalFormat("#.#");
													rooms.get(innerA).setCurrentRoomTemperature(
															rooms.get(innerA).getCurrentRoomTemperature() + 0.1);
													console.msg("Current Temperature for "

															+ rooms.get(innerA).getLocation() + " is "
															+ Double.valueOf(form.format(
																	rooms.get(innerA).getCurrentRoomTemperature())));
													if (Double.valueOf(form.format(
															rooms.get(innerA).getCurrentRoomTemperature())) == rooms
																	.get(innerA).getDesiredRoomTemperature()) {
														console.msg("Current Temperature for "
																+ rooms.get(innerA).getLocation()
																+ " has reached Desired Temperature of "
																+ Double.valueOf(form.format(rooms.get(innerA)
																		.getCurrentRoomTemperature())));

														clockTimer.cancel();
														clockTimer.purge();
													}
												}
											}, 0, 1000);

										} else if (rooms.get(a).getCurrentRoomTemperature() > rooms.get(a)
												.getDesiredRoomTemperature()) {

											final Integer innerA = new Integer(a);

											clockTimer.scheduleAtFixedRate(new TimerTask() {
												public void run() {
													// Your code

													DecimalFormat form = new DecimalFormat("#.#");
													rooms.get(innerA).setCurrentRoomTemperature(
															rooms.get(innerA).getCurrentRoomTemperature() - 0.1);
													console.msg("Current Temperature for "
															+ rooms.get(innerA).getLocation() + " is "
															+ Double.valueOf(form.format(
																	rooms.get(innerA).getCurrentRoomTemperature())));
													if (Double.valueOf(form.format(
															rooms.get(innerA).getCurrentRoomTemperature())) == rooms
																	.get(innerA).getDesiredRoomTemperature()) {
														console.msg("Current Temperature for "
																+ rooms.get(innerA).getLocation()
																+ " has reached Desired Temperature of "
																+ Double.valueOf(form.format(rooms.get(innerA)
																		.getCurrentRoomTemperature())));

														clockTimer.cancel();
														clockTimer.purge();
													}
												}
											}, 0, 1000);

										}
									}
								}
							}
						}

					}
				}, 1000, 1000);
			}
		});

		/**
		 * Display room temperature
		 */
		JComboBox comboBoxSetRoomTemp = this.frame.getComboBoxSetRoomTemp();
		comboBoxSetRoomTemp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Users loggedUser = user.getLoggedUser();
				int currentRoomSelected = comboBoxSetRoomTemp.getSelectedIndex();
				String location = rooms.get(currentRoomSelected).getLocation();
				if (hasPermissions(loggedUser, location)) {
					frame.getLabelCurrentTemp()
							.setText(String.valueOf(rooms.get(currentRoomSelected).getDesiredRoomTemperature()));

					if (rooms.get(currentRoomSelected).isTempOverridden()) {
						frame.getLabelCurrentTemp()
								.setText(String.valueOf(rooms.get(currentRoomSelected).getDesiredRoomTemperature())
										+ " \u00B0C " + " (Overriden)");
					} else {
						frame.getLabelCurrentTemp()
								.setText(String.valueOf(rooms.get(currentRoomSelected).getDesiredRoomTemperature())
										+ " \u00B0C ");
					}
				} else if (isUserLoggedIn) {
					if (!loggedUser.getLocation().equals(location) && (!loggedUser.getPermission().equals("STRANGER")
							&& !loggedUser.getPermission().equals("CHILDREN"))) {
						console.msg("You do not have the permission to execute this command. Reason: Location");
					} else {
						console.msg(
								"You do not have the permission to execute this command. Reason: Permission status of user");
					}
				}

			}
		});

		/**
		 * Set new room temperature
		 */
		JButton btnSetTemp = this.frame.getBtnSetTemp();
		btnSetTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users loggedUser = user.getLoggedUser();
				int currentRoomSelected = comboBoxSetRoomTemp.getSelectedIndex();
				String location = rooms.get(currentRoomSelected).getLocation();
				if (hasPermissions(loggedUser, location)) {
					double newTemp = Double.parseDouble(frame.getNewTempValue().getText());
					rooms.get(currentRoomSelected).setDesiredRoomTemperature(newTemp);
					frame.getLabelCurrentTemp()
							.setText(String.valueOf(rooms.get(currentRoomSelected).getDesiredRoomTemperature())
									+ "\u00B0C  (Overriden)");
					rooms.get(currentRoomSelected).setDesiredRoomTemperature(newTemp);

					// set temperature of room overridden
					rooms.get(currentRoomSelected).setTempOverridden(true);

					String currentRoom = rooms.get(currentRoomSelected).getLocation();

					// console message
					Console.getConsole().msg(currentRoom + " new temperature is " + newTemp + " \u00B0C.");
				} else if (isUserLoggedIn) {
					if (!loggedUser.getLocation().equals(location) && (!loggedUser.getPermission().equals("STRANGER")
							&& !loggedUser.getPermission().equals("CHILDREN"))) {
						console.msg("You do not have the permission to execute this command. Reason: Location");
					} else {
						console.msg(
								"You do not have the permission to execute this command. Reason: Permission status of user");
					}
				}
			}
		});

		/**
		 * Set default summer and winter temperature
		 */
		JButton defaultOK = this.frame.getBtnDefaultSeasonTemp();
		JTextField defaultSummerTextField = this.frame.getTextFieldDefaultSummer();
		JTextField defaultWinterTextField = this.frame.getTextFieldDefaultWinter();
		defaultOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Users loggedUser = user.getLoggedUser();
				if (hasPermissions(loggedUser, "N/A")) {
					if (defaultSummerTextField.getText().toString().equals("")
							|| defaultWinterTextField.getText().equals("")) {
						console.msg(
								"One or both of the text fields, for the default seasonal Away Mode temperatures,\nhave not been filled. Operation Failed.");
					} else {
						double defaultSummer = Double.parseDouble(defaultSummerTextField.getText());
						setDefaultSummerTemp(defaultSummer);
						double defaultWinter = Double.parseDouble(defaultWinterTextField.getText());
						setDefaultWinterTemp(defaultWinter);
						console.msg("The default temperatures for Summer & Winter (Away Mode) have been set");
					}
				} else if (isUserLoggedIn) {
					console.msg(
							"You do not have the permission to execute this command. Reason: Permission status of user");
				}
			}
		});

		/** Set upper and lower threshold **/
		JButton thresholdOK = this.frame.getBtnThresholdOK();
		JTextField UpperThresholdTextField = this.frame.getTextFieldUpperThreshold();
		JTextField LowerThresholdTextField = this.frame.getTextFieldLowerThreshold();
		thresholdOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double upperThresh = Double.parseDouble(UpperThresholdTextField.getText());
				setUpperThreshold(upperThresh);
				double lowerThresh = Double.parseDouble(LowerThresholdTextField.getText());
				setLowerThreshold(lowerThresh);
				console.msg("The thresholds have been set.g");
			}
		});

		/**
		 * This timer will allow us to constantly check if the outdoor temperature is
		 * lower than any of the temperatures in any of the rooms. We will also check if
		 * the inside temperature is equal to 0.
		 */
		Timer tempTimer = new Timer();
		tempTimer.schedule(new TimerTask() {
			public void run() {
				for (Room room : rooms) {
					if (temperature.getOutsideTemp() < room.getDesiredRoomTemperature()
							&& SHPController.getSHPController().getAwayMode() == false) {
						for (int i = 0; i < window.size(); i++) {
							if (window.get(i).getLocation().equals(room.getLocation())) {
								if (!window.get(i).isBlocked()  && counter % 60 == 0) {
									window.get(i).setOpen(true);
									paint();
									console.msg("The window in the " + room.getLocation()
											+ " has been opened due to difference in temperature");
									break;
								} else if (counter % 60 == 0)
									console.msg("The outdoor temperature is lower than the temperature in the "
											+ room.getLocation()
											+ ". The window cannot be opened because it is blocked.");
							}
						}
					}
					if (!UpperThresholdTextField.getText().equals("") && !LowerThresholdTextField.getText().equals("")) {
						if (room.getCurrentRoomTemperature() >= getUpperThreshold() && counter % 60 == 0) {
							console.msg("WARNING! The temperature in the " + room.getLocation()
									+ " has reached the upper threshold.");
						} else if (room.getCurrentRoomTemperature() <= getLowerThreshold() && counter % 60 == 0) {
							console.msg("WARNING! The temperature in the " + room.getLocation()
									+ " has reached the lower threshold.");
						}
					}
				}
				counter++;
			}
		}, 1000, 1000);
	}

	/**
	 * Get the zone of a room
	 * 
	 * @param zoneNum
	 * @return
	 */
	private String getRoomZone(int zoneNum) {
		String roomsList = "";
		for (int i = 0; i < rooms.size(); i++) {
			if (zoneNum == rooms.get(i).getZone()) {
				roomsList += rooms.get(i).getLocation() + ", ";
			}
		}
		if (!roomsList.equals(""))
			roomsList = roomsList.substring(0, roomsList.length() - 2);
		return roomsList;
	}

	private int setDesiredTemp(String temp) {

		return 0;
	}

	/**
	 * Getter
	 */
	public ArrayList<Integer> getWinterSeason() {
		return winterMonths;
	}

	/**
	 * Setter
	 */
	public void setWinterSeason(ArrayList<Integer> winterMonths) {
		this.winterMonths = winterMonths;
	}

	/**
	 * Getter
	 */
	public ArrayList<Integer> getSummerSeason() {
		return summerMonths;
	}

	/**
	 * Setter
	 */
	public void setSummerSeason(ArrayList<Integer> summer) {
		this.summerMonths = summer;
	}

	/**
	 * Setter
	 */
	public void setDesiredTemp(double desiredTemp) {
		this.desiredTemp = desiredTemp;
	}

	/**
	 * Getter
	 */
	public double getDesiredTemp() {
		return desiredTemp;
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

	public static SHHController getSHHController() {
		if (shhController != null)
			return shhController;
		else {
			SHHController.shhController = new SHHController(SHSGui.getSHS());
			return shhController;
		}

	}

	/**
	 * Repaints frame if the simulator is on
	 */
	private void paint() {
		if (simulationButton.isSimulatorState())
			frame.repaint();
	}

	/**
	 * Getter
	 */
	public double getDefaultSummerTemp() {
		return defaultSummerTemp;
	}

	/**
	 * Setter
	 */
	public void setDefaultSummerTemp(double defaultSummerTemp) {
		this.defaultSummerTemp = defaultSummerTemp;
	}

	/**
	 * Getter
	 */
	public double getDefaultWinterTemp() {
		return defaultWinterTemp;
	}

	/**
	 * Setter
	 */
	public void setDefaultWinterTemp(double defaultWinterTemp) {
		this.defaultWinterTemp = defaultWinterTemp;
	}

	public boolean hasPermissions(Users user, String location) {
		if (user == null) {
			if (counter % 2 != 0) {
				console.msg("The system does not have a logged-in user");
			}
			isUserLoggedIn = false;
			return false;
		}
		switch (user.getPermission()) {
		case "PARENT":
			isUserLoggedIn = true;
			return true;
		case "CHILDREN":
			isUserLoggedIn = true;
			return false;
		case "GUEST":
			isUserLoggedIn = true;
			if (user.getLocation().equals(location))
				return true;
			else
				return false;
		case "STRANGER":
			isUserLoggedIn = true;
			return false;
		default:
			return false;
		}
	}

	/**
	 * Getter
	 */
	public double getUpperThreshold() {
		return upperThreshold;
	}

	/**
	 * Setter
	 */
	public void setUpperThreshold(double upperThreshold) {
		this.upperThreshold = upperThreshold;
	}

	/**
	 * Getter
	 */
	public double getLowerThreshold() {
		return lowerThreshold;
	}

	/**
	 * Setter
	 */
	public void setLowerThreshold(double lowerThreshold) {
		this.lowerThreshold = lowerThreshold;
	}

}
