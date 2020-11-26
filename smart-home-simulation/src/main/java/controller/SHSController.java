package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import model.Doors;
import model.HouseLayout;
import model.Lights;
import model.ReadingJsonFile;
import model.RoomCounter;
import model.Temperature;
import model.Time;
import model.Users;
import model.Windows;
import view.ContextSimulation;
import view.SHSGui;

public class SHSController {
	private SHSGui frame;
	private Console console;
	private Temperature temperature;
	private Time time;
	private Users user;
	private SimulationButton simulationButton;
	private EditSimulation editSimulation;
	private HouseLayout houseLayout;
	private ReadingJsonFile rjFile;
	private SHCController coreController;
	private SHPController securityController;
	private RoomCounter rooms;
	private static SHSController shsController;
	private PrintWriter pw;
	public SHSController() {

	}

	private SHSController(SHSGui frame, SHCController coreController, SHPController securityController) {
		/** Main GUI **/
		this.frame = frame;
		user = Users.getUser();
		rooms = RoomCounter.getRoomCounter();

		/** Create default User **/
		Users defaultUser = new Users("Admin","PARENT");

		/** Control Console **/
		this.console = Console.getConsole();
		console.msg("Welcome to the Smart Home Simulator");

		/** Simulation Button **/
		this.simulationButton = SimulationButton.getSimulatorButton();





		/** SHC Controller **/
		this.coreController = coreController;
		this.coreController.setSimButton(simulationButton);
		
		/** SHP Controller **/
		this.securityController=securityController;
		
		/** PrintWriter **/
		try {
			pw = new PrintWriter(new FileOutputStream("SHSControllerLog.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/** Temperature Control **/
		this.temperature = new Temperature(frame, frame.getOutsideTemp(), frame.getHouseTemp(), console, this);
		
		/** Time **/
		this.time = new Time(frame, frame.getPresstimeBtn(), frame.getTimeSpinner(), frame.getDateChooser(), frame.getSlider(), console, this);
		
		/** Edit Simulation **/
		this.editSimulation = new EditSimulation(frame.getPressbuttonEditContext(), user, console, simulationButton, frame, coreController, securityController, this);
		
		// Open File
		readFileEvent();

		// Load File
		loadFileEvent();

		// Read File
		saveFileEvent();

		// User Event Handler
		userEvents();

		// Simulation Button Handler
		simulationButtonEvents();

		// Slider label
		sliderLabel();
	}

	/**
	 * Read File Event Handler
	 */
	private void readFileEvent() {

		/** Open File **/
		this.frame.getMntmOpen().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// open file explorer
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setDialogTitle("Choose a JSON file: ");
				jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jFileChooser.setCurrentDirectory(new File("."));

				// get file name
				int returnValue = jFileChooser.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jFileChooser.getSelectedFile().isFile()) {
						System.out.println("You selected: " + jFileChooser.getSelectedFile());
					}
				}

				// read .json file
				rjFile = new ReadingJsonFile(jFileChooser.getSelectedFile().toString());
				// rjFile.getRoomArray().size() - Number of rooms in the JSON file
				// + 2 - Outside and Entrance
				String[] userRoomArray = new String[rjFile.getRoomArray().size() + 2];
				String[] userWindowsArray = new String[rjFile.getRoomArray().size()];
				String[] itemsArray = new String[rjFile.getRoomArray().size() + 1];

				// get value from array
				for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
					userRoomArray[i] = itemsArray[i] = userWindowsArray[i] = rjFile.getRoomArray().get(i).toString();

					new Windows(rjFile.getRoomArray().get(i).toString());
					new Doors(rjFile.getRoomArray().get(i).toString());
					new Lights(rjFile.getRoomArray().get(i).toString());
					new RoomCounter(rjFile.getRoomArray().get(i).toString());
					if (i == rjFile.getRoomArray().size() - 1) {
						new Doors("Entrance");
						new Lights("Entrance");
						new RoomCounter("Entrance");
					}
				}
				userRoomArray[userRoomArray.length - 1] = "Outside";
				userRoomArray[userRoomArray.length - 2] = "Entrance";
				itemsArray[itemsArray.length - 1] = "Entrance";

				// Setting count of entrance to account for default user
				rooms.getRooms().get(itemsArray.length - 1).incrementCounter();
				coreController.checkLights();
				
				// 2d layout
				houseLayout = new HouseLayout(rjFile, securityController);
				houseLayout = HouseLayout.getHouseLayout();
				frame.getPanelView().add(houseLayout);

				editSimulation.getContext().getComboBoxLocation().setModel(new DefaultComboBoxModel(userRoomArray));
				editSimulation.getContext().getComboBoxWindowLocation().setModel(new DefaultComboBoxModel(userWindowsArray));
				frame.getDoorsComboBox().setModel(new DefaultComboBoxModel(itemsArray));
				frame.getLightsComboBox().setModel(new DefaultComboBoxModel(itemsArray));
				frame.getOpenWindowsComboBox().setModel(new DefaultComboBoxModel(userWindowsArray));

				// refresh layout
				frame.repaint();
			}
		});
	}
	
	/**
	 * Save user in .txt file Event Handler
	 */
	private void saveFileEvent() {

		this.frame.getMntmSave().addActionListener(new ActionListener() {

			// On Click
			public void actionPerformed(ActionEvent action) {

				// Parent Component
				JFrame parentFrame = new JFrame();

				// Open File Explorer
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setDialogTitle("Select Location to Save File");
				jFileChooser.setCurrentDirectory(new File("."));

				// User Selection
				int userSelection = jFileChooser.showSaveDialog(parentFrame);

				if (userSelection == JFileChooser.APPROVE_OPTION) {

					// File Selected
					String fileToSave = jFileChooser.getSelectedFile().toString();
					System.out.println(fileToSave);

					try {
						// File & Object Output Stream
						FileOutputStream fos = new FileOutputStream(new File(fileToSave));
						ObjectOutputStream oos = new ObjectOutputStream(fos);

						// Write User Object
						for (Users u : user.getUserList()) {
							oos.writeObject(u);
						}

						// Close Stream
						fos.close();
						oos.close();

						// Console Message
						console.msg("Users Profile has been SAVED.");
						appendToLog("Users Profile has been SAVED.");

					} catch (FileNotFoundException file_exception) {
						file_exception.printStackTrace();
					} catch (IOException io_exception) {
						io_exception.printStackTrace();
					}

				}
			}
		});

	}

	/**
	 * Load user from .txt file Event Handler
	 */
	private void loadFileEvent() {

		this.frame.getMntmLoad().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent action) {

				// open file explorer
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setDialogTitle("Choose a Text file: ");
				jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jFileChooser.setCurrentDirectory(new File("."));

				// get file name
				int returnValue = jFileChooser.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jFileChooser.getSelectedFile().isFile()) {
						System.out.println("You loaded: " + jFileChooser.getSelectedFile());
					}
				}

				if (jFileChooser.getSelectedFile() != null) {
					try {
						// File & Object Input Stream
						FileInputStream fis = new FileInputStream(new File(jFileChooser.getSelectedFile().toString()));
						ObjectInputStream ois = new ObjectInputStream(fis);

						ArrayList<Users> file_list_users = new ArrayList<Users>();

						// Read file
						while (fis.available() > 0) {
							file_list_users.add((Users) ois.readObject());
						}

						user.setUserList(file_list_users);
						for(int i = 0; i < user.getUserList().size(); i++) {
							String location = user.getUserList().get(i).getLocation();
							for(int j = 0; j < rooms.getRooms().size(); j++) {
								if(rooms.getRooms().get(j).getLocation().equals(location)) {
									rooms.getRooms().get(j).incrementCounter();
									break;
								}
							}
						}
						rooms.getRooms().get(rooms.getRooms().size() - 1).decrementCounter();
						coreController.checkLights();
						
						// Close Stream
						fis.close();
						ois.close();

						// Console Message
						console.msg("Users Profile has been LOADED");
						appendToLog("Users Profile has been LOADED");

					} catch (IOException io_exception) {
						System.out.println("File not found");
						io_exception.printStackTrace();
					} catch (ClassNotFoundException class_exception) {
						class_exception.printStackTrace();
					}

					// Refresh Ui
					paint();
				}
			}
		});

	}
	
	

	/**
	 * User Event Handler
	 */
	private void userEvents() {

		/** Changes User Logged In **/
		final JComboBox comboBoxRole = this.frame.getJComboRole();
		user = Users.getUser();
		comboBoxRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.removeActiveUsers();
				String userToMakeActive = comboBoxRole.getSelectedItem().toString();
				String permission = "";
				ArrayList<Users> userList = user.getUserList();
				for (Users user : userList) {
					if (user.getName().equalsIgnoreCase(userToMakeActive)) {
						user.setActiveUser(true);
						permission = user.getPermission();
						console.msg("A new user is logged into the system: " + user.getName() + ". UserID: " + user.getUserNumber());
						appendToLog("A new user is logged into the system: " + user.getName() + ". UserID: " + user.getUserNumber());
						frame.getUserLocationLabel().setText(user.getLocation());
						frame.getLabelUserPermissionValue().setText(user.getPermission());
						break;
					}
				}
				switch(permission) {
				case "PARENT":
					frame.getLabelProfileImage().setIcon(new ImageIcon(SHSGui.class.getResource("/resources/mother.png")));
					break;
				case "CHILDREN":
					frame.getLabelProfileImage().setIcon(new ImageIcon(SHSGui.class.getResource("/resources/daughter.png")));
					break;
				case "GUEST":
					frame.getLabelProfileImage().setIcon(new ImageIcon(SHSGui.class.getResource("/resources/guest.png")));
					break;
				case "STRANGER":
					frame.getLabelProfileImage().setIcon(new ImageIcon(SHSGui.class.getResource("/resources/stranger.png")));
					break;
				default:
					frame.getLabelProfileImage().setIcon(new ImageIcon(SHSGui.class.getResource("/resources/default.png")));
					break;	
				}
				paint();
			}
		});

		/** Add Use **/
		JButton addNewUserButton = this.frame.getnewUserButton();
		final JTextField enterNewUsername = this.frame.getNewUserName();
		JComboBox comboBoxPermission = this.frame.getComboBoxPermission();
		addNewUserButton.addMouseListener(new MouseAdapter() {
			// new user button click event
			public void mouseClicked(MouseEvent e) {
				boolean contains = false;
				String NewUsername = enterNewUsername.getText();
				String userPermission = comboBoxPermission.getSelectedItem().toString();
				String[] users = user.getUserStringArray();
				for (int i = 0; i < users.length; i++) {
					if (users[i].equals(NewUsername)) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					Users New = new Users(NewUsername, userPermission);
					int index = 0;
					for (int i = 0; i < user.getUserList().size(); i++) {
						if (user.getUserList().get(i).getName().equals(NewUsername)) {
							index = i;
							break;
						}
					}
					console.msg(NewUsername + " has been added. UserID: " + user.getUserList().get(index).getUserNumber());
					appendToLog(NewUsername + " has been added. UserID: " + user.getUserList().get(index).getUserNumber());
					rooms.getRooms().get(rooms.getRooms().size() - 1).incrementCounter();
					coreController.checkLights();
					paint();
				} else {
					console.msg("The username \"" + NewUsername
							+ "\" is already linked to an existing user. User will not be added");
					appendToLog("The username \"" + NewUsername
							+ "\" is already linked to an existing user. User will not be added");
				}
			}
		});

		/** Delete User **/
		JButton deleteUserButton = this.frame.getDeleteUserButton();
		final JComboBox comboBoxDeleteUser = this.frame.getDeleteUserBox();
		deleteUserButton.addMouseListener(new MouseAdapter() {
			// Delete User function
			public void mouseClicked(MouseEvent e) {
				String userToDelete = comboBoxDeleteUser.getSelectedItem().toString();
				String location = "";
				ArrayList<Users> userList = user.getUserList();
				for (Users user : userList) {
					if (user.getName().equalsIgnoreCase(userToDelete)) {
						userList.remove(user);
						location = user.getLocation();
						console.msg(userToDelete + "'s profile has been deleted from the system");
						appendToLog(userToDelete + "'s profile has been deleted from the system");
						paint();
						break;
					}
				}
				for (RoomCounter room : rooms.getRooms()) {
					if (room.getLocation().equals(location)) {
						room.decrementCounter();
						break;
					}
				}
				coreController.checkLights();
			}
		});

		/** Will update User pop up menu every time the user opens the menu **/
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
					comboBoxRole.setModel(new DefaultComboBoxModel(userNameArray));
				}
			}
		};
		comboBoxRole.addPopupMenuListener(userListListener);

		/** Will update User pop up menu every time the user opens the menu **/
		PopupMenuListener userDeletedListener = new PopupMenuListener() {
			boolean initialized = false;

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if (!initialized) {
					String[] userNameArray = new String[user.getUserList().size()];
					userNameArray = user.getUserStringArray();
					comboBoxDeleteUser.setModel(new DefaultComboBoxModel(userNameArray));
				}
			}
		};
		comboBoxDeleteUser.addPopupMenuListener(userDeletedListener);

		frame.getComboBoxWeather().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String weather = frame.getComboBoxWeather().getSelectedItem().toString();
				frame.getWeatherValue().setText(weather);
			}
		});

	}
	/**
	 * On Click Simulation Button Event Handler
	 */
	private void simulationButtonEvents() {
		this.frame.getTogglebuttonSimulator().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();

				if (state == ItemEvent.SELECTED) {
					// Set Simulation State to TRUE
					simulationButton.setSimulatorState(true);

					// Run timer
					time.startTimer();
					
					//Refresh UI
					frame.repaint();
					
					// Display Message to Console
					console.msg("Simulator ON");
					appendToLog("Simulator ON");
				} else if (state == ItemEvent.DESELECTED) {
					// Set Simulation State to FALSE
					simulationButton.setSimulatorState(false);

					// Stop Timer
					time.stopTimer();

					// Display Message to Console
					console.msg("Simulator OFF");
					appendToLog("Simulator OFF");
				}

			}
		});

	}

	/**
	 * Labels for the JSlider
	 */
	private void sliderLabel() {
		// Add position label in the slider
		JSlider slider = this.frame.getSlider();
		Hashtable position = new Hashtable();
		position.put(1, new JLabel("1m"));
		position.put(15, new JLabel("15m"));
		position.put(30, new JLabel("30m"));
		position.put(45, new JLabel("45m"));
		position.put(60, new JLabel("1h"));
		position.put(75, new JLabel("1h15"));
		position.put(90, new JLabel("1h30"));
		position.put(105, new JLabel("1h45"));
		position.put(120, new JLabel("2h"));
		slider.setLabelTable(position);

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
	 * Repaints frame if the simulator is on
	 */
	private void paint() {
		if(simulationButton.isSimulatorState())
			frame.repaint();
	}

	/**
	 * Singleton Getter
	 */
	public static SHSController getSHSController() {
		if (shsController != null)
			return shsController;
		else {
			SHSController.shsController = new SHSController(SHSGui.getSHS(),SHCController.getSHCController(),SHPController.getSHPController());
			return shsController;
		}
		
	}
	/**
	 * Getter
	 */
	public ReadingJsonFile getRjFile() {
		return rjFile;
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
	 * Append all of the console messages to the corresponding log file
	 */
	public void appendToLog(String text) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		pw.write("[" + formatter.format(date) + "] " + text + "\n");
	}

}
