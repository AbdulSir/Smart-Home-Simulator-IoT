package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

public class SimulationButton {

	private JToggleButton simulationButton;
	private Console console;
	private boolean simulatorState;
	
	
	/**
	 * constructor
	 */
	public SimulationButton() {
		simulatorState = false;
		
	}
	/**
	 * Constructor
	 * 
	 * @param simulationButton
	 * @param console
	 */
	public SimulationButton(JToggleButton simulationButton, Console console) {
		this.simulationButton = simulationButton;
		this.console = console;

		// event handler
		createEvents();
	}

	/**
	 * Handle all event with the simulation button
	 */
	private void createEvents() {

		// Toggle Button State Change
		simulationButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();

				if (state == ItemEvent.SELECTED) {
					setSimulatorState(true);
					console.msg("Simulator ON");
				} else if (state == ItemEvent.DESELECTED) {
					console.msg("Simulator OFF");
					setSimulatorState(false);
				}

			}
		});

	}
	/**
	 * 
	 * getter simulator state
	 */
	public boolean isSimulatorState() {
		return simulatorState;
	}
	/**
	 * 
	 * setter simulator state
	 */
	public void setSimulatorState(boolean simulatorState) {
		this.simulatorState = simulatorState;
	}

}
