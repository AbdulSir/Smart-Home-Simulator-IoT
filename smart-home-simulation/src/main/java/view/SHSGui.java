package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.SHSController;
import controller.Users;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

public class SHSGui extends JFrame {
	private JLabel labelProfileImage;
	private JTextArea textAreaConsoleLog;
	private JToggleButton togglebuttonSimulator;
	private JTextField enterNewUsername;
	private Users user;
	private JComboBox comboBoxRole;
	private JButton newUser;
	private JComboBox comboBoxDeleteUser;
	private JButton deleteUserButton;
	private JButton PressbuttonEditContext;
	private JLabel CurrentLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SHSGui frame = new SHSGui();
					SHSController controller = new SHSController(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SHSGui() {
		initComponents();
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating and
	// initializing components.
	//////////////////////////////////////////////////////////////
	private void initComponents() {
		user = new Users();
		setTitle("Smart Home Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 998, 551);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panelContainer = new JPanel();
		panelContainer.setBackground(Color.WHITE);

		labelProfileImage = new JLabel("");

		JPanel panelControl = new JPanel();
		panelControl.setBackground(Color.WHITE);

		JPanel panelView = new JPanel();
		panelView.setBackground(Color.WHITE);

		JPanel panelConsole = new JPanel();
		panelConsole.setBackground(SystemColor.control);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(panelContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addGap(5)
								.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addGap(5)
								.addComponent(panelView, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
								.addGap(8))
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelConsole, GroupLayout.PREFERRED_SIZE, 687, Short.MAX_VALUE)
								.addContainerGap()))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
										.addComponent(panelControl, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 349,
												Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelConsole,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(panelContainer, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(70, Short.MAX_VALUE)));
		GroupLayout gl_panelView = new GroupLayout(panelView);
		gl_panelView.setHorizontalGroup(
				gl_panelView.createParallelGroup(Alignment.LEADING).addGap(0, 422, Short.MAX_VALUE));
		gl_panelView
				.setVerticalGroup(gl_panelView.createParallelGroup(Alignment.LEADING).addGap(0, 418, Short.MAX_VALUE));
		panelView.setLayout(gl_panelView);

		JLabel labelConsoleLog = new JLabel("Console Log");

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panelConsole = new GroupLayout(panelConsole);
		gl_panelConsole.setHorizontalGroup(gl_panelConsole.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConsole.createSequentialGroup().addGap(10).addGroup(gl_panelConsole
						.createParallelGroup(Alignment.LEADING).addComponent(labelConsoleLog)
						.addGroup(gl_panelConsole.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)))
						.addGap(1234)));
		gl_panelConsole.setVerticalGroup(gl_panelConsole.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConsole.createSequentialGroup().addGap(14).addComponent(labelConsoleLog).addGap(6)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		textAreaConsoleLog = new JTextArea();
		scrollPane.setViewportView(textAreaConsoleLog);
		textAreaConsoleLog.setEditable(false);
		panelConsole.setLayout(gl_panelConsole);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE).addContainerGap()));
		gl_panelControl.setVerticalGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE).addContainerGap()));
//************************************************SHS PANEL************************************************//		
		JPanel panelSHS = new JPanel();
		tabbedPane.addTab("SHS", null, panelSHS, null);
//************************************************Add User************************************************//			
		newUser = new JButton("Add User");
		enterNewUsername = new JTextField();
		enterNewUsername.setText("Enter New Username");
		enterNewUsername.setColumns(10);
//************************************************Delete User************************************************//		
		comboBoxDeleteUser = new JComboBox();

		deleteUserButton = new JButton("Delete User");

		GroupLayout gl_panelSHS = new GroupLayout(panelSHS);
		gl_panelSHS.setHorizontalGroup(gl_panelSHS.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				gl_panelSHS.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSHS.createSequentialGroup().addGap(6).addComponent(comboBoxDeleteUser,
										0, 190, Short.MAX_VALUE))
								.addComponent(enterNewUsername, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 196,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING)
								.addComponent(newUser, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addComponent(deleteUserButton))
						.addGap(17)));
		gl_panelSHS.setVerticalGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSHS.createSequentialGroup().addGap(9)
						.addGroup(gl_panelSHS.createParallelGroup(Alignment.BASELINE)
								.addComponent(enterNewUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(newUser, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelSHS.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxDeleteUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(deleteUserButton))
						.addContainerGap(225, Short.MAX_VALUE)));
		panelSHS.setLayout(gl_panelSHS);
//************************************************SHC PANEL************************************************//	
		JPanel panelSHC = new JPanel();
		tabbedPane.addTab("SHC", null, panelSHC, null);

		JPanel panelSHP = new JPanel();
		tabbedPane.addTab("SHP", null, panelSHP, null);

		JPanel panelSHH = new JPanel();
		tabbedPane.addTab("SHH", null, panelSHH, null);
//************************************************Add Tab************************************************//			
		JPanel panelPlus = new JPanel();
		tabbedPane.addTab("+", null, panelPlus, null);
		panelControl.setLayout(gl_panelControl);
//************************************************Left-most panel of GUI************************************************//	
		JPanel panelProfileInfo = new JPanel();
//************************************************Simulator Button************************************************//	
		togglebuttonSimulator = new JToggleButton("Simulator");

		JPanel panelHouseInfo = new JPanel();

		JPanel panelOutsideInfo = new JPanel();

		JPanel panelDateTime = new JPanel();
		
		PressbuttonEditContext = new JButton("Edit");

		GroupLayout gl_panelContainer = new GroupLayout(panelContainer);
		gl_panelContainer.setHorizontalGroup(
			gl_panelContainer.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContainer.createSequentialGroup()
							.addGap(6)
							.addComponent(PressbuttonEditContext)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelProfileImage, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addComponent(togglebuttonSimulator, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
						.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 267, Short.MAX_VALUE)
						.addComponent(panelHouseInfo, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
						.addComponent(panelOutsideInfo, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
						.addComponent(panelDateTime, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelContainer.setVerticalGroup(
			gl_panelContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(togglebuttonSimulator)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContainer.createParallelGroup(Alignment.LEADING)
						.addComponent(labelProfileImage, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(PressbuttonEditContext))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelHouseInfo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelOutsideInfo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelDateTime, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
		);
//************************************************Date Time************************************************//	
		JDateChooser dateChooser = new JDateChooser();

		JLabel labelTime = new JLabel("Time");

		JLabel labelTimeValue = new JLabel("02:08:33");
		GroupLayout gl_panelDateTime = new GroupLayout(panelDateTime);
		gl_panelDateTime.setHorizontalGroup(gl_panelDateTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDateTime.createSequentialGroup().addContainerGap().addGroup(gl_panelDateTime
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDateTime.createSequentialGroup()
								.addComponent(labelTime, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(labelTimeValue,
										GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)).addContainerGap()));
		gl_panelDateTime
				.setVerticalGroup(
						gl_panelDateTime.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_panelDateTime.createSequentialGroup().addContainerGap()
												.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panelDateTime.createParallelGroup(Alignment.BASELINE)
														.addComponent(labelTime).addComponent(labelTimeValue))
												.addContainerGap(24, Short.MAX_VALUE)));
		panelDateTime.setLayout(gl_panelDateTime);
//************************************************Temperature************************************************//	
		JLabel labelHouseTemp = new JLabel("Inside Temp.");

		JLabel labelHouseTempValue = new JLabel("15C");
		GroupLayout gl_panelHouseInfo = new GroupLayout(panelHouseInfo);
		gl_panelHouseInfo.setHorizontalGroup(gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup().addContainerGap()
						.addComponent(labelHouseTemp, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(labelHouseTempValue, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panelHouseInfo
				.setVerticalGroup(gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHouseInfo.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelHouseInfo.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelHouseTemp).addComponent(labelHouseTempValue))
								.addContainerGap(17, Short.MAX_VALUE)));
		panelHouseInfo.setLayout(gl_panelHouseInfo);
//************************************************Weather************************************************//	
		JLabel labelWeather = new JLabel("Weather:");

		JComboBox comboBoxWeather = new JComboBox();
		comboBoxWeather
				.setModel(new DefaultComboBoxModel(new String[] { "Cloudy", "Rainy", "Sunny", "Windy", "Snowy" }));

		JLabel labelOutsideTemp = new JLabel("Outside Temp.");

		JLabel labelOutsideTempValue = new JLabel("15C");
		GroupLayout gl_panelOutsideInfo = new GroupLayout(panelOutsideInfo);
		gl_panelOutsideInfo.setHorizontalGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup().addContainerGap().addGroup(gl_panelOutsideInfo
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOutsideInfo.createSequentialGroup()
								.addComponent(labelWeather, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(comboBoxWeather, GroupLayout.PREFERRED_SIZE, 152,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelOutsideInfo.createSequentialGroup().addComponent(labelOutsideTemp).addGap(10)
								.addComponent(labelOutsideTempValue)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panelOutsideInfo.setVerticalGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup().addContainerGap()
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelOutsideInfo.createSequentialGroup().addGap(3)
										.addComponent(labelWeather))
								.addComponent(comboBoxWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(labelOutsideTemp).addComponent(labelOutsideTempValue))
						.addContainerGap()));
		panelOutsideInfo.setLayout(gl_panelOutsideInfo);
//************************************************Users/Location************************************************//	
		JLabel labelRole = new JLabel("User");
		JLabel labelLocation = new JLabel("Location:");

		comboBoxRole = new JComboBox();
		// Will update User pop up menu every time the user opens the menu
		PopupMenuListener userListListener = new PopupMenuListener() {
			boolean initialized = false;

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if (!initialized) {
					String[] userNameArray = new String[user.getUserList().size()];
					for (int i = 0; i < userNameArray.length; i++) {
						userNameArray[i] = user.getUserList().get(i).getName();
					}
					comboBoxRole.setModel(new DefaultComboBoxModel(userNameArray));
				}
			}
		};
		comboBoxRole.addPopupMenuListener(userListListener);

		JComboBox comboBoxLocation = new JComboBox();
		GroupLayout gl_panelProfileInfo = new GroupLayout(panelProfileInfo);
		gl_panelProfileInfo.setHorizontalGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup().addContainerGap().addGroup(gl_panelProfileInfo
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup().addComponent(labelRole)
								.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE).addComponent(
										comboBoxRole, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelProfileInfo.createSequentialGroup().addComponent(labelLocation)
								.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE).addComponent(
										comboBoxLocation, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_panelProfileInfo.setVerticalGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE).addComponent(labelRole)
								.addComponent(comboBoxRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelLocation).addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(64)));
		panelProfileInfo.setLayout(gl_panelProfileInfo);
		panelContainer.setLayout(gl_panelContainer);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Getting profile image
	 */
	public JLabel getLabelProfileImage() {
		return labelProfileImage;
	}

	/**
	 * Setting profile image
	 */
	public void setLabelProfileImage(String image_path) {
		labelProfileImage.setIcon(new ImageIcon(SHSGui.class.getResource(image_path)));
	}

	/**
	 * Getting textAreaConsoleLog
	 */
	public JTextArea getTextAreaConsoleLog() {
		return textAreaConsoleLog;
	}

	/**
	 * 
	 * Setting textAreaConsoleLog
	 */
	public void setTextAreaConsoleLog(JTextArea textAreaConsoleLog) {
		this.textAreaConsoleLog = textAreaConsoleLog;
	}

	/**
	 * Getter toggleButtonSimulator
	 */
	public JToggleButton getTogglebuttonSimulator() {
		return togglebuttonSimulator;
	}

	/**
	 * Setter toggleButtonSimulator
	 */
	public void setTogglebuttonSimulator(JToggleButton togglebuttonSimulator) {
		this.togglebuttonSimulator = togglebuttonSimulator;
	}

	/**
	 * getter comboBoxRole
	 */
	public JComboBox getJComboRole() {
		return comboBoxRole;
	}

	/**
	 * Getter user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * Getter the new user button
	 */
	public JButton getnewUserButton() {
		return newUser;
	}

	/**
	 * Getter new user name
	 */
	public JTextField getNewUserName() {
		return enterNewUsername;
	}
	/**
	 * Getter deleteUserButton
	 */
	public JButton getDeleteUserButton() {
		return deleteUserButton;
	}
	/**
	 * Getter comboBoxDeleteUser
	 */
	public JComboBox getDeleteUserBox() {
		return comboBoxDeleteUser;
	}
	/**
	 * Getter PressbuttonEditContext
	 */
	public JButton getPressbuttonEditContext() {
		return PressbuttonEditContext;
	}
	/**
	 * Setter PressbuttonEditContext
	 */
	public void setPressbuttonEditContext(JButton pressbuttonEditContext) {
		PressbuttonEditContext = pressbuttonEditContext;
	}
	/**
	 * Getter CurrentLocation
	 */
	public JLabel getCurrentLocation() {
		return CurrentLocation;
	}
	/**
	 * Setter CurrentLocation
	 */
	public void setCurrentLocation(JLabel currentLocation) {
		CurrentLocation = currentLocation;
	}
}