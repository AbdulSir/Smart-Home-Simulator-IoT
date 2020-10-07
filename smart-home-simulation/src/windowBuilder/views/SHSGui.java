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

public class SHSGui extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=114,159
	 */
	private final TextField textField = new TextField();

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
		setBounds(100, 100, 846, 499);
		
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
		
		JPanel panelControl = new JPanel();
		panelControl.setBackground(Color.RED);
		
		JPanel panelView = new JPanel();
		panelView.setBackground(Color.WHITE);
		
		JPanel panelConsole = new JPanel();
		panelConsole.setBackground(Color.BLUE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelContainer, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
						.addComponent(panelConsole, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelContainer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
								.addComponent(panelControl, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelConsole, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		GroupLayout gl_panelView = new GroupLayout(panelView);
		gl_panelView.setHorizontalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGap(0, 241, Short.MAX_VALUE)
		);
		gl_panelView.setVerticalGroup(
			gl_panelView.createParallelGroup(Alignment.LEADING)
				.addGap(0, 428, Short.MAX_VALUE)
		);
		panelView.setLayout(gl_panelView);
		GroupLayout gl_panelContainer = new GroupLayout(panelContainer);
		gl_panelContainer.setHorizontalGroup(
			gl_panelContainer.createParallelGroup(Alignment.LEADING)
				.addGap(0, 263, Short.MAX_VALUE)
		);
		gl_panelContainer.setVerticalGroup(
			gl_panelContainer.createParallelGroup(Alignment.LEADING)
				.addGap(0, 428, Short.MAX_VALUE)
		);
		panelContainer.setLayout(gl_panelContainer);
		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGap(0, 284, Short.MAX_VALUE)
		);
		gl_panelControl.setVerticalGroup(
			gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGap(0, 428, Short.MAX_VALUE)
		);
		panelControl.setLayout(gl_panelControl);
		contentPane.setLayout(gl_contentPane);
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {

	}
}
