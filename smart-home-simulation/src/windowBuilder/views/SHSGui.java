package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import windowBuilder.controller.SHSController;

import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import java.awt.TextArea;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;

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
		initComponents();
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating and
	// initializing components.
	//////////////////////////////////////////////////////////////
	private void initComponents() {
		setTitle("Smart Home Simulation");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(SHSGui.class.getResource("/windowBuilder/resources/shs_128.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 998, 626);

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
		labelProfileImage.setIcon(new ImageIcon(SHSGui.class.getResource("/windowBuilder/resources/default.png")));

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
				.addGap(5)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE).addGap(5)
								.addComponent(panelView, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelConsole, GroupLayout.PREFERRED_SIZE, 687, Short.MAX_VALUE))
				.addGap(8)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panelView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelConsole,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(panelContainer, GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE));
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
		gl_panelContainer.setHorizontalGroup(
			gl_panelContainer.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addGroup(gl_panelContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelContainer.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelDateTime, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelContainer.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelContainer.createParallelGroup(Alignment.LEADING)
								.addComponent(panelOutsideInfo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(panelHouseInfo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 257, Short.MAX_VALUE)))
						.addGroup(gl_panelContainer.createSequentialGroup()
							.addGap(79)
							.addComponent(labelProfileImage, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelContainer.createSequentialGroup()
							.addGap(94)
							.addComponent(togglebuttonSimulator)))
					.addContainerGap())
		);
		gl_panelContainer.setVerticalGroup(
			gl_panelContainer.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addComponent(togglebuttonSimulator)
					.addGap(18)
					.addComponent(labelProfileImage)
					.addGap(18)
					.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelHouseInfo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelOutsideInfo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelDateTime, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		
		JDateChooser dateChooser = new JDateChooser();
		GroupLayout gl_panelDateTime = new GroupLayout(panelDateTime);
		gl_panelDateTime.setHorizontalGroup(
			gl_panelDateTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDateTime.createSequentialGroup()
					.addContainerGap()
					.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelDateTime.setVerticalGroup(
			gl_panelDateTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDateTime.createSequentialGroup()
					.addContainerGap()
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		panelDateTime.setLayout(gl_panelDateTime);
		
		JLabel labelHouseTemp = new JLabel("Outside Temp.");
		
		JLabel labelHouseTempValue = new JLabel("15C");
		GroupLayout gl_panelHouseInfo = new GroupLayout(panelHouseInfo);
		gl_panelHouseInfo.setHorizontalGroup(
			gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelHouseTemp, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelHouseTempValue, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		gl_panelHouseInfo.setVerticalGroup(
			gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHouseInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelHouseTemp)
						.addComponent(labelHouseTempValue))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panelHouseInfo.setLayout(gl_panelHouseInfo);
		
				JLabel labelWeather = new JLabel("Weather:");
		
				JComboBox comboBoxWeather = new JComboBox();
				comboBoxWeather.setModel(new DefaultComboBoxModel(new String[] {"Cloudy", "Rainy", "Sunny", "Windy", "Snowy"}));
		
				JLabel labelOutsideTemp = new JLabel("Outside Temp.");
		
				JLabel labelOutsideTempValue = new JLabel("15C");
		GroupLayout gl_panelOutsideInfo = new GroupLayout(panelOutsideInfo);
		gl_panelOutsideInfo.setHorizontalGroup(
			gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOutsideInfo.createSequentialGroup()
							.addComponent(labelWeather, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(comboBoxWeather, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelOutsideInfo.createSequentialGroup()
							.addComponent(labelOutsideTemp)
							.addGap(10)
							.addComponent(labelOutsideTempValue)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelOutsideInfo.setVerticalGroup(
			gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup()
					.addContainerGap()
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOutsideInfo.createSequentialGroup()
							.addGap(3)
							.addComponent(labelWeather))
						.addComponent(comboBoxWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(labelOutsideTemp)
						.addComponent(labelOutsideTempValue))
					.addContainerGap())
		);
		panelOutsideInfo.setLayout(gl_panelOutsideInfo);

		JLabel labelRole = new JLabel("Role");

		JLabel labelLocation = new JLabel("Location:");

		JComboBox comboBoxRole = new JComboBox();

		JComboBox comboBoxLocation = new JComboBox();
		GroupLayout gl_panelProfileInfo = new GroupLayout(panelProfileInfo);
		gl_panelProfileInfo.setHorizontalGroup(
			gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addComponent(labelRole)
							.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
							.addComponent(comboBoxRole, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addComponent(labelLocation)
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelProfileInfo.setVerticalGroup(
			gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelRole)
						.addComponent(comboBoxRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLocation)
						.addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(64))
		);
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
