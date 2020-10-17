package controller;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;
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
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelLocation = new JPanel();
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
					for(int i = 0; i < userNameArray.length; i++) {
						userNameArray[i] = users.getUserList().get(i).getName();
					}
					comboBoxUsers.setModel(new DefaultComboBoxModel(userNameArray));
	          }
	        }
	    };
	    comboBoxUsers.addPopupMenuListener(userListListener);
		
		JLabel labelLocation = new JLabel("Location:");
		comboBoxLocation = new JComboBox();
		comboBoxLocation.setModel(new DefaultComboBoxModel(new String[] {"Bedroom","Master Bedroom","Kitchen","Bathroom","Living Room","Garage","Hallway","Outside"}));
		
		setLocation = new JButton("Set Location");
		
		GroupLayout gl_panelProfileInfo_1 = new GroupLayout(panelProfileInfo_1);
		gl_panelProfileInfo_1.setHorizontalGroup(
			gl_panelProfileInfo_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo_1.createSequentialGroup()
					.addGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelProfileInfo_1.createSequentialGroup()
									.addComponent(labelUsers)
									.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
									.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelProfileInfo_1.createSequentialGroup()
									.addComponent(labelLocation)
									.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
									.addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panelProfileInfo_1.createSequentialGroup()
							.addGap(77)
							.addComponent(setLocation)))
					.addContainerGap())
		);
		gl_panelProfileInfo_1.setVerticalGroup(
			gl_panelProfileInfo_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelUsers)
						.addComponent(comboBoxUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProfileInfo_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLocation)
						.addComponent(comboBoxLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(setLocation))
		);
		panelProfileInfo_1.setLayout(gl_panelProfileInfo_1);
		
		JLabel lblNewLabel = new JLabel("Set Location of Users");
		
		GroupLayout gl_panelLocation = new GroupLayout(panelLocation);
		gl_panelLocation.setHorizontalGroup(
			gl_panelLocation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLocation.createSequentialGroup()
					.addGap(123)
					.addComponent(lblNewLabel)
					.addContainerGap(128, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panelLocation.createSequentialGroup()
					.addContainerGap(60, Short.MAX_VALUE)
					.addComponent(panelProfileInfo_1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
		);
		gl_panelLocation.setVerticalGroup(
			gl_panelLocation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLocation.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(74)
					.addComponent(panelProfileInfo_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(147, Short.MAX_VALUE))
		);
		panelLocation.setLayout(gl_panelLocation);
		
		JPanel panelHouse = new JPanel();
		panelHouse.setBackground(Color.WHITE);
		contentPane.add(panelHouse);
		
		JLabel lblNewLabel_2_1 = new JLabel("House Layout");
		GroupLayout gl_panelHouse = new GroupLayout(panelHouse);
		gl_panelHouse.setHorizontalGroup(
			gl_panelHouse.createParallelGroup(Alignment.LEADING)
				.addGap(0, 409, Short.MAX_VALUE)
				.addGroup(gl_panelHouse.createSequentialGroup()
					.addGap(157)
					.addComponent(lblNewLabel_2_1)
					.addContainerGap(166, Short.MAX_VALUE))
		);
		gl_panelHouse.setVerticalGroup(
			gl_panelHouse.createParallelGroup(Alignment.LEADING)
				.addGap(0, 352, Short.MAX_VALUE)
				.addGroup(gl_panelHouse.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2_1)
					.addContainerGap(330, Short.MAX_VALUE))
		);
		panelHouse.setLayout(gl_panelHouse);
		
		JPanel panelWindows = new JPanel();
		panelWindows.setBackground(Color.WHITE);
		contentPane.add(panelWindows);
		
		JLabel lblWindows = new JLabel("Block Windows");
		BlockWindows = new JButton("Update");
		
		BedroomRadioButton = new JRadioButton("Bedroom");
		BathroomRadioButton = new JRadioButton("Bathroom");
		GarageRadioButton = new JRadioButton("Garage");
		KitchenRadioButton = new JRadioButton("Kitchen");
		LivingRoomRadioButton = new JRadioButton("Living Room");
		MasterBedroomRadioButton = new JRadioButton("Master Bedroom");

		GroupLayout gl_panelWindows = new GroupLayout(panelWindows);
		gl_panelWindows.setHorizontalGroup(
			gl_panelWindows.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWindows.createSequentialGroup()
					.addGroup(gl_panelWindows.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelWindows.createSequentialGroup()
							.addGap(174)
							.addComponent(lblWindows))
						.addGroup(gl_panelWindows.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_panelWindows.createParallelGroup(Alignment.LEADING)
								.addComponent(GarageRadioButton)
								.addComponent(KitchenRadioButton)
								.addComponent(LivingRoomRadioButton)
								.addComponent(MasterBedroomRadioButton)
								.addComponent(BedroomRadioButton)
								.addComponent(BathroomRadioButton)))
						.addGroup(gl_panelWindows.createSequentialGroup()
							.addGap(169)
							.addComponent(BlockWindows)))
					.addContainerGap(159, Short.MAX_VALUE))
		);
		gl_panelWindows.setVerticalGroup(
			gl_panelWindows.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelWindows.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWindows)
					.addGap(38)
					.addComponent(BathroomRadioButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(BedroomRadioButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(GarageRadioButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(KitchenRadioButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(LivingRoomRadioButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MasterBedroomRadioButton)
					.addGap(27)
					.addComponent(BlockWindows)
					.addContainerGap(80, Short.MAX_VALUE))
		);
	}

	public JButton getBlockWindows() {
		return BlockWindows;
	}

	public void setBlockWindows(JButton blockWindows) {
		BlockWindows = blockWindows;
	}

	public JButton getSetLocation() {
		return setLocation;
	}

	public void setSetLocation(JButton setLocation) {
		this.setLocation = setLocation;
	}

	public JComboBox getComboBoxUsers() {
		return comboBoxUsers;
	}

	public JComboBox getComboBoxLocation() {
		return comboBoxLocation;
	}

	public JRadioButton getBedroomRadioButton() {
		return BedroomRadioButton;
	}

	public void setBedroomRadioButton(JRadioButton bedroomRadioButton) {
		BedroomRadioButton = bedroomRadioButton;
	}

	public JRadioButton getBathroomRadioButton() {
		return BathroomRadioButton;
	}

	public void setBathroomRadioButton(JRadioButton bathroomRadioButton) {
		BathroomRadioButton = bathroomRadioButton;
	}

	public JRadioButton getGarageRadioButton() {
		return GarageRadioButton;
	}

	public void setGarageRadioButton(JRadioButton garageRadioButton) {
		GarageRadioButton = garageRadioButton;
	}

	public JRadioButton getKitchenRadioButton() {
		return KitchenRadioButton;
	}

	public void setKitchenRadioButton(JRadioButton kitchenRadioButton) {
		KitchenRadioButton = kitchenRadioButton;
	}

	public JRadioButton getLivingRoomRadioButton() {
		return LivingRoomRadioButton;
	}

	public void setLivingRoomRadioButton(JRadioButton livingRoomRadioButton) {
		LivingRoomRadioButton = livingRoomRadioButton;
	}

	public JRadioButton getMasterBedroomRadioButton() {
		return MasterBedroomRadioButton;
	}

	public void setMasterBedroomRadioButton(JRadioButton masterBedroomRadioButton) {
		MasterBedroomRadioButton = masterBedroomRadioButton;
	}
}
