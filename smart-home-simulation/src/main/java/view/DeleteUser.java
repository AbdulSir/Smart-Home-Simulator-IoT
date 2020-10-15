package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.Users;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxDeleteUser;
	private JLabel deleteUserDropDownLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
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
	public DeleteUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton deleteUserButton = new JButton("Delete User");
		deleteUserButton.addMouseListener(new MouseAdapter() {
			// Delete User function
			public void mouseClicked(MouseEvent e) {
//				System.out.println("button clicked");
//				String deleteUser = deleteUserInput.getText();
//				System.out.println(deleteUser);
//				Users delete = new Users();
//				ArrayList<Users> userList = delete.getUserList();
//				for (Users user: userList) {
//					if(user.getName().equalsIgnoreCase(deleteUser)) {
//						userList.remove(user);
//						deleteUserInput.setText("User Deleted");
//						break;						
//					}
//					else {
//						deleteUserInput.setText("No User Found");
//					}
//				}
				String userToDelete = comboBoxDeleteUser.getSelectedItem().toString();
				Users delete = new Users();
				ArrayList<Users> userList = delete.getUserList();
				for (Users user: userList) {
					if(user.getName().equalsIgnoreCase(userToDelete)) {
						userList.remove(user);
					System.out.println("User Deleted");
						break;						
					}
					else {
						System.out.println("No User Found");
					}
				}
			}
		});
		contentPane.add(deleteUserButton, BorderLayout.SOUTH);
		final Users Admin = new Users();
		comboBoxDeleteUser = new JComboBox();
		// Will update User pop up menu every time the user opens the menu 
	    PopupMenuListener listener = new PopupMenuListener() {
	        boolean initialized = false;

	        public void popupMenuCanceled(PopupMenuEvent e) {
	        }

	        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	        }

	        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
	          if (!initialized) {
					String[] userNameArray = new String[Admin.getUserList().size()];
					for(int i = 0; i < userNameArray.length; i++) {
						userNameArray[i] = Admin.getUserList().get(i).getName();
					}
					comboBoxDeleteUser.setModel(new DefaultComboBoxModel(userNameArray));
	          }
	        }
	    };
		comboBoxDeleteUser.addPopupMenuListener(listener);
	
		contentPane.add(comboBoxDeleteUser, BorderLayout.CENTER);
		
		deleteUserDropDownLabel = new JLabel("Select User to Delete");
		contentPane.add(deleteUserDropDownLabel, BorderLayout.NORTH);
	}

}
