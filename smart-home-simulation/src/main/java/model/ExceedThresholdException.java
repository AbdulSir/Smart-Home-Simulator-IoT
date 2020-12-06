package model;

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
	}
	
	/**
	 * Constructor
	 */
	public ExceedThresholdException() {
		super("Pipes about to burst!");
	}
	
}
