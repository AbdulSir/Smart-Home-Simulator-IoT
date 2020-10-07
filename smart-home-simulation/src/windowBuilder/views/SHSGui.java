package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class SHSGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SHSGui frame = new SHSGui();
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
		createEvents();
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelContainer = new JPanel();
		panelContainer.setBackground(Color.WHITE);
		
		JLabel labelRole = new JLabel("Role");
		
		JLabel labelLocation = new JLabel("Location:");
		
		JLabel labelLocationValue = new JLabel("location");
		
		JLabel labelTemperature = new JLabel("Outside Temp.");
		
		JLabel labelTemperaureValue = new JLabel("15C");
		
		JLabel labelImage = new JLabel("");
		
		JPanel panelControl = new JPanel();
		panelControl.setBackground(Color.WHITE);
		
		JPanel panelView = new JPanel();
		panelView.setBackground(Color.WHITE);
		
		JPanel panelConsole = new JPanel();
		panelConsole.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
							.addGap(5)
							.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
						.addComponent(panelConsole, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelConsole, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addGap(4))
				.addComponent(panelContainer, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
		);
		GroupLayout gl_panelView = new GroupLayout(panelView);
		gl_panelView.setHorizontalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGap(0, 359, Short.MAX_VALUE)
		);
		gl_panelView.setVerticalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGap(0, 406, Short.MAX_VALUE)
		);
		panelView.setLayout(gl_panelView);
		GroupLayout gl_panelConsole = new GroupLayout(panelConsole);
		gl_panelConsole.setHorizontalGroup(
			gl_panelConsole.createParallelGroup(Alignment.LEADING)
				.addGap(0, 596, Short.MAX_VALUE)
		);
		gl_panelConsole.setVerticalGroup(
			gl_panelConsole.createParallelGroup(Alignment.LEADING)
				.addGap(0, 121, Short.MAX_VALUE)
		);
		panelConsole.setLayout(gl_panelConsole);
		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGap(0, 232, Short.MAX_VALUE)
		);
		gl_panelControl.setVerticalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGap(0, 406, Short.MAX_VALUE)
		);
		panelControl.setLayout(gl_panelControl);
		GroupLayout gl_panelContainer = new GroupLayout(panelContainer);
		gl_panelContainer.setHorizontalGroup(
			gl_panelContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addGap(140)
					.addComponent(labelRole))
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addGap(100)
					.addComponent(labelLocation)
					.addGap(10)
					.addComponent(labelLocationValue))
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addGap(121)
					.addComponent(labelTemperature)
					.addGap(6)
					.addComponent(labelTemperaureValue, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_panelContainer.createSequentialGroup()
					.addContainerGap(64, Short.MAX_VALUE)
					.addComponent(labelImage, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(59))
		);
		gl_panelContainer.setVerticalGroup(
			gl_panelContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContainer.createSequentialGroup()
					.addGap(72)
					.addComponent(labelImage, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(labelRole)
					.addGap(18)
					.addGroup(gl_panelContainer.createParallelGroup(Alignment.LEADING)
						.addComponent(labelLocation)
						.addComponent(labelLocationValue))
					.addGap(40)
					.addGroup(gl_panelContainer.createParallelGroup(Alignment.LEADING)
						.addComponent(labelTemperature)
						.addComponent(labelTemperaureValue)))
		);
		panelContainer.setLayout(gl_panelContainer);
		contentPane.setLayout(gl_contentPane);
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {

	}
}
