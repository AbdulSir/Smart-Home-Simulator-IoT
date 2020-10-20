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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JSpinner;

import com.toedter.calendar.JDateChooser;

import controller.Console;
import view.SHSGui;

public class Time {

	private JSpinner time;
	private Console console;
	private Date date;
	private SHSGui frame;
	private JDateChooser dateChooser;

	/**
	 * Constructor
	 */
	public Time() {
	}

	public Time(SHSGui frame,final JButton btn, final JSpinner time, JDateChooser date, final Console console) {
		this.console = console;
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTime(time);
				Date value = (Date) time.getValue();
				String formattedValue = new SimpleDateFormat("HH:mm").format(value);
				frame.getTimeValue().setText(formattedValue);
				Date setDate = date.getDate();
//				int year = date.getCalendar().YEAR;
//				int month = date.getCalendar().MONTH;
//				int day = date.getCalendar().DATE;
//				String date1 = (day+"-"+month+"-"+year);
				String strDate = DateFormat.getDateInstance().format(setDate);
				frame.getDateValue().setText(strDate);
				frame.repaint();
				console.msg("The time has been set at " + formattedValue);
				console.msg("the date has been set to " + strDate);
			}
		});
	}

	public JSpinner getTime() {
		return time;
	}

	public void setTime(JSpinner time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

}
