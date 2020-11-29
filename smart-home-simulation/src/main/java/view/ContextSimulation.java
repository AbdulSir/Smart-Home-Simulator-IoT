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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContextSimulation extends JFrame {

	private JPanel contentPane;

	final Users users = Users.getUser();;
	private JButton setLocation;
	private HouseLayout panelHouse;
	private ReadingJsonFile rjFile;
	private JComboBox comboBoxUsers;
	private JComboBox comboBoxLocation;
	private JButton BlockButton;
	private JComboBox comboBoxWindowLocation;
	private JButton blockAllButton;
	private JButton unblockAllButton;
	private JButton sendUsersOutisdeBtn;
	private JComboBox comboBoxWinterStart;
	private JComboBox comboBoxWinterEnd;
	private JComboBox comboBoxSummerStart;
	private JComboBox comboBoxSummerEnd;
	private JButton setSeasonsBtn;

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
		setBounds(250, 250, 648, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/** Location Panel **/
		JPanel panelLocation = new JPanel();
		panelLocation.setBounds(5, 11, 252, 192);
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
		
		sendUsersOutisdeBtn = new JButton("Send Users Outside");


		GroupLayout gl_panelUserLocation = new GroupLayout(panelUserLocation);
		gl_panelUserLocation.setHorizontalGroup(
			gl_panelUserLocation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUserLocation.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelUserLocation.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelUserLocation.createSequentialGroup()
							.addComponent(labelUsers)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panelUserLocation.createSequentialGroup()
							.addComponent(labelLocation)
							.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
							.addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addComponent(setLocation)
						.addComponent(sendUsersOutisdeBtn))
					.addContainerGap())
		);
		gl_panelUserLocation.setVerticalGroup(
			gl_panelUserLocation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUserLocation.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelUserLocation.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelUsers)
						.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelUserLocation.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLocation)
						.addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(setLocation)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(sendUsersOutisdeBtn))
		);
		panelUserLocation.setLayout(gl_panelUserLocation);

		JLabel labelUserLocation = new JLabel("Set Location of Users");

		GroupLayout gl_panelLocation = new GroupLayout(panelLocation);
		gl_panelLocation.setHorizontalGroup(
			gl_panelLocation.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLocation.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelLocation.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelUserLocation, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelUserLocation, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelLocation.setVerticalGroup(
			gl_panelLocation.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLocation.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelUserLocation, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelUserLocation, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelLocation.setLayout(gl_panelLocation);

		/** Window Panel **/
		JPanel panelWindows = new JPanel();
		panelWindows.setBounds(5, 215, 252, 197);
		panelWindows.setBackground(Color.WHITE);

		JLabel labelWindows = new JLabel("Block Windows");
		labelWindows.setBounds(6, 12, 94, 16);
		
		JPanel panelBlockWindows = new JPanel();
		panelBlockWindows.setBounds(16, 40, 230, 151);
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
		
		blockAllButton = new JButton("Block ALL");
		blockAllButton.setBounds(16, 85, 105, 29);
		panelBlockWindows.add(blockAllButton);
		
		unblockAllButton = new JButton("Unblock ALL");
		unblockAllButton.setBounds(16, 115, 117, 29);
		panelBlockWindows.add(unblockAllButton);
		panelWindows.add(labelWindows);
		
		JPanel panelSeasons = new JPanel();
		panelSeasons.setLayout(null);
		panelSeasons.setBackground(Color.WHITE);
		panelSeasons.setBounds(269, 138, 326, 216);
		contentPane.add(panelSeasons);
		
		JPanel panelChangeSeasons = new JPanel();
		panelChangeSeasons.setLayout(null);
		panelChangeSeasons.setBounds(16, 40, 304, 170);
		panelSeasons.add(panelChangeSeasons);
		
		JLabel labelWinterStart = new JLabel("Winter (Start):");
		labelWinterStart.setBounds(6, 27, 101, 16);
		panelChangeSeasons.add(labelWinterStart);
		
		String[] monthsArray = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		comboBoxWinterStart = new JComboBox();
		comboBoxWinterStart.setBounds(119, 23, 152, 27);
		comboBoxWinterStart.setModel(new DefaultComboBoxModel(monthsArray));
		panelChangeSeasons.add(comboBoxWinterStart);
		
		JLabel lblWinterEnd = new JLabel("Winter (End):");
		lblWinterEnd.setBounds(6, 55, 101, 16);
		panelChangeSeasons.add(lblWinterEnd);
		
		comboBoxWinterEnd = new JComboBox();
		comboBoxWinterEnd.setBounds(119, 51, 152, 27);
		comboBoxWinterEnd.setModel(new DefaultComboBoxModel(monthsArray));
		panelChangeSeasons.add(comboBoxWinterEnd);
		
		JLabel lblSummerstart = new JLabel("Summer (Start):");
		lblSummerstart.setBounds(6, 83, 101, 16);
		panelChangeSeasons.add(lblSummerstart);
		
		comboBoxSummerStart = new JComboBox();
		comboBoxSummerStart.setBounds(119, 79, 152, 27);
		comboBoxSummerStart.setModel(new DefaultComboBoxModel(monthsArray));
		panelChangeSeasons.add(comboBoxSummerStart);
		
		JLabel lblSummerend = new JLabel("Summer (End):");
		lblSummerend.setBounds(6, 111, 101, 16);
		panelChangeSeasons.add(lblSummerend);
		
		comboBoxSummerEnd = new JComboBox();
		comboBoxSummerEnd.setBounds(119, 107, 152, 27);
		comboBoxSummerEnd.setModel(new DefaultComboBoxModel(monthsArray));
		panelChangeSeasons.add(comboBoxSummerEnd);
		
		setSeasonsBtn = new JButton("Set Seasons");
		setSeasonsBtn.setBounds(181, 135, 117, 29);
		panelChangeSeasons.add(setSeasonsBtn);
		
		JLabel labelWindows_1 = new JLabel("Set Winter/Summer Months");
		labelWindows_1.setBounds(6, 12, 183, 16);
		panelSeasons.add(labelWindows_1);
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

	/**
	 * Getter
	 */
	public JButton getBlockAllButton() {
		return blockAllButton;
	}

	/**
	 * Setter
	 */
	public void setBlockAllButton(JButton blockAllButton) {
		this.blockAllButton = blockAllButton;
	}

	/**
	 * Getter
	 */
	public JButton getUnblockAllButton() {
		return unblockAllButton;
	}

	/**
	 * Setter
	 */
	public void setUnblockAllButton(JButton unblockAllButton) {
		this.unblockAllButton = unblockAllButton;
	}

	/**
	 * Getter
	 */
	public JButton getSendUsersOutisdeBtn() {
		return sendUsersOutisdeBtn;
	}

	/**
	 * Setter
	 */
	public void setSendUsersOutisdeBtn(JButton sendUsersOutisdeBtn) {
		this.sendUsersOutisdeBtn = sendUsersOutisdeBtn;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxWinterStart() {
		return comboBoxWinterStart;
	}

	/**
	 * Setter
	 */
	public void setComboBoxWinterStart(JComboBox comboBoxWinterStart) {
		this.comboBoxWinterStart = comboBoxWinterStart;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxWinterEnd() {
		return comboBoxWinterEnd;
	}

	/**
	 * Setter
	 */
	public void setComboBoxWinterEnd(JComboBox comboBoxWinterEnd) {
		this.comboBoxWinterEnd = comboBoxWinterEnd;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxSummerStart() {
		return comboBoxSummerStart;
	}

	/**
	 * Setter
	 */
	public void setComboBoxSummerStart(JComboBox comboBoxSummerStart) {
		this.comboBoxSummerStart = comboBoxSummerStart;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxSummerEnd() {
		return comboBoxSummerEnd;
	}

	/**
	 * Setter
	 */
	public void setComboBoxSummerEnd(JComboBox comboBoxSummerEnd) {
		this.comboBoxSummerEnd = comboBoxSummerEnd;
	}

	/**
	 * Getter
	 */
	public JButton getSetSeasonsBtn() {
		return setSeasonsBtn;
	}

	/**
	 * Setter
	 */
	public void setSetSeasonsBtn(JButton setSeasonsBtn) {
		this.setSeasonsBtn = setSeasonsBtn;
	}
	
}