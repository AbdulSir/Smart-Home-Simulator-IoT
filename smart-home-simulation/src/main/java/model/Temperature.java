/**
 * 
 * Temperature Class holds data about the temperature
 *
 */

package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import controller.Console;
import controller.SHSController;
import controller.SimulationButton;
import view.SHSGui;

public class Temperature {

	private static double outsideTemp;
	private static double insideTemp;
	private Console console;
	private SHSGui frame;
	private static Temperature temperature;
	private SHSController controller;

	/**
	 * Constructor
	 */
	public Temperature() {
	}

	/**
	 * Parametrized Constructor
	 */

	public Temperature(SHSGui frame, final JTextField out, final JTextField in, final Console console,
			SHSController controller) {

		this.frame = frame;
		this.console = console;
		this.controller = controller;

		out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setOutsideTemperatureFunction(out, frame, console, controller);
			}
		});

		in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setInsideTemperatureFunction(in, frame, console, controller);
			}
		});
	}

	/**
	 * set temperature outside method in constructor
	 */
	public void setOutsideTemperatureFunction(JTextField out, SHSGui frame, Console console, SHSController controller) {
		// get text value
		String outsideTempStrValue = out.getText();
		// change str to int
		int outsideTempIntValue = Integer.parseInt(outsideTempStrValue);
		// call set method
		setOutsideTemp(outsideTempIntValue);
		frame.getOutdoorTemperatureValue().setText(outsideTempStrValue + "\u00B0C");
		console.msg("The temperature for the outside of the house has been set at " + outsideTemp + "\u00B0C");
		controller
				.appendToLog("The temperature for the outside of the house has been set at " + outsideTemp + "\u00B0C");
	}

	/**
	 * set temperature inside method in constructor
	 */
	public void setInsideTemperatureFunction(JTextField in, SHSGui frame, Console console, SHSController controller) {
		// get text value
		String insideTempStrValue = in.getText();
		// change str to int
		int insideTempIntValue = Integer.parseInt(insideTempStrValue);
		// call set method
		setInsideTemp(insideTempIntValue);
		frame.getIndoorHouseTempValue().setText(insideTempStrValue + "\u00B0C");
		console.msg("The temperature for the inside of the house has been set at " + insideTemp + "\u00B0C");
		controller.appendToLog("The temperature for the inside of the house has been set at " + insideTemp + "\u00B0C");
	}

	/**
	 * Getter
	 */
	public static double getOutsideTemp() {
		return outsideTemp;
	}

	/**
	 * Setter
	 */
	public void setOutsideTemp(double x) {
		this.outsideTemp = x;
	}

	/**
	 * Getter
	 */
	public static double getInsideTemp() {
		return insideTemp;
	}

	/**
	 * Setter
	 */
	public void setInsideTemp(double x) {
		this.insideTemp = x;
	}

	public static Temperature getTemperature() {
		if (temperature != null)
			return temperature;
		else {
			Temperature.temperature = new Temperature();
			return temperature;
		}
	}
}