package controller;

import view.SHSGui;

public class SimulationButton {

	private boolean simulatorState;
	private static SimulationButton simulatorButton;

	/**
	 * Constructor
	 */
	private SimulationButton() {
		simulatorState = false;

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

	public static SimulationButton getSimulatorButton() {
		if (simulatorButton != null)
			return simulatorButton;
		else {
			SimulationButton.simulatorButton = new SimulationButton();
			return simulatorButton;
		}
	}

}
