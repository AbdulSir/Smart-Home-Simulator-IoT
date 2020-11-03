package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import controller.SHSController;
import controller.SHCController;
import model.Users;
import model.ReadingJsonFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class SHSGui extends JFrame {
	private JLabel labelProfileImage;
	private JTextArea textAreaConsoleLog;
	private JToggleButton togglebuttonSimulator;
	private JTextField houseTemp;
	private JTextField outsideTemp;
	private JTextField enterNewUsername;
	private Users user;
	private JButton newUser;
	private JComboBox comboBoxDeleteUser;
	private JButton deleteUserButton;
	private JButton pressbuttonEditContext;
	private JLabel CurrentLocation;
	private ReadingJsonFile rjFile;
	private JPanel panelView;
	private JMenuItem mntmOpen;
	private JLabel labelBoxLocation;
	private JComboBox comboBoxRole;
	private JLabel userLocationLabel;
	private JSpinner timeSpinner;
	private JButton presstimebtn;
	private JLabel indoorHouseTempValue;
	private JLabel outdoorTemperatureValue;
	private JLabel weatherValue;
	private JLabel timeValue;
	private JLabel dateValue;
	private JComboBox comboBoxWeather;
	private JDateChooser dateChooser;
	private JComboBox doorsComboBox;
	private JButton OpenDoorsButton;
	private JComboBox lightsComboBox;
	private JButton LightsButton;
	private JComboBox OpenWindowsComboBox;
	private JButton openWindowsButton;
	private JToggleButton AutoModeToggleButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SHSGui frame = new SHSGui();
					// Controller
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

	/**
	 * This method contains all of the code for creating and initializing
	 * components.
	 **/
	private void initComponents() {
		/** Ui Window Icon **/
		setIconImage(Toolkit.getDefaultToolkit().getImage(SHSGui.class.getResource("/resources/shs_128.png")));

		/** Window Title **/
		setTitle("Smart Home Simulation");

		/** Termiante on close **/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/** Window Size **/
		setBounds(100, 100, 1255, 763);

		/** Menu Bar **/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// File Section
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		// Open Option under File
		mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		/** Main Panel **/
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/** Profile Panel **/
		JPanel panelProfile = new JPanel();
		panelProfile.setBackground(Color.WHITE);
		// Profile image
		labelProfileImage = new JLabel("");
		labelProfileImage.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/default.png")));

		/** Horizontal Tabs **/
		JPanel panelControl = new JPanel();
		panelControl.setBackground(Color.WHITE);

		/** Console Panel **/
		JPanel panelConsole = new JPanel();
		panelConsole.setBackground(SystemColor.control);

		/** 2d Layout Panel **/
		panelView = new JPanel();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(panelProfile, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelConsole, GroupLayout.PREFERRED_SIZE, 795, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(11)
								.addComponent(panelControl, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
										.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
								.addGap(18).addComponent(panelConsole, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(panelProfile, GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
				.addGap(10)));
		GroupLayout gl_panelView = new GroupLayout(panelView);
		gl_panelView.setHorizontalGroup(
				gl_panelView.createParallelGroup(Alignment.LEADING).addGap(0, 438, Short.MAX_VALUE));
		gl_panelView
				.setVerticalGroup(gl_panelView.createParallelGroup(Alignment.LEADING).addGap(0, 533, Short.MAX_VALUE));
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

		/** Console Log Text Area */
		textAreaConsoleLog = new JTextArea();
		scrollPane.setViewportView(textAreaConsoleLog);
		textAreaConsoleLog.setEditable(false);
		panelConsole.setLayout(gl_panelConsole);

		/** Horizontal Tabs **/
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE).addContainerGap()));
		gl_panelControl.setVerticalGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE).addContainerGap()));

		/** SHS PANEL **/
		JPanel panelSHS = new JPanel();
		tabbedPane.addTab("SHS", null, panelSHS, null);

		/** Add User **/
		newUser = new JButton("Add User");
		newUser.setBounds(180, 10, 136, 26);
		enterNewUsername = new JTextField();
		enterNewUsername.setBounds(6, 9, 176, 26);
		enterNewUsername.setColumns(10);

		/** Delete User **/
		comboBoxDeleteUser = new JComboBox();
		comboBoxDeleteUser.setBounds(6, 42, 176, 28);
		deleteUserButton = new JButton("Delete User");
		deleteUserButton.setBounds(180, 42, 136, 29);

		/* Calendar **/
		dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 89, 294, 32);
		JLabel labelTime = new JLabel("Time: ");
		labelTime.setBounds(173, 141, 110, 16);
		timeSpinner = new JSpinner();
		timeSpinner.setBounds(213, 133, 91, 32);
		timeSpinner.setModel(new SpinnerDateModel());
		timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));
		pressbuttonEditContext = new JButton("Edit Context Of Simulator");
		pressbuttonEditContext.setBounds(99, 450, 205, 29);
		presstimebtn = new JButton("Set new Date & Time");
		presstimebtn.setBounds(109, 165, 195, 29);
		JLabel labelOutsideTemp = new JLabel("Outside Temp.");
		labelOutsideTemp.setBounds(109, 206, 92, 16);
		outsideTemp = new JTextField(5);
		outsideTemp.setBounds(213, 201, 70, 26);
		JLabel labelOutsideTempValue = new JLabel("\u00B0C");
		labelOutsideTempValue.setBounds(295, 206, 150, 16);
		panelSHS.setLayout(null);
		panelSHS.add(dateChooser);
		panelSHS.add(comboBoxDeleteUser);
		panelSHS.add(enterNewUsername);
		panelSHS.add(newUser);
		panelSHS.add(deleteUserButton);
		panelSHS.add(outsideTemp);
		panelSHS.add(labelTime);
		panelSHS.add(timeSpinner);
		panelSHS.add(labelOutsideTempValue);
		panelSHS.add(presstimebtn);
		panelSHS.add(pressbuttonEditContext);
		panelSHS.add(labelOutsideTemp);
		comboBoxWeather = new JComboBox();
		comboBoxWeather.setBounds(154, 239, 152, 27);
		panelSHS.add(comboBoxWeather);
		comboBoxWeather
				.setModel(new DefaultComboBoxModel(new String[] { "Cloudy", "Rainy", "Sunny", "Windy", "Snowy" }));
		JLabel labelWeather = new JLabel("Weather:");
		labelWeather.setBounds(88, 243, 74, 16);
		panelSHS.add(labelWeather);
		JLabel labelHouseTemp = new JLabel("House Temp.");
		labelHouseTemp.setBounds(118, 278, 83, 16);
		panelSHS.add(labelHouseTemp);
		houseTemp = new JTextField(5);
		houseTemp.setBounds(213, 273, 70, 26);
		panelSHS.add(houseTemp);
		JLabel labelHouseTempValue = new JLabel("\u00B0C");
		labelHouseTempValue.setBounds(289, 278, 15, 16);
		panelSHS.add(labelHouseTempValue);

		/** SHC PANEL **/
		JPanel panelSHC = new JPanel();
		tabbedPane.addTab("SHC", null, panelSHC, null);
		panelSHC.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(16, 6, 277, 406);
		panelSHC.add(tabbedPane_1);
		
		JPanel LightsPanel = new JPanel();
		tabbedPane_1.addTab("Lights", null, LightsPanel, null);
		LightsPanel.setLayout(null);
		
		JLabel lblLights = new JLabel("Rooms");
		lblLights.setBounds(6, 46, 50, 16);
		LightsPanel.add(lblLights);
		
		lightsComboBox = new JComboBox();
		lightsComboBox.setBounds(46, 42, 204, 27);
		LightsPanel.add(lightsComboBox);
		
		AutoModeToggleButton = new JToggleButton("Auto Mode");
		AutoModeToggleButton.setBounds(6, 7, 244, 27);
		LightsPanel.add(AutoModeToggleButton);
		
		LightsButton = new JButton("Turn ON/OFF");
		LightsButton.setBounds(68, 73, 117, 29);
		LightsPanel.add(LightsButton);
		
		JPanel DoorsPanel = new JPanel();
		tabbedPane_1.addTab("Doors", null, DoorsPanel, null);
		DoorsPanel.setLayout(null);
		
		doorsComboBox = new JComboBox();
		doorsComboBox.setBounds(46, 5, 204, 27);
		DoorsPanel.add(doorsComboBox);
		
		JLabel lblNewLabel = new JLabel("Rooms");
		lblNewLabel.setBounds(6, 9, 54, 16);
		DoorsPanel.add(lblNewLabel);
		
		OpenDoorsButton = new JButton("Open/Close");
		OpenDoorsButton.setBounds(69, 32, 117, 29);
		DoorsPanel.add(OpenDoorsButton);
		
		JPanel WindowsPanel = new JPanel();
		tabbedPane_1.addTab("Windows", null, WindowsPanel, null);
		WindowsPanel.setLayout(null);
		
		OpenWindowsComboBox = new JComboBox();
		OpenWindowsComboBox.setBounds(46, 5, 204, 27);
		WindowsPanel.add(OpenWindowsComboBox);
		
		JLabel lblWindows = new JLabel("Rooms");
		lblWindows.setBounds(6, 9, 54, 16);
		WindowsPanel.add(lblWindows);
		
		openWindowsButton = new JButton("Open/Close");
		openWindowsButton.setBounds(69, 32, 117, 29);
		WindowsPanel.add(openWindowsButton);

		/** SHP PANEL **/
		JPanel panelSHP = new JPanel();
		tabbedPane.addTab("SHP", null, panelSHP, null);

		/** SHH PANEL **/
		JPanel panelSHH = new JPanel();
		tabbedPane.addTab("SHH", null, panelSHH, null);

		/** Add Tab **/
		JPanel panelPlus = new JPanel();
		tabbedPane.addTab("+", null, panelPlus, null);
		panelControl.setLayout(gl_panelControl);

		/** Left-most panel of GUI **/
		JPanel panelProfileInfo = new JPanel();

		/** Simulator Button **/
		togglebuttonSimulator = new JToggleButton("Simulator");

		/** House Panel **/
		JPanel panelHouseInfo = new JPanel();

		/** Outside Panel **/
		JPanel panelOutsideInfo = new JPanel();

		/** Form Open Button **/

		JPanel panel = new JPanel();

		GroupLayout gl_panelProfile = new GroupLayout(panelProfile);
		gl_panelProfile.setHorizontalGroup(gl_panelProfile.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelProfile.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
								.addComponent(togglebuttonSimulator, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
								.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 416, Short.MAX_VALUE)
								.addComponent(panelHouseInfo, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
								.addComponent(panelOutsideInfo, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(gl_panelProfile.createSequentialGroup().addGap(153)
						.addComponent(labelProfileImage, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE).addGap(148))
				.addGroup(Alignment.LEADING, gl_panelProfile.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE).addContainerGap()));
		gl_panelProfile.setVerticalGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfile.createSequentialGroup().addContainerGap().addComponent(togglebuttonSimulator)
						.addGap(7).addComponent(labelProfileImage, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelHouseInfo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelOutsideInfo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE).addGap(209)));
		panel.setLayout(null);

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setBounds(6, 6, 61, 16);
		panel.add(dateLabel);

		dateValue = new JLabel("N/A");
		dateValue.setBounds(254, 6, 161, 16);
		panel.add(dateValue);

		JLabel timeLabel = new JLabel("Time");
		timeLabel.setBounds(6, 34, 61, 16);
		panel.add(timeLabel);

		timeValue = new JLabel("N/A");
		timeValue.setBounds(254, 34, 61, 16);
		panel.add(timeValue);

		/** House Temperature **/

		JLabel houseTempLabel = new JLabel("House Temp.");

		indoorHouseTempValue = new JLabel("0");

		GroupLayout gl_panelHouseInfo = new GroupLayout(panelHouseInfo);
		gl_panelHouseInfo.setHorizontalGroup(
			gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup()
					.addContainerGap()
					.addComponent(houseTempLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
					.addComponent(indoorHouseTempValue, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(78))
		);
		gl_panelHouseInfo.setVerticalGroup(
			gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHouseInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(houseTempLabel)
						.addComponent(indoorHouseTempValue, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panelHouseInfo.setLayout(gl_panelHouseInfo);

		/** Weather Label **/

		/** Weather ComboBox Option **/

		/** Outdoor Temperature Label **/

		JLabel outdoorTemperatureLabel = new JLabel("Outdoor Temp.");

		JLabel weatherLabel = new JLabel("Weather");

		outdoorTemperatureValue = new JLabel("0");

		weatherValue = new JLabel("N/A");

		GroupLayout gl_panelOutsideInfo = new GroupLayout(panelOutsideInfo);
		gl_panelOutsideInfo.setHorizontalGroup(
			gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(outdoorTemperatureLabel)
						.addComponent(weatherLabel))
					.addGap(151)
					.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(weatherValue)
						.addComponent(outdoorTemperatureValue, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_panelOutsideInfo.setVerticalGroup(
			gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(outdoorTemperatureLabel)
						.addComponent(outdoorTemperatureValue, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(weatherLabel)
						.addComponent(weatherValue))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panelOutsideInfo.setLayout(gl_panelOutsideInfo);

		/** Users Location **/
		JLabel labelRole = new JLabel("User");
		JLabel labelLocation = new JLabel("Location:");

		/** Combo Box Role **/
		comboBoxRole = new JComboBox();

		/** Combo Box Location **/
		labelBoxLocation = new JLabel();

		userLocationLabel = new JLabel("N/A");

		GroupLayout gl_panelProfileInfo = new GroupLayout(panelProfileInfo);
		gl_panelProfileInfo.setHorizontalGroup(
			gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(labelRole)
						.addComponent(labelLocation))
					.addGap(182)
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userLocationLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(labelBoxLocation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(comboBoxRole, 0, 152, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panelProfileInfo.setVerticalGroup(
			gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addGap(4)
							.addComponent(labelRole))
						.addComponent(comboBoxRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addGap(6)
							.addComponent(labelBoxLocation, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(labelLocation))
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(userLocationLabel)))
					.addContainerGap())
		);
		panelProfileInfo.setLayout(gl_panelProfileInfo);
		panelProfile.setLayout(gl_panelProfile);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Getter
	 */
	public JMenuItem getMntmOpen() {
		return mntmOpen;
	}

	/**
	 * Setter
	 */
	public void setMntmOpen(JMenuItem mntmOpen) {
		this.mntmOpen = mntmOpen;
	}

	/**
	 * Getter
	 */
	public JPanel getPanelView() {
		return panelView;
	}

	/**
	 * Setter
	 */
	public void setPanelView(JPanel panelView) {
		this.panelView = panelView;
	}

	/**
	 * Getting house temperature
	 */
	public JTextField getHouseTemp() {
		return houseTemp;
	}

	/**
	 * Setting house temperature
	 */
	public void setHouseTemp(JTextField houseTemp) {
		this.houseTemp = houseTemp;
	}

	/**
	 * Getting outside temperature
	 */
	public JTextField getOutsideTemp() {
		return outsideTemp;
	}

	/**
	 * Setting outside temperature
	 */
	public void setOutsideTemp(JTextField outsideTemp) {
		this.outsideTemp = outsideTemp;
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
		return pressbuttonEditContext;
	}

	/**
	 * Setter PressbuttonEditContext
	 */
	public void setPressbuttonEditContext(JButton pressbuttonEditContext) {
		pressbuttonEditContext = pressbuttonEditContext;
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

	/**
	 * Getter
	 */
	public JLabel getComboBoxLocation() {
		return labelBoxLocation;
	}

	/**
	 * Setter
	 */
	public void setComboBoxLocation(JLabel comboBoxLocation) {
		this.labelBoxLocation = comboBoxLocation;
	}

	/**
	 * Getter
	 */
	public JLabel getUserLocationLabel() {
		return userLocationLabel;
	}

	/**
	 * Setter
	 */
	public void setUserLocationLabel(JLabel userLocationLabel) {
		this.userLocationLabel = userLocationLabel;
	}

	/**
	 * Getter
	 */
	public JSpinner getTimeSpinner() {
		return timeSpinner;
	}

	/**
	 * Setter
	 */
	public void setTimeSpinner(JSpinner timeSpinner) {
		this.timeSpinner = timeSpinner;
	}

	/**
	 * Getter
	 */
	public JButton getPresstimeBtn() {
		return presstimebtn;
	}

	/**
	 * Setter
	 */
	public void setPresstimeBtn(JButton presstimeBtn) {
		this.presstimebtn = presstimeBtn;
	}

	/**
	 * Getter
	 */
	public JLabel getIndoorHouseTempValue() {
		return indoorHouseTempValue;
	}

	/**
	 * Setter
	 */
	public void setIndoorHouseTempValue(JLabel indoorHouseTempValue) {
		this.indoorHouseTempValue = indoorHouseTempValue;
	}

	/**
	 * Getter
	 */
	public JLabel getOutdoorTemperatureValue() {
		return outdoorTemperatureValue;
	}

	/**
	 * Setter
	 */
	public void setOutdoorTemperatureValue(JLabel outdoorTemperatureValue) {
		this.outdoorTemperatureValue = outdoorTemperatureValue;
	}

	/**
	 * Getter
	 */
	public JLabel getWeatherValue() {
		return weatherValue;
	}

	/**
	 * Setter
	 */
	public void setWeatherValue(JLabel weatherValue) {
		this.weatherValue = weatherValue;
	}

	/**
	 * Getter
	 */
	public JLabel getTimeValue() {
		return timeValue;
	}

	/**
	 * Setter
	 */
	public void setTimeValue(JLabel timeValue) {
		this.timeValue = timeValue;
	}

	/**
	 * Getter
	 */
	public JLabel getDateValue() {
		return dateValue;
	}

	/**
	 * Setter
	 */
	public void setDateValue(JLabel dateValue) {
		this.dateValue = dateValue;
	}
	/**
	 * Getter
	 */
	public JComboBox getComboBoxWeather() {
		return comboBoxWeather;
	}
	/**
	 * Setter
	 */
	public void setComboBoxWeather(JComboBox comboBoxWeather) {
		this.comboBoxWeather = comboBoxWeather;
	}
	
	/**
	 * Getter
	 */
	public JDateChooser getDateChooser() {
		return dateChooser;
	}
	/**
	 * Setter
	 */
	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}
	
	/**
	 * Getter
	 */
	public JComboBox getDoorsComboBox() {
		return doorsComboBox;
	}

	/**
	 * Setter
	 */
	public void setDoorsComboBox(JComboBox doorsComboBox) {
		this.doorsComboBox = doorsComboBox;
	}

	/**
	 * Getter
	 */
	public JButton getOpenDoorsButton() {
		return OpenDoorsButton;
	}

	/**
	 * Setter
	 */
	public void setOpenDoorsButton(JButton openDoorsButton) {
		OpenDoorsButton = openDoorsButton;
	}

	/**
	 * Getter
	 */
	public JComboBox getLightsComboBox() {
		return lightsComboBox;
	}

	/**
	 * Setter
	 */
	public void setLightsComboBox(JComboBox lightsComboBox) {
		this.lightsComboBox = lightsComboBox;
	}

	/**
	 * Getter
	 */
	public JButton getLightsButton() {
		return LightsButton;
	}

	/**
	 * Setter
	 */
	public void setLightsButton(JButton lightsButton) {
		LightsButton = lightsButton;
	}

	/**
	 * Getter
	 */
	public JComboBox getOpenWindowsComboBox() {
		return OpenWindowsComboBox;
	}

	/**
	 * Setter
	 */
	public void setOpenWindowsComboBox(JComboBox openWindowsComboBox) {
		OpenWindowsComboBox = openWindowsComboBox;
	}

	/**
	 * Getter
	 */
	public JButton getOpenWindowsButton() {
		return openWindowsButton;
	}

	/**
	 * Setter
	 */
	public void setOpenWindowsButton(JButton openWindowsButton) {
		this.openWindowsButton = openWindowsButton;
	}

	/**
	 * Getter
	 */
	public JToggleButton getAutoModeToggleButton() {
		return AutoModeToggleButton;
	}

	/**
	 * Setter
	 */
	public void setAutoModeToggleButton(JToggleButton autoModeToggleButton) {
		AutoModeToggleButton = autoModeToggleButton;
	}
}