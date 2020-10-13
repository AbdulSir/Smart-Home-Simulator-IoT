package windowBuilder.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import windowBuilder.views.ProfileSelection;
import windowBuilder.views.SHSGui;

public class SHSController {
	private SHSGui frame;
	private ProfileSelection profileselection;

	public SHSController(SHSGui frame) {
		// main ui
		this.frame = frame;
		createEvents();

		// control console
		Console console = new Console(frame.getTextAreaConsoleLog());
		console.msg("Welcome to the Smart Home Simulator");

		// selection profile ui
		profileselection = new ProfileSelection(frame);
		this.profileselection = profileselection;
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {
		this.frame.getLabelProfileImage().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (profileselection.isVisible()) {
					System.out.println("already open!");
				} else {
					profileselection.setVisible(true);
				}
			}
		});
	}
}
