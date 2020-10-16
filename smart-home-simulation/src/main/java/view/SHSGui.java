package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SHSController;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;

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
import javax.swing.JSpinner;

public class SHSGui extends JFrame {
	private JLabel labelProfileImage;
	private JTextArea textAreaConsoleLog;
	private JToggleButton togglebuttonSimulator;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(SHSGui.class.getResource("/resources/shs_128.png")));
		initComponents();
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating and
	// initializing components.
	//////////////////////////////////////////////////////////////
	private void initComponents() {
		
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
		labelProfileImage.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/default.png")));

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

		JTabbedPane tabbedPaneSHS = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("SHS", null, tabbedPaneSHS, null);

		JTabbedPane tabbedPaneSHC = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("SHC", null, tabbedPaneSHC, null);

		JTabbedPane tabbedPaneSHP = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("SHP", null, tabbedPaneSHP, null);

		JTabbedPane tabbedPaneSHH = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("SHH", null, tabbedPaneSHH, null);

		JTabbedPane tabbedPaneAdd = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("+", null, tabbedPaneAdd, null);
		panelControl.setLayout(gl_panelControl);

		JPanel panelProfileInfo = new JPanel();

		togglebuttonSimulator = new JToggleButton("Simulator");

		JPanel panelHouseInfo = new JPanel();

		JPanel panelOutsideInfo = new JPanel();

		JPanel panelDateTime = new JPanel();

		GroupLayout gl_panelContainer = new GroupLayout(panelContainer);
		gl_panelContainer.setHorizontalGroup(gl_panelContainer.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContainer.createSequentialGroup().addGroup(gl_panelContainer
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContainer.createSequentialGroup().addContainerGap().addGroup(gl_panelContainer
								.createParallelGroup(Alignment.LEADING)
								.addComponent(togglebuttonSimulator, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 257, Short.MAX_VALUE)
								.addComponent(panelHouseInfo, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(panelOutsideInfo, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(panelDateTime, GroupLayout.PREFERRED_SIZE, 257,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelContainer.createSequentialGroup().addGap(79).addComponent(labelProfileImage,
								GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_panelContainer.setVerticalGroup(gl_panelContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContainer.createSequentialGroup().addContainerGap()
						.addComponent(togglebuttonSimulator).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(labelProfileImage, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelHouseInfo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelOutsideInfo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelDateTime, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JDateChooser dateChooser = new JDateChooser();

		/**
		 * Set and modify time
		 */
		JLabel labelTime = new JLabel("Time: ");
		JSpinner timeSpinner = new JSpinner();
        timeSpinner.setModel(new SpinnerDateModel());
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm:ss"));
		
		GroupLayout gl_panelDateTime = new GroupLayout(panelDateTime);
		gl_panelDateTime.setHorizontalGroup(
			gl_panelDateTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDateTime.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDateTime.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
						.addGroup(gl_panelDateTime.createSequentialGroup()
							.addComponent(labelTime, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(timeSpinner, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelDateTime.setVerticalGroup(
			gl_panelDateTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDateTime.createSequentialGroup()
					.addContainerGap()
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDateTime.createParallelGroup(Alignment.LEADING)
						.addComponent(labelTime)
						.addComponent(timeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panelDateTime.setLayout(gl_panelDateTime);

		JLabel labelHouseTemp = new JLabel("Outside Temp.");

		JLabel labelHouseTempValue = new JLabel("15C");
		GroupLayout gl_panelHouseInfo = new GroupLayout(panelHouseInfo);
		gl_panelHouseInfo.setHorizontalGroup(
			gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelHouseTemp, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelHouseTempValue, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelHouseInfo.setVerticalGroup(
			gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHouseInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelHouseTemp)
						.addComponent(labelHouseTempValue))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panelHouseInfo.setLayout(gl_panelHouseInfo);

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

		JLabel labelRole = new JLabel("Role");

		JLabel labelLocation = new JLabel("Location:");

		JComboBox comboBoxRole = new JComboBox();

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
}