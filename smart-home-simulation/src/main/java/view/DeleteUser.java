package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Users;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	private JTextField deleteUserInput;

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
				System.out.println("button clicked");
				String deleteUser = deleteUserInput.getText();
				System.out.println(deleteUser);
				Users delete = new Users();
				ArrayList<Users> userList = delete.getUserList();
				for (Users user: userList) {
					if(user.getName().equalsIgnoreCase(deleteUser)) {
						userList.remove(user);
						deleteUserInput.setText("User Deleted");
						break;						
					}
					else {
						deleteUserInput.setText("No User Found");
					}
				}
			}
		});
		contentPane.add(deleteUserButton, BorderLayout.SOUTH);
		
		deleteUserInput = new JTextField();
		deleteUserInput.setText("Enter UserName You Wish To Delete");
		contentPane.add(deleteUserInput, BorderLayout.CENTER);
		deleteUserInput.setColumns(10);
	}

}
