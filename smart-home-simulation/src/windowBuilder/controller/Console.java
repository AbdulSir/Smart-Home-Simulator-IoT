package windowBuilder.controller;

import javax.swing.JTextArea;

public class Console {

	private JTextArea textAreaConsoleLog;

	public Console(JTextArea textAreaConsoleLog) {
		this.textAreaConsoleLog = textAreaConsoleLog;
	}

	public void msg(String msg) {
		this.textAreaConsoleLog.append(msg);
	}
}
