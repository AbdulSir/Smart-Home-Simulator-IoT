package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

import model.Users;
import view.ContextSimulation;
import view.SHSGui;

public class SHPController {
	private SHSGui frame;
	private Console console;
	private Boolean awayMode;
	private Users user;
	private ContextSimulation context;

	public SHPController() {
	}

	public SHPController(SHSGui frame) {
		/** Main GUI **/
		this.frame = frame;
		awayMode = false;
		/** Control Console **/
		this.console = new Console(frame.getTextAreaConsoleLog());
		this.context = new ContextSimulation();
		userEvents();
	}

	private void userEvents() {
		/** awayModeBtn event **/
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

	public Boolean getAwayMode() {
		return awayMode;
	}

	public void setAwayMode(Boolean awayMode) {
		this.awayMode = awayMode;
	}
}