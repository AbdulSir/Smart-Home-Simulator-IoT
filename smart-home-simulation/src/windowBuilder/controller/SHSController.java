package windowBuilder.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import windowBuilder.views.SHSGui;

public class SHSController {
	private SHSGui frame;

	public SHSController(SHSGui frame) {
		this.frame = frame;
		createEvents();
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {
		this.frame.getLabelProfileImage().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Profile");
				frame.setLabelProfileImage("/windowBuilder/resources/father.jpg");
			}
		});
	}
}
