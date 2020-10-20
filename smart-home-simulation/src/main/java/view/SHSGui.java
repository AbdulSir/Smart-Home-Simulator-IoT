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

import controller.ReadingJsonFile;
import controller.SHSController;
import controller.Users;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		enterNewUsername = new JTextField();
		enterNewUsername.setColumns(10);

		/** Delete User **/
		comboBoxDeleteUser = new JComboBox();
		deleteUserButton = new JButton("Delete User");

		/* Calendar **/
		JDateChooser dateChooser = new JDateChooser();
		JLabel labelTime = new JLabel("Time: ");
		timeSpinner = new JSpinner();
		timeSpinner.setModel(new SpinnerDateModel());
		timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm:ss"));
		pressbuttonEditContext = new JButton("Edit Context Of Simulator");

		GroupLayout gl_panelSHS = new GroupLayout(panelSHS);
		gl_panelSHS.setHorizontalGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING).addGroup(gl_panelSHS
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING).addGroup(gl_panelSHS
						.createSequentialGroup()
						.addGroup(gl_panelSHS.createParallelGroup(Alignment.TRAILING).addGroup(gl_panelSHS
								.createSequentialGroup()
								.addComponent(labelTime, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
								.addComponent(timeSpinner, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE).addGroup(
										gl_panelSHS.createSequentialGroup()
												.addGroup(gl_panelSHS.createParallelGroup(Alignment.TRAILING)
														.addComponent(comboBoxDeleteUser, 0, 148, Short.MAX_VALUE)
														.addComponent(enterNewUsername, GroupLayout.DEFAULT_SIZE, 148,
																Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING, false)
														.addComponent(newUser, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(deleteUserButton, GroupLayout.DEFAULT_SIZE, 136,
																Short.MAX_VALUE))))
						.addGap(17))
						.addGroup(Alignment.TRAILING, gl_panelSHS.createSequentialGroup()
								.addComponent(pressbuttonEditContext).addContainerGap()))));
		gl_panelSHS.setVerticalGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING).addGroup(gl_panelSHS
				.createSequentialGroup().addGap(9)
				.addGroup(gl_panelSHS.createParallelGroup(Alignment.BASELINE)
						.addComponent(enterNewUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(newUser, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelSHS.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxDeleteUser, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteUserButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addComponent(
						dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(gl_panelSHS.createParallelGroup(Alignment.BASELINE).addComponent(labelTime).addComponent(
						timeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 303, Short.MAX_VALUE).addComponent(pressbuttonEditContext)
				.addContainerGap()));
		panelSHS.setLayout(gl_panelSHS);

		/** SHC PANEL **/
		JPanel panelSHC = new JPanel();
		tabbedPane.addTab("SHC", null, panelSHC, null);

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

		GroupLayout gl_panelProfile = new GroupLayout(panelProfile);
		gl_panelProfile.setHorizontalGroup(gl_panelProfile.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelProfile.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
								.addComponent(togglebuttonSimulator, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
								.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 398, Short.MAX_VALUE)
								.addComponent(panelHouseInfo, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
								.addComponent(panelOutsideInfo, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(gl_panelProfile.createSequentialGroup().addGap(153)
						.addComponent(labelProfileImage, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE).addGap(148)));
		gl_panelProfile.setVerticalGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfile.createSequentialGroup().addContainerGap().addComponent(togglebuttonSimulator)
						.addGap(7).addComponent(labelProfileImage, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelHouseInfo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelOutsideInfo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addGap(282)));

		/** House Temperature **/
		JLabel labelHouseTemp = new JLabel("House Temp.");
		JLabel labelHouseTempValue = new JLabel("\u00B0C");
		houseTemp = new JTextField(5);

		GroupLayout gl_panelHouseInfo = new GroupLayout(panelHouseInfo);
		gl_panelHouseInfo
				.setHorizontalGroup(gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHouseInfo.createSequentialGroup().addGap(10).addComponent(labelHouseTemp)
								.addGap(21).addComponent(houseTemp, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(5).addComponent(labelHouseTempValue)));
		gl_panelHouseInfo.setVerticalGroup(gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup().addGap(8).addComponent(labelHouseTemp))
				.addGroup(gl_panelHouseInfo.createSequentialGroup().addGap(5).addComponent(houseTemp,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelHouseInfo.createSequentialGroup().addGap(8).addComponent(labelHouseTempValue)));
		panelHouseInfo.setLayout(gl_panelHouseInfo);

		/** Weather Label **/
		JLabel labelWeather = new JLabel("Weather:");

		/** Weather ComboBox Option **/
		JComboBox comboBoxWeather = new JComboBox();
		comboBoxWeather.setModel(new DefaultComboBoxModel(new String[] { "Cloudy", "Rainy", "Sunny", "Windy", "Snowy" }));

		/** Outdoor Temperature Label **/
		JLabel labelOutsideTemp = new JLabel("Outside Temp.");
		JLabel labelOutsideTempValue = new JLabel("\u00B0C");
		outsideTemp = new JTextField(5);

		GroupLayout gl_panelOutsideInfo = new GroupLayout(panelOutsideInfo);
		gl_panelOutsideInfo
				.setHorizontalGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOutsideInfo.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
										.addComponent(labelWeather, GroupLayout.PREFERRED_SIZE, 74,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelOutsideTemp))
								.addGap(10)
								.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelOutsideInfo.createSequentialGroup()
												.addComponent(outsideTemp, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(labelOutsideTempValue, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(comboBoxWeather, GroupLayout.PREFERRED_SIZE, 152,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(11, Short.MAX_VALUE)));
		gl_panelOutsideInfo.setVerticalGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup().addContainerGap(13, Short.MAX_VALUE)
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelOutsideInfo.createSequentialGroup().addGap(3)
										.addComponent(labelWeather))
								.addComponent(comboBoxWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(labelOutsideTemp)
								.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.BASELINE)
										.addComponent(labelOutsideTempValue).addComponent(outsideTemp,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
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
							.addGap(6)
							.addComponent(userLocationLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
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
							.addComponent(labelBoxLocation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(userLocationLabel)
								.addComponent(labelLocation))))
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
}