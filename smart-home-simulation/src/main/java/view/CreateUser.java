package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.Users;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateUser extends JFrame {
	private JTextField enterNewUsername;
	private JPanel newUsernamePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser frame = new CreateUser();
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
	public CreateUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		newUsernamePanel = new JPanel();
		newUsernamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		newUsernamePanel.setLayout(new BorderLayout(0, 0));
		setContentPane(newUsernamePanel);
		
		enterNewUsername = new JTextField();
		getContentPane().add(enterNewUsername, BorderLayout.CENTER);
		enterNewUsername.setColumns(10);
		
		JLabel AddUser = new JLabel("Add New User");
		newUsernamePanel.add(AddUser, BorderLayout.NORTH);
		
		JButton newUser = new JButton("New button");
		newUser.addMouseListener(new MouseAdapter() {
			
			// new user button click event
			public void mouseClicked(MouseEvent e) {
				String NewUsername = enterNewUsername.getText();
				Users New = new Users(NewUsername);
				New.printUserList();
			}
		});
		newUsernamePanel.add(newUser, BorderLayout.SOUTH);
	}

}
