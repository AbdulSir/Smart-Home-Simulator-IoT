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
import view.SHSGui;

public class Temperature {

	private int outsideTemp;
	private int insideTemp;
	private Console console;
	private SHSGui Frame;

	/**
	 * Constructor
	 */
	public Temperature() {
	}

	public Temperature(SHSGui frame, final JTextField out, final JTextField in, final Console console) {
		this.console = console;
		out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				// get text value
				String outsideTempStrValue = out.getText();
				// change str to int
				int outsideTempIntValue = Integer.parseInt(outsideTempStrValue);
				// call set method
				setOutsideTemp(outsideTempIntValue);
				frame.getOutdoorTemperatureValue().setText(outsideTempStrValue + "\u00B0C");
				frame.repaint();
				console.msg("The temperature for the outside of the house has been set at " + outsideTemp + "\u00B0C");
			}
		});

		in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// get text value
				String insideTempStrValue = in.getText();
				// change str to int
				int insideTempIntValue = Integer.parseInt(insideTempStrValue);
				// call set method
				setInsideTemp(insideTempIntValue);
				frame.getIndoorHouseTempValue().setText(insideTempStrValue + "\u00B0C");
				frame.repaint();
				console.msg("The temperature for the inside of the house has been set at " + insideTemp + "\u00B0C");
			}
		});
	}

	public int getOutsideTemp() {
		return outsideTemp;
	}

	public void setOutsideTemp(int x) {
		this.outsideTemp = x;
	}

	public int getInsideTemp() {
		return insideTemp;
	}

	public void setInsideTemp(int x) {
		this.insideTemp = x;
	}

}