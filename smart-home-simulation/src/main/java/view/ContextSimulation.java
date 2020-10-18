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

import controller.Users;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ContextSimulation extends JFrame {

	private JPanel contentPane;
	final Users users = new Users();
	private JButton BlockWindows;
	private JButton setLocation;
	private final JComboBox comboBoxUsers;
	private final JComboBox comboBoxLocation;
	private JRadioButton BedroomRadioButton;
	private JRadioButton BathroomRadioButton;
	private JRadioButton GarageRadioButton;
	private JRadioButton KitchenRadioButton;
	private JRadioButton LivingRoomRadioButton;
	private JRadioButton MasterBedroomRadioButton;

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
		setTitle("Edit Context of Simulation");
		setBounds(250, 250, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelLocation = new JPanel();
		panelLocation.setBounds(55, 10, 384, 284);
		panelLocation.setBackground(Color.WHITE);
		contentPane.add(panelLocation);

		JPanel panelProfileInfo_1 = new JPanel();

		JLabel labelUsers = new JLabel("User:");
		comboBoxUsers = new JComboBox();
		PopupMenuListener userListListener = new PopupMenuListener() {
			boolean initialized = false;

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if (!initialized) {
					String[] userNameArray = new String[users.getUserList().size()];
					for (int i = 0; i < userNameArray.length; i++) {
						userNameArray[i] = users.getUserList().get(i).getName();
					}
					comboBoxUsers.setModel(new DefaultComboBoxModel(userNameArray));
				}
			}
		};
		comboBoxUsers.addPopupMenuListener(userListListener);

		JLabel labelLocation = new JLabel("Location:");
		comboBoxLocation = new JComboBox();
		comboBoxLocation.setModel(new DefaultComboBoxModel(new String[] { "Bedroom", "Master Bedroom", "Kitchen",
				"Bathroom", "Living Room", "Garage", "Hallway", "Outside" }));

		setLocation = new JButton("Set Location");

		GroupLayout gl_panelProfileInfo_1 = new GroupLayout(panelProfileInfo_1);
		gl_panelProfileInfo_1.setHorizontalGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo_1.createSequentialGroup().addGroup(gl_panelProfileInfo_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo_1.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panelProfileInfo_1.createSequentialGroup().addComponent(labelUsers)
												.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
												.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, 152,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelProfileInfo_1.createSequentialGroup()
												.addComponent(labelLocation)
												.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
												.addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE, 152,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelProfileInfo_1.createSequentialGroup().addGap(77).addComponent(setLocation)))
						.addContainerGap()));
		gl_panelProfileInfo_1.setVerticalGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.BASELINE).addComponent(labelUsers)
								.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelLocation).addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(setLocation)));
		panelProfileInfo_1.setLayout(gl_panelProfileInfo_1);

		JLabel lblNewLabel = new JLabel("Set Location of Users");

		GroupLayout gl_panelLocation = new GroupLayout(panelLocation);
		gl_panelLocation.setHorizontalGroup(gl_panelLocation.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLocation.createSequentialGroup().addGap(123).addComponent(lblNewLabel)
						.addContainerGap(128, Short.MAX_VALUE))
				.addGroup(gl_panelLocation.createSequentialGroup().addContainerGap(58, Short.MAX_VALUE)
						.addComponent(panelProfileInfo_1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
						.addGap(54)));
		gl_panelLocation
				.setVerticalGroup(
						gl_panelLocation.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelLocation.createSequentialGroup().addContainerGap()
										.addComponent(lblNewLabel).addGap(73).addComponent(panelProfileInfo_1,
												GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(80, Short.MAX_VALUE)));
		panelLocation.setLayout(gl_panelLocation);

		JPanel panelHouse = new JPanel();
		panelHouse.setBounds(485, 10, 728, 662);
		panelHouse.setBackground(Color.WHITE);
		contentPane.add(panelHouse);
		panelHouse.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("House Layout");
		lblNewLabel_2_1.setBounds(300, 6, 86, 16);
		panelHouse.add(lblNewLabel_2_1);

		JPanel panelWindows = new JPanel();
		panelWindows.setBounds(55, 306, 384, 366);
		panelWindows.setBackground(Color.WHITE);
		contentPane.add(panelWindows);

		JLabel lblWindows = new JLabel("Block Windows");
		lblWindows.setBounds(150, 6, 94, 16);
		BlockWindows = new JButton("Update");
		BlockWindows.setBounds(150, 296, 88, 29);

		BedroomRadioButton = new JRadioButton("Bedroom");
		BedroomRadioButton.setBounds(20, 81, 87, 23);
		BathroomRadioButton = new JRadioButton("Bathroom");
		BathroomRadioButton.setBounds(20, 46, 92, 23);
		GarageRadioButton = new JRadioButton("Garage");
		GarageRadioButton.setBounds(20, 116, 75, 23);
		KitchenRadioButton = new JRadioButton("Kitchen");
		KitchenRadioButton.setBounds(20, 151, 79, 23);
		LivingRoomRadioButton = new JRadioButton("Living Room");
		LivingRoomRadioButton.setBounds(20, 186, 110, 23);
		MasterBedroomRadioButton = new JRadioButton("Master Bedroom");
		MasterBedroomRadioButton.setBounds(20, 221, 133, 23);
		panelWindows.setLayout(null);
		panelWindows.add(lblWindows);
		panelWindows.add(GarageRadioButton);
		panelWindows.add(KitchenRadioButton);
		panelWindows.add(LivingRoomRadioButton);
		panelWindows.add(MasterBedroomRadioButton);
		panelWindows.add(BedroomRadioButton);
		panelWindows.add(BathroomRadioButton);
		panelWindows.add(BlockWindows);
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
	public JComboBox getComboBoxLocation() {
		return comboBoxLocation;
	}

	/**
	 * Getter
	 */
	public JRadioButton getBedroomRadioButton() {
		return BedroomRadioButton;
	}

	/**
	 * Setter
	 */
	public void setBedroomRadioButton(JRadioButton bedroomRadioButton) {
		BedroomRadioButton = bedroomRadioButton;
	}

	/**
	 * Getter
	 */
	public JRadioButton getBathroomRadioButton() {
		return BathroomRadioButton;
	}

	/**
	 * Setter
	 */
	public void setBathroomRadioButton(JRadioButton bathroomRadioButton) {
		BathroomRadioButton = bathroomRadioButton;
	}

	/**
	 * Getter
	 */
	public JRadioButton getGarageRadioButton() {
		return GarageRadioButton;
	}

	/**
	 * Setter
	 */
	public void setGarageRadioButton(JRadioButton garageRadioButton) {
		GarageRadioButton = garageRadioButton;
	}

	/**
	 * Getter
	 */
	public JRadioButton getKitchenRadioButton() {
		return KitchenRadioButton;
	}

	/**
	 * Setter
	 */
	public void setKitchenRadioButton(JRadioButton kitchenRadioButton) {
		KitchenRadioButton = kitchenRadioButton;
	}

	/**
	 * Getter
	 */
	public JRadioButton getLivingRoomRadioButton() {
		return LivingRoomRadioButton;
	}

	public void setLivingRoomRadioButton(JRadioButton livingRoomRadioButton) {
		LivingRoomRadioButton = livingRoomRadioButton;
	}

	/**
	 * Getter
	 */
	public JRadioButton getMasterBedroomRadioButton() {
		return MasterBedroomRadioButton;
	}

	public void setMasterBedroomRadioButton(JRadioButton masterBedroomRadioButton) {
		MasterBedroomRadioButton = masterBedroomRadioButton;
	}
}
