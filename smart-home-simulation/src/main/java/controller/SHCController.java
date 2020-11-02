package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Doors;
import model.Lights;
import model.Users;
import view.SHSGui;

public class SHCController {
	private SHSGui frame;
	private Users user;
	private Console console;

	public SHCController() {
	}

	public SHCController(SHSGui frame, Console console) {
		/** Main GUI **/
		this.frame = frame;
		this.console = console;
		user = new Users();

		// User Event Handler
		userEvents();
	}

	private void userEvents() {
		frame.getOpenDoorsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doors doors = new Doors();
				String location = frame.getDoorsComboBox().getSelectedItem().toString();
				int index = frame.getDoorsComboBox().getSelectedIndex();
				if (!doors.getDoorList().get(index).isOpen()) {
					doors.getDoorList().get(index).setOpen(true);
					frame.repaint();
					console.msg("The door in the " + location + " is open");
				} else {
					doors.getDoorList().get(index).setOpen(false);
					frame.repaint();
					console.msg("The door in the " + location + " is closed");
				}

			}
		});

		frame.getLightsButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lights lights = new Lights();
				String location = frame.getLightsComboBox().getSelectedItem().toString();
				int index = frame.getLightsComboBox().getSelectedIndex();
				if (!lights.getLightsList().get(index).areLightsOn()) {
					lights.getLightsList().get(index).setLights(true);
					frame.repaint();
					console.msg("The light in the " + location + " is on");
				} else {
					lights.getLightsList().get(index).setLights(false);
					frame.repaint();
					console.msg("The light in the " + location + " is off");
				}
			}

		});
	}
}
