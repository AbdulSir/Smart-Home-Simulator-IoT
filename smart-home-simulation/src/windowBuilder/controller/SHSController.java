package windowBuilder.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import windowBuilder.views.ProfileSelection;
import windowBuilder.views.SHSGui;

public class SHSController {
	private SHSGui frame;
	private ProfileSelection profileselection;
	private Console console;

	public SHSController(SHSGui frame) {
		// main ui
		this.frame = frame;
		createEvents();

		// control console
		this.console = new Console(frame.getTextAreaConsoleLog());
		console.msg("Welcome to the Smart Home Simulator");

		// selection profile ui
		profileselection = new ProfileSelection(frame);
		this.profileselection = profileselection;
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {
		// Clicking Pr0ofile Picture
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

		// Toggle Button State Change
		this.frame.getTogglebuttonSimulator().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int state = arg0.getStateChange();

				if (state == ItemEvent.SELECTED)
					console.msg("Simulator ON");
				else if (state == ItemEvent.DESELECTED)
					console.msg("Simulator OFF");
			}
		});
	}
}
