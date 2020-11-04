package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import model.Users;
import view.SHSGui;

public class SHPController {
	private SHSGui frame;
	private Console console;
	private Boolean awayMode;

	public SHPController() {
	}

	public SHPController(SHSGui frame) {
		/** Main GUI **/
		this.frame = frame;
		awayMode = false;
		/** Control Console **/
		this.console = new Console(frame.getTextAreaConsoleLog());

		userEvents();
	}

	private void userEvents() {
		JButton AwayModeBtn = this.frame.getAwayModeBtn();
		AwayModeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (awayMode == false) {
					awayMode = true;
					console.msg("Away mode on");
				}
				else if (awayMode == true) {
					awayMode = false;
					console.msg("Away mode off");
				}
			}
		});
	}
}