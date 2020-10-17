/**
 * 
 * Temperature Class holds data about the temperature
 *
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;


public class Temperature {
	
	private int outsideTemp;
	private int insideTemp;

	
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
	
	public Temperature(final JTextField out, final JTextField in) {
		
		out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//get text value
				String outsideTempStrValue = out.getText();
				//change str to int
				int outsideTempIntValue = Integer.parseInt(outsideTempStrValue);
				//call set method
				setOutsideTemp(outsideTempIntValue);
			}
		});
		in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//get text value
				String insideTempStrValue = in.getText();
				System.out.println(insideTempStrValue);
				//change str to int
				int insideTempIntValue = Integer.parseInt(insideTempStrValue);
				//call set method
				setInsideTemp(insideTempIntValue);			
			}
		});
	}
	

	public void temperatureBalance() {
		//SHH shall shut down air conditioning and open windows if temperature outside is cooler than the inside temperature
		if(outsideTemp < insideTemp) {
			
		}
		
	}
		

	

		
	
}
