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
import model.RoomCounter;
import model.Temperature;
import model.Time;
import model.Users;
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
	private ArrayList<RoomCounter> rooms;
	private ReadingJsonFile rjFile;
	private Time time;
	private double desiredTemp;
	private Zone zone;
	private double defaultSummerTemp;
	private double defaultWinterTemp;

	public SHHController() {
	}

	public SHHController(SHSGui frame) {
		this.frame = frame;
		this.console = Console.getConsole();
		this.user = Users.getUser();
		rooms = RoomCounter.getRooms();
		this.time = time.getWatch();
		this.zone = new Zone();
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

				Zone zone = new Zone(currentZoneSelected, currentPeriodSelected, formattedInitialPeriodTime,
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
				zone.printZoneDetails();
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
									for (int i = 0; i < rooms.size(); i++) {
										if (zone.getCurrentZone() + 1 == rooms.get(i).getZone()) {
											rooms.get(i).setTemperature(zone.getDesiredTemperature());
											System.out.println(rooms.get(i).getLocation() + "    "
													+ rooms.get(i).getTemperature());
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

				frame.getLabelCurrentTemp().setText(String.valueOf(rooms.get(currentRoomSelected).getTemperature()));
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
				
				rooms.get(currentRoomSelected).setTemperature(newTemp);
				
				frame.getLabelCurrentTemp().setText(String.valueOf(rooms.get(currentRoomSelected).getTemperature()) + "Overriden");
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
				String formattedCurrentTime = new SimpleDateFormat("mm").format(currentTimeDate);
				int currentMonth = Integer.parseInt(formattedCurrentTime);
				if(SHPController.getSHPController().getAwayMode() == true) {
					double defaultSummer = Double.parseDouble(defaultSummerTextField.getText());
					setDefaultSummerTemp(defaultSummer);
					double defaultWinter = Double.parseDouble(defaultWinterTextField.getText());
					setDefaultSummerTemp(defaultWinter);
					
					if(summerMonths.contains(currentMonth)) {
						for(int i=0; i<rooms.size(); i++) {
							rooms.get(i).setTemperature(defaultSummer);
						}
					}else if(winterMonths.contains(currentMonth)) {
						for(int i=0; i<rooms.size(); i++) {
							rooms.get(i).setTemperature(defaultWinter);
						}
					}
				}else 
					console.msg("Away Mode not ON");
			}
		});
	
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

	public static SHHController getSHHController() {
		if (shhController != null)
			return shhController;
		else {
			SHHController.shhController = new SHHController(SHSGui.getSHS());
			return shhController;
		}

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
