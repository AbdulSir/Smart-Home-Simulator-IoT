package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class Console {

	private JTextArea textAreaConsoleLog;

	/**
	 * Constructor
	 * 
	 * @param textAreaConsoleLog console log text area
	 */
	public Console(JTextArea textAreaConsoleLog) {
		this.textAreaConsoleLog = textAreaConsoleLog;
	}

	/**
	 * Display message in the Console
	 * 
	 * @param msg
	 */
	public void msg(String msg) {
		// get date-time
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		// display in console log
		this.textAreaConsoleLog.append("[" + formatter.format(date) + "] " + msg + "\n");
	}
}