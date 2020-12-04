/**
 * 
 * Time Class holds data about the temperature
 *
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import controller.Console;

import controller.SHPController;

import controller.SHSController;

import controller.SimulationButton;
import view.SHSGui;

public class Time {

	private JSpinner time_spinner;
	private Console console;
	private Date date;
	private SHSGui frame;
	private JDateChooser dateChooser;
	private Timer t;
	private static Date time;
	private int increment_time_value;

	private static Time watch;

	private SHSController controller;

	/**
	 * Default constructor
	 */
	private Time() {
		date = null;
		dateChooser = null;
		t = null;
		time = null;
		increment_time_value = 0;
	}

	/**
	 * Constructor
	 */
	public Time(SHSGui frame, JButton btn, JSpinner time_spinner, JDateChooser date, JSlider slider, Console console,
			SHSController controller) {
		this.increment_time_value = 1;
		this.console = console;
		this.frame = frame;
		this.controller = controller;

		// listen for button click
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// set time
				setTimeFromSpinnner(time_spinner, frame);
				// set date
				setDateFromSpinnner(date, frame);
			}
		});

		// Slider Event Handler
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				setIncrement_time_value(slider.getValue());
			}
		});

		// constantly incrementing timer
		this.t = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// make sure there a value for time
				if (getTime() != null) {
					// display time
					displayTime(frame);
					// increment time
					incrementTime();
				}
			}
		});
	}

	/**
	 * Set Time from spinner
	 */
	public void setTimeFromSpinnner(JSpinner time_spinner, SHSGui frame) {
		Date value = (Date) time_spinner.getValue();
		String formattedValue = new SimpleDateFormat("HH:mm").format(value);
		frame.getTimeValue().setText(formattedValue);
		setTime(value);
		console.msg("The time has been set at " + formattedValue);
		controller.appendToLog("The time has been set at " + formattedValue);
	}

	/**
	 * Set date from spinner
	 */
	public void setDateFromSpinnner(JDateChooser date, SHSGui frame) {
		Date setDate = date.getDate();
		String strDate = DateFormat.getDateInstance().format(setDate);
		frame.getDateValue().setText(strDate);
		console.msg("The date has been set to " + strDate);
		controller.appendToLog("The date has been set to " + strDate);
	}
	
	/**
	 * Display Time
	 */
	public void displayTime(SHSGui frame) {
		String timeToString = new SimpleDateFormat("HH:mm").format(getTime());
		frame.getTimeValue().setText(timeToString);
	}

	/**
	 * Increment Time
	 */
	public void incrementTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getTime());
		calendar.add(Calendar.MINUTE, increment_time_value);
		setTime(calendar.getTime());
	}

	/**
	 * Start Timer
	 */
	public void startTimer() {
		this.t.start();
	}

	/**
	 * Stop Timer
	 */
	public void stopTimer() {
		this.t.stop();
	}

	/**
	 * Getter
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Setter
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Getter
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Setter
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Getter
	 */
	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	/**
	 * Setter
	 */
	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	/**
	 * Getter
	 */
	public int getIncrement_time_value() {
		return increment_time_value;
	}

	/**
	 * Setter
	 */
	public void setIncrement_time_value(int increment_time_value) {
		// at least 1
		if (increment_time_value <= 0)
			increment_time_value = 1;

		this.increment_time_value = increment_time_value;
	}

	public static Time getWatch() {
		if (watch != null)
			return watch;
		else {
			Time.watch = new Time();
			return watch;
		}
	}
}