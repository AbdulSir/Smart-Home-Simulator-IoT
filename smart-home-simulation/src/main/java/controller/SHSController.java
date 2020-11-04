package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	private RoomCounter rooms; 

	public SHSController() {
	}

	public SHSController(SHSGui frame) {
		/** Main GUI **/
		this.frame = frame;
		user = new Users();
		rooms = new RoomCounter();
		
		/** Create default User **/
		Users defaultUser = new Users("Admin");

		/** Control Console **/
		this.console = new Console(frame.getTextAreaConsoleLog());
		console.msg("Welcome to the Smart Home Simulator");

		/** Simulation Button **/
		this.simulationButton = new SimulationButton(frame.getTogglebuttonSimulator(), console);

		/** Temperature Control **/
		this.temperature = new Temperature(frame, frame.getOutsideTemp(), frame.getHouseTemp(), console);
		/** Time **/
		this.time = new Time(frame, frame.getPresstimeBtn(), frame.getTimeSpinner(), frame.getDateChooser(), console);

		/** Edit Simulation **/
		this.editSimulation = new EditSimulation(frame.getPressbuttonEditContext(), user, console, frame);

		/** SHC Controller **/
		this.coreController = new SHCController(frame, console);

		// Open File
		readFileEvent();

		// User Event Handler
		userEvents();
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
				String[] itemsArray = new String[rjFile.getRoomArray().size()+1];

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
				
				//Setting count of entrance to account for default user
				rooms.getRooms().get(itemsArray.length - 1).setCount(1);
				
				// 2d layout
				houseLayout = new HouseLayout(rjFile);
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
	 * User Event Handler
	 */
	private void userEvents() {

		/** Changes User Logged In **/
		final JComboBox comboBoxRole = this.frame.getJComboRole();
		user = new Users();
		comboBoxRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.removeActiveUsers();
				String userToMakeActive = comboBoxRole.getSelectedItem().toString();
				ArrayList<Users> userList = user.getUserList();
				for (Users user : userList) {
					if (user.getName().equalsIgnoreCase(userToMakeActive)) {
						user.setActiveUser(true);
						console.msg(user.getName() + " is now logged in");
						frame.getUserLocationLabel().setText(user.getLocation());
						frame.repaint();
						break;
					} 
				}

			}
		});

		/** Add Use **/
		JButton addNewUserButton = this.frame.getnewUserButton();
		final JTextField enterNewUsername = this.frame.getNewUserName();
		addNewUserButton.addMouseListener(new MouseAdapter() {
			// new user button click event
			public void mouseClicked(MouseEvent e) {
				boolean contains = false;
				
				String NewUsername = enterNewUsername.getText();
				String[] users = user.getUserStringArray();
				for (int i = 0; i < users.length; i++) {
					if (users[i].equals(NewUsername))
						contains = true;
				}
				if (!contains) {
					Users New = new Users(NewUsername);
					int index = 0;
					for (int i = 0; i < user.getUserList().size(); i++) {
						if (user.getUserList().get(i).getName().equals(NewUsername)) {
							index = i;
							break;
						}
					}
					console.msg(
							NewUsername + " has been added. UserID: " + user.getUserList().get(index).getUserNumber());
					int count = rooms.getRooms().get(rooms.getRooms().size()-1).getCount();
					rooms.getRooms().get(rooms.getRooms().size()-1).setCount(count+1);
					frame.repaint();
				} else {
					console.msg("The username \"" + NewUsername
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
				ArrayList<Users> userList = user.getUserList();
				for (Users user : userList) {
					if (user.getName().equalsIgnoreCase(userToDelete)) {
						userList.remove(user);
						console.msg(userToDelete + "'s profile has been deleted from the system");
						frame.repaint();
						break;
					}
				}
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
				frame.repaint();
			}
		});

	}
}