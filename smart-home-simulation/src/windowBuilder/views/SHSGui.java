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

public class SHSGui extends JFrame {
	private JLabel labelProfileImage;

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
		setBounds(100, 100, 969, 619);

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
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE).addGap(5)
								.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
						.addComponent(panelConsole, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelConsole, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE).addGap(4))
				.addComponent(panelContainer, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE));
		GroupLayout gl_panelView = new GroupLayout(panelView);
		gl_panelView.setHorizontalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGap(0, 422, Short.MAX_VALUE)
		);
		gl_panelView.setVerticalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGap(0, 418, Short.MAX_VALUE)
		);
		panelView.setLayout(gl_panelView);
		
		JTextArea textAreaConsoleLog = new JTextArea();
		textAreaConsoleLog.setEditable(false);
		
		JLabel labelConsoleLog = new JLabel("Console Log");
		GroupLayout gl_panelConsole = new GroupLayout(panelConsole);
		gl_panelConsole.setHorizontalGroup(
			gl_panelConsole.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConsole.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelConsole.createParallelGroup(Alignment.LEADING)
						.addComponent(labelConsoleLog)
						.addComponent(textAreaConsoleLog, GroupLayout.PREFERRED_SIZE, 641, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panelConsole.setVerticalGroup(
			gl_panelConsole.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConsole.createSequentialGroup()
					.addGap(14)
					.addComponent(labelConsoleLog)
					.addGap(6)
					.addComponent(textAreaConsoleLog, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
		);
		panelConsole.setLayout(gl_panelConsole);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelControl.setVerticalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addContainerGap())
		);
		
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
		
		JToggleButton togglebuttonSimulator = new JToggleButton("Simulator");
		GroupLayout gl_panelContainer = new GroupLayout(panelContainer);
		gl_panelContainer.setHorizontalGroup(
			gl_panelContainer.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addGroup(gl_panelContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContainer.createSequentialGroup()
							.addGap(71)
							.addComponent(labelProfileImage, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelContainer.createSequentialGroup()
							.addGap(91)
							.addComponent(togglebuttonSimulator))
						.addGroup(Alignment.TRAILING, gl_panelContainer.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 252, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelContainer.setVerticalGroup(
			gl_panelContainer.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addComponent(togglebuttonSimulator)
					.addGap(18)
					.addComponent(labelProfileImage)
					.addGap(55)
					.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGap(124))
		);
				GridBagLayout gbl_panelProfileInfo = new GridBagLayout();
				gbl_panelProfileInfo.columnWidths = new int[]{31, 0, 21, 44, 70, 19, 0};
				gbl_panelProfileInfo.rowHeights = new int[]{14, 0, 0, 0, 0};
				gbl_panelProfileInfo.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panelProfileInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panelProfileInfo.setLayout(gbl_panelProfileInfo);
										
												JLabel labelRole = new JLabel("Role");
												GridBagConstraints gbc_labelRole = new GridBagConstraints();
												gbc_labelRole.anchor = GridBagConstraints.NORTHWEST;
												gbc_labelRole.insets = new Insets(0, 0, 5, 5);
												gbc_labelRole.gridx = 1;
												gbc_labelRole.gridy = 0;
												panelProfileInfo.add(labelRole, gbc_labelRole);
												
														JLabel labelLocation = new JLabel("Location:");
														GridBagConstraints gbc_labelLocation = new GridBagConstraints();
														gbc_labelLocation.anchor = GridBagConstraints.NORTHWEST;
														gbc_labelLocation.insets = new Insets(0, 0, 5, 5);
														gbc_labelLocation.gridx = 1;
														gbc_labelLocation.gridy = 1;
														panelProfileInfo.add(labelLocation, gbc_labelLocation);
										
												JLabel labelTemperature = new JLabel("Outside Temp.");
												GridBagConstraints gbc_labelTemperature = new GridBagConstraints();
												gbc_labelTemperature.anchor = GridBagConstraints.NORTHWEST;
												gbc_labelTemperature.insets = new Insets(0, 0, 5, 5);
												gbc_labelTemperature.gridx = 1;
												gbc_labelTemperature.gridy = 2;
												panelProfileInfo.add(labelTemperature, gbc_labelTemperature);
								
										JLabel labelTemperaureValue = new JLabel("15C");
										GridBagConstraints gbc_labelTemperaureValue = new GridBagConstraints();
										gbc_labelTemperaureValue.insets = new Insets(0, 0, 5, 5);
										gbc_labelTemperaureValue.anchor = GridBagConstraints.NORTHWEST;
										gbc_labelTemperaureValue.gridx = 2;
										gbc_labelTemperaureValue.gridy = 2;
										panelProfileInfo.add(labelTemperaureValue, gbc_labelTemperaureValue);
						
								JLabel labelLocationValue = new JLabel("location");
								GridBagConstraints gbc_labelLocationValue = new GridBagConstraints();
								gbc_labelLocationValue.insets = new Insets(0, 0, 0, 5);
								gbc_labelLocationValue.gridx = 1;
								gbc_labelLocationValue.gridy = 3;
								panelProfileInfo.add(labelLocationValue, gbc_labelLocationValue);
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
}
