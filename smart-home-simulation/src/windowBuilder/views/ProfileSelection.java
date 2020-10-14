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
	private JLabel labelProfileGuest;

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

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 208);
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

		labelProfileGuest = new JLabel("");
		labelProfileGuest
				.setIcon(new ImageIcon(ProfileSelection.class.getResource("/windowBuilder/resources/default.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
						.addComponent(labelProfileFather, GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE).addGap(28)
						.addComponent(labelProfileMother, GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE).addGap(18)
						.addComponent(labelProfileSon, GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE).addGap(18)
						.addComponent(labelProfileDaughter, GroupLayout.PREFERRED_SIZE, 134, Short.MAX_VALUE).addGap(18)
						.addComponent(labelProfileGuest, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE).addGap(8)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(11)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelProfileFather, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
								.addComponent(labelProfileMother, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
								.addComponent(labelProfileSon, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
								.addComponent(labelProfileDaughter, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
								.addComponent(labelProfileGuest, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE))));
		contentPane.setLayout(gl_contentPane);

	}

	private void createEvents() {
		/* Father Image OnClick */
		labelProfileFather.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/father.jpg");
				else
					System.out.println("No frame");
			}
		});

		/* Mother Image OnClick */
		labelProfileMother.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/mother.png");
				else
					System.out.println("No frame");
			}
		});

		/* Son Image OnClick */
		labelProfileSon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/son.png");
				else
					System.out.println("No frame");
			}
		});

		/* Daughter Image OnClick */
		labelProfileDaughter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/daughter.png");
				else
					System.out.println("No frame");
			}
		});

		/* Guest Image OnClick */
		labelProfileGuest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (frame != null)
					frame.setLabelProfileImage("/windowBuilder/resources/default.png");
				else
					System.out.println("No frame");
			}
		});
	}
}
