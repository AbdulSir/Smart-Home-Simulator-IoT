package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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
	private int counter = 0;

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
		UserEvents();
	}

	private void UserEvents() {

		/**
		 * Select Zone event
		 */
		final JComboBox zoneComboBox = this.frame.getZoneComboBox();
		zoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

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
			}
		});

		/**
		 * Select room to add to zone event
		 */
		JButton btnAddRoomToZone = this.frame.getBtnAddRoomToZone();
		JComboBox roomToZoneComboBox = this.frame.getRoomToZoneComboBox();
		btnAddRoomToZone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				Zone zone = new Zone((currentZoneSelected+1), currentPeriodSelected, formattedInitialPeriodTime,
						formattedfinalPeriodTime, desiredTempInt);
//				if (currentTime != null && finalPeriodTime != null) {
//				if ((formattedCurrentTime).compareTo(formattedfinalPeriodTime) > 0) {
//					
//				}
//			}

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
									
									for (int a = 0; a < rooms.size(); a++) {
										if (rooms.get(a).getCurrentRoomTemperature() < rooms.get(a)
												.getDesiredRoomTemperature()) {
											final Integer innerA = new Integer(a);
	
											clockTimer.scheduleAtFixedRate(new TimerTask() {
												public void run() {
													// Your code

													rooms.get(innerA).setCurrentRoomTemperature(
															rooms.get(innerA).getCurrentRoomTemperature() + 0.1);
													System.out.println("Current Temperature for "
																	+ rooms.get(innerA).getLocation() + " is "
																	+ rooms.get(innerA).getCurrentRoomTemperature());
													if (rooms.get(innerA).getCurrentRoomTemperature() > rooms
															.get(innerA).getDesiredRoomTemperature()) {
														System.out.println("Current Temperature for "
																+ rooms.get(innerA).getLocation() + " has reached Desired Temperature of "
																+ rooms.get(innerA).getCurrentRoomTemperature());
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
				int currentRoomSelected = comboBoxSetRoomTemp.getSelectedIndex();
				frame.getLabelCurrentTemp()
						.setText(String.valueOf(rooms.get(currentRoomSelected).getCurrentRoomTemperature()));

				if (rooms.get(currentRoomSelected).isTempOverridden()) {
					frame.getLabelCurrentTemp()
							.setText(String.valueOf(rooms.get(currentRoomSelected).getCurrentRoomTemperature())
									+ " \u00B0C " + " OVERRIDDEN");
				} else {
					frame.getLabelCurrentTemp().setText(
							String.valueOf(rooms.get(currentRoomSelected).getCurrentRoomTemperature()) + " \u00B0C ");
				}

			}
		});

		/**
		 * Set new room temperature
		 */
		JButton btnSetTemp = this.frame.getBtnSetTemp();
		btnSetTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int currentRoomSelected = comboBoxSetRoomTemp.getSelectedIndex();
				double newTemp = Double.parseDouble(frame.getNewTempValue().getText());
				rooms.get(currentRoomSelected).setDesiredRoomTemperature(newTemp);
				frame.getLabelCurrentTemp().setText(
						String.valueOf(rooms.get(currentRoomSelected).getDesiredRoomTemperature()) + "Overriden");
				rooms.get(currentRoomSelected).setDesiredRoomTemperature(newTemp);

				// set temperature of room overridden
				rooms.get(currentRoomSelected).setTempOverridden(true);

				String currentRoom = rooms.get(currentRoomSelected).getLocation();

				// console message
				Console.getConsole().msg(currentRoom + " new temperature is " + newTemp + " \u00B0C.");
			}
		});

		/**
		 * Set default summer and winter temperature
		 */
		JButton defaultOK = this.frame.getBtnDefaultSummer();
		JTextField defaultSummerTextField = this.frame.getTextFieldDefaultSummer();
		JTextField defaultWinterTextField = this.frame.getTextFieldDefaultWinter();
		defaultOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date currentTimeDate = time.getTime();
				String formattedCurrentTime = new SimpleDateFormat("MM").format(currentTimeDate);
				int currentMonth = Integer.parseInt(formattedCurrentTime);
				if (SHPController.getSHPController().getAwayMode() == true) {
					double defaultSummer = Double.parseDouble(defaultSummerTextField.getText());
					setDefaultSummerTemp(defaultSummer);
					double defaultWinter = Double.parseDouble(defaultWinterTextField.getText());
					setDefaultSummerTemp(defaultWinter);
					if (summerMonths != null && winterMonths != null) {
						if (summerMonths.contains(currentMonth)) {
							for (int i = 0; i < rooms.size(); i++)
								rooms.get(i).setDesiredRoomTemperature(defaultSummer);
						} else if (winterMonths.contains(currentMonth)) {
							for (int i = 0; i < rooms.size(); i++)
								rooms.get(i).setDesiredRoomTemperature(defaultWinter);
						} else {
							for (int i = 0; i < rooms.size(); i++) {
								rooms.get(i).setDesiredRoomTemperature(temperature.getInsideTemp());
							}
						}
					} else
						console.msg("The winter and summer months have not been set. Operation Failed.");
				} else
					console.msg("Away Mode not ON");
			}
		});

		/**
		 * This timer will allow us to constantly check if the outdoor temperature is
		 * lower than any of the temperatures in any of the rooms. We will also check if
		 * the inside temperature is equal to 0.
		 */
//		Timer tempTimer = new Timer();
//		tempTimer.schedule(new TimerTask() {
//			public void run() {
//				for (Room room : rooms) {
//					if (temperature.getOutsideTemp() < room.getCurrentRoomTemperature()
//							&& SHPController.getSHPController().getAwayMode() == false) {
//						for (int i = 0; i < window.size(); i++) {
//							if (window.get(i).getLocation().equals(room.getLocation())) {
//								if (!window.get(i).isBlocked()) {
//									window.get(i).setOpen(true);
//									paint();
//									console.msg("The window in the " + room.getLocation()
//											+ " has been opened due to difference in temperature");
//									break;
//								} else
//									console.msg("The outdoor temperature is lower than the temperature in the "
//											+ room.getLocation()
//											+ ". The window cannot be opened because it is blocked.");
//							}
//						}
//					}
//				}
//				if (Temperature.getTemperature().getInsideTemp() == 0 && counter % 60 == 0) {
//					console.msg("The temperature inside of the house is at 0\u00B0C. The pipes might burst.");
//				}
//				counter++;
//			}
//		}, 1000, 1000);

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

}
