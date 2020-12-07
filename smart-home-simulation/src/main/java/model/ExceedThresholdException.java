package model;

import javax.swing.JOptionPane;

/**
 * Exception class that handle potential burst to pipe if the temperature exceed threshold
 *
 */
@SuppressWarnings("serial")
public class ExceedThresholdException extends Exception{

	/**
	 * Constructor
	 * @param message error message
	 */
	public ExceedThresholdException(String message) {
		super(message);
		msgDialog();
	}
	
	/**
	 * Constructor
	 */
	public ExceedThresholdException() {
		super("Pipes about to burst!");
		msgDialog();
	}
	
	/**
	 * Dialog box
	 */
	public void msgDialog() {
        JOptionPane.showMessageDialog(null, "Pipes about to burst!", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
	}
}
