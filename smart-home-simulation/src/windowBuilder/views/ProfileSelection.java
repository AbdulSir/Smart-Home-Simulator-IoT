package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfileSelection extends JFrame {

	private JPanel contentPane;
	private JLabel labelProfileFather;
	private JLabel labelProfileMother;
	private JLabel labelProfileSon;
	private JLabel labelProfileDaughter;
	private SHSGui frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileSelection frame = new ProfileSelection(null);
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
	public ProfileSelection(SHSGui frame) {
		this.frame = frame;
		initComponents();
		createEvents();
	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ProfileSelection.class.getResource("/windowBuilder/resources/shs_128.png")));
		setTitle("Profile Select");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		labelProfileFather = new JLabel("");
		labelProfileFather
				.setIcon(new ImageIcon(ProfileSelection.class.getResource("/windowBuilder/resources/father.jpg")));

		labelProfileMother = new JLabel("");
		labelProfileMother
				.setIcon(new ImageIcon(ProfileSelection.class.getResource("/windowBuilder/resources/mother.png")));

		labelProfileSon = new JLabel("");
		labelProfileSon.setIcon(new ImageIcon(ProfileSelection.class.getResource("/windowBuilder/resources/son.png")));

		labelProfileDaughter = new JLabel("");
		labelProfileDaughter
				.setIcon(new ImageIcon(ProfileSelection.class.getResource("/windowBuilder/resources/daughter.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(labelProfileFather, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(28)
						.addComponent(labelProfileMother, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(labelProfileSon, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(labelProfileDaughter, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(61, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelProfileDaughter, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelProfileMother, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelProfileFather, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelProfileSon, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(142, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

	}

	private void createEvents() {
		labelProfileFather.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/father.jpg");
				else
					System.out.println("No frame");
			}
		});

		labelProfileMother.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/mother.png");
				else
					System.out.println("No frame");
			}
		});

		labelProfileSon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/son.png");
				else
					System.out.println("No frame");
			}
		});

		labelProfileDaughter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/daughter.png");
				else
					System.out.println("No frame");
			}
		});
	}

}
