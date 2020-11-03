package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import model.HouseLayout;
import model.ReadingJsonFile;
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
	private ContextSimulation contextSimulation;

	public SHSController() {
	}

	public SHSController(SHSGui frame) {
		/** Main GUI **/
		this.frame = frame;
		user = new Users();

		/** Create default User **/
		Users defaultUser = new Users("Admin", "PARENT");

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

		// Open File
		readFileEvent();

		// Load File
		loadFileEvent();

		// Read File
		saveFileEvent();

		// User Event Handler
		userEvents();
	}

	/**
	 * Save user in .txt file Event Handler
	 */
	private void saveFileEvent() {

		this.frame.getMntmSave().addActionListener(new ActionListener() {

			// On Click
			public void actionPerformed(ActionEvent action) {

				try {
					// File & Object Output Stream
					FileOutputStream fos = new FileOutputStream(new File("myUsers.txt"));
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

				} catch (FileNotFoundException file_exception) {
					file_exception.printStackTrace();
				} catch (IOException io_exception) {
					io_exception.printStackTrace();
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

				try {
					// File & Object Input Stream
					FileInputStream fis = new FileInputStream(new File("myUsers.txt"));
					ObjectInputStream ois = new ObjectInputStream(fis);

					ArrayList<Users> file_list_users = new ArrayList<Users>();

					// Read file
					while (fis.available() > 0) {
						file_list_users.add((Users) ois.readObject());
					}

					user.setUserList(file_list_users);

					// Close Stream
					fis.close();
					ois.close();

					// Console Message
					console.msg("Users Profile has been LOADED");

				} catch (IOException io_exception) {
					System.out.println("File not found");
					io_exception.printStackTrace();
				} catch (ClassNotFoundException class_exception) {
					class_exception.printStackTrace();
				}

				// Refresh Ui
				frame.repaint();

			}
		});

	}

	/**
	 * Read File Event Handler
	 */
	private void readFileEvent() {

		/** Open File **/
		this.frame.getMntmOpen().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent action) {
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

				if (jFileChooser.getSelectedFile() != null) {
					// read .json file
					rjFile = new ReadingJsonFile(jFileChooser.getSelectedFile().toString());
					// rjFile.getRoomArray().size() - Number of rooms in the JSON file
					// + 2 - Outside and Hallway
					String[] userRoomArray = new String[rjFile.getRoomArray().size() + 2];
					String[] userWindowArray = new String[rjFile.getRoomArray().size()];

					// get value from array
					for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
						userRoomArray[i] = userWindowArray[i] = rjFile.getRoomArray().get(i).toString();
						new Windows(rjFile.getRoomArray().get(i).toString());
					}
					userRoomArray[userRoomArray.length - 1] = "Outside";
					userRoomArray[userRoomArray.length - 2] = "Hallway";
					// 2d layout
					houseLayout = new HouseLayout(rjFile);
					frame.getPanelView().add(houseLayout);

					editSimulation.getContext().getComboBoxLocation().setModel(new DefaultComboBoxModel(userRoomArray));
					editSimulation.getContext().getComboBoxWindowLocation()
							.setModel(new DefaultComboBoxModel(userWindowArray));

					// refresh layout
					frame.repaint();
				} else {
					System.out.println("No file selected.");
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
						frame.getLabelUserPermissionValue().setText(user.getPermission());

						// Refresh UI
						frame.repaint();
						break;
					} else {
						continue;
					}
				}

			}
		});

		/** Add User **/
		JButton addNewUserButton = this.frame.getnewUserButton();
		JTextField enterNewUsername = this.frame.getNewUserName();
		JComboBox comboBoxPermission = this.frame.getComboBoxPermission();
		addNewUserButton.addMouseListener(new MouseAdapter() {
			// new user button click event
			public void mouseClicked(MouseEvent e) {
				boolean contains = false;
				String newUsername = enterNewUsername.getText();
				String userPermission = comboBoxPermission.getSelectedItem().toString();
				String[] users = user.getUserStringArray();
				for (int i = 0; i < users.length; i++) {
					if (users[i].equals(newUsername))
						contains = true;
				}
				if (!contains) {
					Users New = new Users(newUsername, userPermission);
					int index = 0;
					for (int i = 0; i < user.getUserList().size(); i++) {
						if (user.getUserList().get(i).getName().equals(newUsername)) {
							index = i;
							break;
						}
					}
					console.msg(
							newUsername + " has been added. UserID: " + user.getUserList().get(index).getUserNumber());
					frame.repaint();
				} else {
					console.msg("The username \"" + newUsername
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
					} else {
						continue;
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