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

import controller.HouseLayout;
import controller.ReadingJsonFile;
import controller.Users;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ContextSimulation extends JFrame {

	private JPanel contentPane;

	final Users users = new Users();
	private JButton BlockWindows;
	private JButton setLocation;
	private JCheckBox BedroomCheckBox;
	private JCheckBox BathroomCheckBox;
	private JCheckBox GarageCheckBox;
	private JCheckBox KitchenCheckBox;
	private JCheckBox LivingRoomCheckBox;
	private JCheckBox MasterBedroomCheckBox;
	private HouseLayout panelHouse;
	private ReadingJsonFile rjFile;
	private JComboBox comboBoxUsers;
	private JComboBox comboBoxLocation;

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
		setBounds(250, 250, 306, 539);

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
		panelWindows.setBounds(5, 188, 179, 301);
		panelWindows.setBackground(Color.WHITE);

		JLabel labelWindows = new JLabel("Block Windows");

		/** Update Windows **/
		BlockWindows = new JButton("Update");

		/** Radio Button **/
		BedroomCheckBox = new JCheckBox("BedRM");
		BathroomCheckBox = new JCheckBox("BathRM");
		GarageCheckBox = new JCheckBox("Garage");
		KitchenCheckBox = new JCheckBox("Kitchen");
		LivingRoomCheckBox = new JCheckBox("Living RM");
		MasterBedroomCheckBox = new JCheckBox("Master BedRM");
		GroupLayout gl_panelWindows = new GroupLayout(panelWindows);
		gl_panelWindows.setHorizontalGroup(gl_panelWindows.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWindows.createSequentialGroup().addGap(20)
						.addComponent(BathroomCheckBox, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE).addGap(185))
				.addGroup(gl_panelWindows.createSequentialGroup().addGap(20)
						.addComponent(BedroomCheckBox, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE).addGap(190))
				.addGroup(gl_panelWindows.createSequentialGroup().addGap(20)
						.addComponent(GarageCheckBox, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE).addGap(202))
				.addGroup(gl_panelWindows.createSequentialGroup().addGap(20)
						.addComponent(KitchenCheckBox, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE).addGap(198))
				.addGroup(gl_panelWindows.createSequentialGroup().addGap(20)
						.addComponent(LivingRoomCheckBox, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE).addGap(167))
				.addGroup(gl_panelWindows.createSequentialGroup().addContainerGap()
						.addComponent(labelWindows, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE).addGap(193))
				.addGroup(gl_panelWindows.createSequentialGroup().addGap(20)
						.addGroup(gl_panelWindows.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelWindows.createSequentialGroup()
										.addComponent(BlockWindows, GroupLayout.PREFERRED_SIZE, 144,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(gl_panelWindows.createSequentialGroup().addComponent(MasterBedroomCheckBox,
										GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE).addGap(144)))));
		gl_panelWindows.setVerticalGroup(gl_panelWindows.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWindows.createSequentialGroup().addGap(12)
						.addComponent(labelWindows, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE).addGap(18)
						.addComponent(BathroomCheckBox, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE).addGap(12)
						.addComponent(BedroomCheckBox, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE).addGap(12)
						.addComponent(GarageCheckBox, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE).addGap(12)
						.addComponent(KitchenCheckBox, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE).addGap(12)
						.addComponent(LivingRoomCheckBox, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE).addGap(12)
						.addComponent(MasterBedroomCheckBox, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE).addGap(18)
						.addComponent(BlockWindows, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE).addGap(25)));
		panelWindows.setLayout(gl_panelWindows);
		contentPane.setLayout(null);
		contentPane.add(panelLocation);
		contentPane.add(panelWindows);
	}

	public JCheckBox getGarageCheckBox() {
		return GarageCheckBox;
	}

	public void setGarageCheckBox(JCheckBox garageCheckBox) {
		GarageCheckBox = garageCheckBox;
	}

	/**
	 * Getter
	 */
	public JButton getBlockWindows() {
		return BlockWindows;
	}

	public void setBlockWindows(JButton blockWindows) {
		BlockWindows = blockWindows;
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
	public JCheckBox getBedroomCheckBox() {
		return BedroomCheckBox;
	}

	/**
	 * Setter
	 */
	public void setBedroomCheckBox(JCheckBox bedroomCheckBox) {
		BedroomCheckBox = bedroomCheckBox;
	}

	/**
	 * Getter
	 */
	public JCheckBox getBathroomCheckBox() {
		return BathroomCheckBox;
	}

	/**
	 * Setter
	 */
	public void setBathroomCheckBox(JCheckBox bathroomCheckBox) {
		BathroomCheckBox = bathroomCheckBox;
	}

	/**
	 * Getter
	 */
	public JCheckBox getKitchenCheckBox() {
		return KitchenCheckBox;
	}

	/**
	 * Setter
	 */
	public void setKitchenCheckBox(JCheckBox kitchenCheckBox) {
		KitchenCheckBox = kitchenCheckBox;
	}

	/**
	 * Getter
	 */
	public JCheckBox getLivingRoomCheckBox() {
		return LivingRoomCheckBox;
	}

	/**
	 * Setter
	 */
	public void setLivingRoomCheckBox(JCheckBox livingRoomCheckBox) {
		LivingRoomCheckBox = livingRoomCheckBox;
	}

	/**
	 * Getter
	 */
	public JCheckBox getMasterBedroomCheckBox() {
		return MasterBedroomCheckBox;
	}

	/**
	 * Setter
	 */
	public void setMasterBedroomCheckBox(JCheckBox masterBedroomCheckBox) {
		MasterBedroomCheckBox = masterBedroomCheckBox;
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

}