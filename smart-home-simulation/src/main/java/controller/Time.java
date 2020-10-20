/**
 * 
 * Time Class holds data about the temperature
 *
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JSpinner;


public class Time {
	
	private JSpinner time;
	private Console console;
	
	/**
	 * Constructor
	 */
	public Time() {
	}
	
	public Time(final JButton btn, final JSpinner time, final Console console) {
		this.console = console;
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTime(time);
				Date value = (Date)time.getValue();
				String formattedValue = new SimpleDateFormat("HH:mm").format(value);
                console.msg("The temperature for the outside of the house has been set at " + formattedValue);
			}
		});
	}
	
	

	public JSpinner getTime() {
		return time;
	}

	public void setTime(JSpinner time) {
		this.time = time;
	}

	}

