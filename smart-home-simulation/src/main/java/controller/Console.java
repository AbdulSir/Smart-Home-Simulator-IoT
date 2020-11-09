package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

import view.SHSGui;

public class Console {

	private JTextArea textAreaConsoleLog;
	private static Console console;

	/**
	 * Constructor
	 * 
	 * @param textAreaConsoleLog console log text area
	 */
	private Console(JTextArea textAreaConsoleLog) {
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

	public static Console getConsole() {
		if (console != null)
			return console;
		else {
			Console.console = new Console(SHSGui.getShs().getTextAreaConsoleLog());
			return console;
		}
	}
}
