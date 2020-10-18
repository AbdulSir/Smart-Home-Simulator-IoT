package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

public class SimulationButton {

	private JToggleButton simulationButton;
	private Console console;

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

				if (state == ItemEvent.SELECTED)
					console.msg("Simulator ON");
				else if (state == ItemEvent.DESELECTED)
					console.msg("Simulator OFF");

			}
		});

	}

}