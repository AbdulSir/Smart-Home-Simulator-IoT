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
import model.Users;
import model.ReadingJsonFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;

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
	private JMenuItem mntmSave;
	private JMenuItem mntmLoad;
	private JComboBox comboBoxPermission;
	private JLabel labelUserPermissionValue;

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
		setBounds(100, 100, 1255, 792);

		/** Menu Bar **/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// File Section
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		// Open Option Under File
		mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/open_layout.png")));
		mnFile.add(mntmOpen);

		// Save Option Under File
		mntmSave = new JMenuItem("Save");

		mntmSave.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/save.png")));
		mnFile.add(mntmSave);

		// Load Option Under File
		mntmLoad = new JMenuItem("Load");

		mntmLoad.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/load.png")));
		mnFile.add(mntmLoad);

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
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(123, Short.MAX_VALUE)));
		gl_panelControl.setVerticalGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE).addContainerGap()));

		/** SHS PANEL **/

		/** Add User **/

		/** Delete User **/

		/* Calendar **/

		/** SHC PANEL **/
		JPanel panelSHS = new JPanel();
		tabbedPane.addTab("SHS", null, panelSHS, null);
		pressbuttonEditContext = new JButton("Edit Context Of Simulator");

		JPanel panelUser = new JPanel();
		enterNewUsername = new JTextField();
		enterNewUsername.setColumns(10);
		newUser = new JButton("Add User");
		newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBoxDeleteUser = new JComboBox();
		deleteUserButton = new JButton("Delete User");
		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		comboBoxPermission = new JComboBox();
		comboBoxPermission
				.setModel(new DefaultComboBoxModel(new String[] { "PARENT", "CHILDREN", "GUEST", "STRANGER" }));

		JLabel labelUserSetting = new JLabel("USER SETTING");
		GroupLayout gl_panelUser = new GroupLayout(panelUser);
		gl_panelUser.setHorizontalGroup(gl_panelUser.createParallelGroup(Alignment.LEADING).addGroup(gl_panelUser
				.createSequentialGroup()
				.addGroup(gl_panelUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelUser.createSequentialGroup().addGap(10).addGroup(gl_panelUser
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelUser.createSequentialGroup()
										.addComponent(enterNewUsername, GroupLayout.PREFERRED_SIZE, 117,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBoxPermission, 0, 151, Short.MAX_VALUE))
								.addGroup(gl_panelUser.createSequentialGroup()
										.addComponent(comboBoxDeleteUser, GroupLayout.PREFERRED_SIZE, 142,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(deleteUserButton,
												GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))))
						.addGroup(gl_panelUser.createSequentialGroup().addContainerGap().addComponent(newUser,
								GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
						.addGroup(gl_panelUser.createSequentialGroup().addContainerGap().addComponent(labelUserSetting,
								GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panelUser.setVerticalGroup(gl_panelUser.createParallelGroup(Alignment.LEADING).addGroup(gl_panelUser
				.createSequentialGroup()
				.addComponent(labelUserSetting, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(enterNewUsername, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxPermission, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(newUser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panelUser.createParallelGroup(Alignment.LEADING).addComponent(deleteUserButton)
						.addComponent(comboBoxDeleteUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		panelUser.setLayout(gl_panelUser);

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_panelSHS = new GroupLayout(panelSHS);
		gl_panelSHS.setHorizontalGroup(
			gl_panelSHS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSHS.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING)
						.addComponent(panelUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pressbuttonEditContext, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(panel_2, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelSHS.setVerticalGroup(
			gl_panelSHS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSHS.createSequentialGroup()
					.addGap(10)
					.addComponent(panelUser, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addComponent(pressbuttonEditContext, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		JLabel labelTemperatureSetting = new JLabel("TEMPERATURE SETTING");
		JLabel labelOutsideTemp = new JLabel("Outside Temp :");
		outsideTemp = new JTextField(5);
		JLabel labelOutsideTempValue = new JLabel("\u00B0C");
		JLabel labelWeather = new JLabel("Weather:");
		comboBoxWeather = new JComboBox();
		comboBoxWeather
				.setModel(new DefaultComboBoxModel(new String[] { "Cloudy", "Rainy", "Sunny", "Windy", "Snowy" }));
		JLabel labelHouseTemp = new JLabel("House Temp :");
		houseTemp = new JTextField(5);
		JLabel labelHouseTempValue = new JLabel("\u00B0C");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelTemperatureSetting, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2
								.createParallelGroup(Alignment.LEADING)
								.addComponent(labelWeather, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
								.addComponent(labelOutsideTemp, GroupLayout.PREFERRED_SIZE, 142,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(labelHouseTemp, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
												.addComponent(houseTemp, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(labelHouseTempValue))
										.addComponent(comboBoxWeather, Alignment.TRAILING, 0, 140, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
												.addComponent(outsideTemp, GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(labelOutsideTempValue)))))
				.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(labelTemperatureSetting).addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(labelOutsideTempValue)
						.addComponent(outsideTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelOutsideTemp))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(labelWeather).addComponent(
						comboBoxWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(labelHouseTemp)
						.addComponent(labelHouseTempValue).addComponent(houseTemp, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		panel_2.setLayout(gl_panel_2);

		JLabel labelDateAndTimeSetting = new JLabel("DATE AND TIME SETTING");
		dateChooser = new JDateChooser();
		JLabel labelTime = new JLabel("Time: ");
		timeSpinner = new JSpinner();
		timeSpinner.setModel(new SpinnerDateModel());
		timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));
		presstimebtn = new JButton("Set new Date & Time");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_panel_1.createSequentialGroup().addContainerGap().addComponent(dateChooser,
												GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING,
										gl_panel_1.createSequentialGroup().addContainerGap()
												.addComponent(labelTime, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(timeSpinner,
														GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(
										labelDateAndTimeSetting, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup().addContainerGap()
										.addComponent(presstimebtn, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(6)
				.addComponent(labelDateAndTimeSetting, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(
						dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(labelTime).addComponent(
						timeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(presstimebtn).addGap(6)));
		panel_1.setLayout(gl_panel_1);
		panelSHS.setLayout(gl_panelSHS);
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

		JPanel panel = new JPanel();

		GroupLayout gl_panelProfile = new GroupLayout(panelProfile);
		gl_panelProfile.setHorizontalGroup(gl_panelProfile.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelProfile.createSequentialGroup().addGap(153)
						.addComponent(labelProfileImage, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE).addGap(148))
				.addGroup(Alignment.LEADING,
						gl_panelProfile.createSequentialGroup().addContainerGap()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.LEADING,
						gl_panelProfile.createSequentialGroup().addContainerGap()
								.addComponent(panelOutsideInfo, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(gl_panelProfile.createSequentialGroup().addContainerGap()
						.addComponent(panelHouseInfo, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE).addContainerGap())
				.addGroup(gl_panelProfile.createSequentialGroup().addContainerGap()
						.addComponent(togglebuttonSimulator, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(Alignment.LEADING,
						gl_panelProfile.createSequentialGroup().addContainerGap()
								.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 416, Short.MAX_VALUE)
								.addContainerGap()));
		gl_panelProfile.setVerticalGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfile.createSequentialGroup().addContainerGap().addComponent(togglebuttonSimulator)
						.addGap(7).addComponent(labelProfileImage, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelHouseInfo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelOutsideInfo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE).addGap(150)));
		panel.setLayout(null);

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setBounds(6, 6, 61, 16);
		panel.add(dateLabel);

		dateValue = new JLabel("N/A");
		dateValue.setBounds(204, 6, 202, 16);
		panel.add(dateValue);

		JLabel timeLabel = new JLabel("Time");
		timeLabel.setBounds(6, 34, 61, 16);
		panel.add(timeLabel);

		timeValue = new JLabel("N/A");
		timeValue.setBounds(204, 34, 202, 16);
		panel.add(timeValue);

		JSlider slider = new JSlider();
		slider.setBounds(6, 61, 400, 26);
		panel.add(slider);

		/** House Temperature **/

		JLabel houseTempLabel = new JLabel("House Temp.");

		indoorHouseTempValue = new JLabel("0");

		GroupLayout gl_panelHouseInfo = new GroupLayout(panelHouseInfo);
		gl_panelHouseInfo.setHorizontalGroup(gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup().addContainerGap().addComponent(houseTempLabel)
						.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
						.addComponent(indoorHouseTempValue, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGap(115)));
		gl_panelHouseInfo.setVerticalGroup(gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelHouseInfo.createParallelGroup(Alignment.BASELINE).addComponent(houseTempLabel)
								.addComponent(indoorHouseTempValue, GroupLayout.PREFERRED_SIZE, 16,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(15, Short.MAX_VALUE)));
		panelHouseInfo.setLayout(gl_panelHouseInfo);

		/** Weather Label **/
		JLabel outdoorTemperatureLabel = new JLabel("Outdoor Temp.");

		/** Weather ComboBox Option **/
		JLabel weatherLabel = new JLabel("Weather");

		/** Outdoor Temperature Label **/
		outdoorTemperatureValue = new JLabel("0");

		weatherValue = new JLabel("N/A");

		GroupLayout gl_panelOutsideInfo = new GroupLayout(panelOutsideInfo);
		gl_panelOutsideInfo.setHorizontalGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING, false)
								.addComponent(weatherLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(outdoorTemperatureLabel, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
						.addGap(18)
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(weatherValue, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(outdoorTemperatureValue, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelOutsideInfo.setVerticalGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(outdoorTemperatureLabel).addComponent(outdoorTemperatureValue,
										GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.BASELINE).addComponent(weatherLabel)
								.addComponent(weatherValue))
						.addContainerGap(12, Short.MAX_VALUE)));
		panelOutsideInfo.setLayout(gl_panelOutsideInfo);

		/** Users Location **/
		JLabel labelRole = new JLabel("User");
		JLabel labelLocation = new JLabel("Location:");

		/** Combo Box Role **/
		comboBoxRole = new JComboBox();

		/** Combo Box Location **/
		labelBoxLocation = new JLabel();

		userLocationLabel = new JLabel("N/A");

		JLabel labelPermission = new JLabel("Permission");

		labelUserPermissionValue = new JLabel("N/A");

		GroupLayout gl_panelProfileInfo = new GroupLayout(panelProfileInfo);
		gl_panelProfileInfo.setHorizontalGroup(
			gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addComponent(labelRole, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
							.addComponent(labelPermission, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addComponent(labelLocation, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(labelUserPermissionValue, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxRole, 0, 199, Short.MAX_VALUE))
							.addGap(18))
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addComponent(userLocationLabel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(labelBoxLocation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_panelProfileInfo.setVerticalGroup(
			gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelRole)
						.addComponent(comboBoxRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addGap(6)
							.addComponent(labelBoxLocation, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelPermission, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelUserPermissionValue))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelLocation)
								.addComponent(userLocationLabel))))
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
	public JMenuItem getMntmSave() {
		return mntmSave;
	}

	/**
	 * Setter
	 */
	public void setMntmSave(JMenuItem mntmSave) {
		this.mntmSave = mntmSave;
	}

	/**
	 * Getter
	 */
	public JMenuItem getMntmLoad() {
		return mntmLoad;
	}

	/**
	 * Setter
	 */
	public void setMntmLoad(JMenuItem mntmLoad) {
		this.mntmLoad = mntmLoad;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxPermission() {
		return comboBoxPermission;
	}

	/**
	 * Setter
	 */
	public void setComboBoxPermission(JComboBox comboBoxPermission) {
		this.comboBoxPermission = comboBoxPermission;
	}

	/**
	 * Getter
	 */
	public JLabel getLabelUserPermissionValue() {
		return labelUserPermissionValue;
	}

	/**
	 * Setter
	 */
	public void setLabelUserPermissionValue(JLabel labelUserPermissionValue) {
		this.labelUserPermissionValue = labelUserPermissionValue;
	}
}