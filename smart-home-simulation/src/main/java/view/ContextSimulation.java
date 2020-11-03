package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;

import model.Users;
import model.HouseLayout;
import model.ReadingJsonFile;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ContextSimulation extends JFrame {

	private JPanel contentPane;

	final Users users = new Users();
	private JButton setLocation;
	private HouseLayout panelHouse;
	private ReadingJsonFile rjFile;
	private JComboBox comboBoxUsers;
	private JComboBox comboBoxLocation;
	private JButton BlockButton;
	private JComboBox comboBoxWindowLocation;

	/**
	 * Launch the application.
	 */
	public void NewScreen(final ContextSimulation cs) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ContextSimulation() {
		/** Window Title **/
		setTitle("Edit Context of Simulation");

		/** Window Size **/
		setBounds(250, 250, 306, 398);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/** Location Panel **/
		JPanel panelLocation = new JPanel();
		panelLocation.setBounds(5, 11, 252, 166);
		panelLocation.setBackground(Color.WHITE);

		/** User Location Panel **/
		JPanel panelUserLocation = new JPanel();
		JLabel labelUsers = new JLabel("User:");
		comboBoxUsers = new JComboBox();

		/** Label Location **/
		JLabel labelLocation = new JLabel("Location:");
		comboBoxLocation = new JComboBox();		

		/** Set Location Button **/
		setLocation = new JButton("Set Location");

		GroupLayout gl_panelUserLocation = new GroupLayout(panelUserLocation);
		gl_panelUserLocation.setHorizontalGroup(gl_panelUserLocation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUserLocation.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelUserLocation.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING,
										gl_panelUserLocation.createSequentialGroup().addComponent(labelUsers)
												.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
												.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, 152,
														GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING,
										gl_panelUserLocation.createSequentialGroup().addComponent(labelLocation)
												.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
												.addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE, 152,
														GroupLayout.PREFERRED_SIZE))
								.addComponent(setLocation))
						.addContainerGap()));
		gl_panelUserLocation.setVerticalGroup(gl_panelUserLocation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUserLocation.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelUserLocation.createParallelGroup(Alignment.BASELINE).addComponent(labelUsers)
								.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelUserLocation.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelLocation).addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(setLocation)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelUserLocation.setLayout(gl_panelUserLocation);

		JLabel labelUserLocation = new JLabel("Set Location of Users");

		GroupLayout gl_panelLocation = new GroupLayout(panelLocation);
		gl_panelLocation.setHorizontalGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelLocation.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelLocation.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelUserLocation, GroupLayout.PREFERRED_SIZE, 233,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelUserLocation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelLocation
				.setVerticalGroup(gl_panelLocation.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelLocation.createSequentialGroup().addContainerGap()
								.addComponent(labelUserLocation, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelUserLocation,
										GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		panelLocation.setLayout(gl_panelLocation);

		/** Window Panel **/
		JPanel panelWindows = new JPanel();
		panelWindows.setBounds(5, 188, 252, 143);
		panelWindows.setBackground(Color.WHITE);

		JLabel labelWindows = new JLabel("Block Windows");
		labelWindows.setBounds(6, 12, 94, 16);
		
		JPanel panelBlockWindows = new JPanel();
		panelBlockWindows.setBounds(16, 40, 230, 90);
		panelBlockWindows.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(panelLocation);
		contentPane.add(panelWindows);
		panelWindows.setLayout(null);
		panelWindows.add(panelBlockWindows);
		
		JLabel labelLocation_1 = new JLabel("Location:");
		labelLocation_1.setBounds(6, 27, 58, 16);
		panelBlockWindows.add(labelLocation_1);
		
		comboBoxWindowLocation = new JComboBox();
		comboBoxWindowLocation.setBounds(72, 23, 152, 27);
		panelBlockWindows.add(comboBoxWindowLocation);
		
		BlockButton = new JButton("Block/Unblock");
		BlockButton.setBounds(16, 55, 137, 29);
		panelBlockWindows.add(BlockButton);
		panelWindows.add(labelWindows);
	}

	/**
	 * Getter
	 */
	public JButton getSetLocation() {
		return setLocation;
	}

	public void setSetLocation(JButton setLocation) {
		this.setLocation = setLocation;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxUsers() {
		return comboBoxUsers;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxLocation() {
		return comboBoxLocation;
	}

	/**
	 * Getter
	 */
	public JPanel getPanelHouse() {
		return panelHouse;
	}

	/**
	 * Setter
	 */
	public void setPanelHouse(HouseLayout panelHouse) {
		this.panelHouse = panelHouse;
	}

	/**
	 * Getter
	 */
	public JButton getBlockButton() {
		return BlockButton;
	}

	/**
	 * Setter
	 */
	public void setBlockButton(JButton blockButton) {
		BlockButton = blockButton;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxWindowLocation() {
		return comboBoxWindowLocation;
	}

	/**
	 * Setter
	 */
	public void setComboBoxWindowLocation(JComboBox comboBoxWindowLocation) {
		this.comboBoxWindowLocation = comboBoxWindowLocation;
	}
}