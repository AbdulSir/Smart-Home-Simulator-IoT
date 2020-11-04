package controller;

public class SimulationButton {

	private boolean simulatorState;

	/**
	 * Constructor
	 */
	public SimulationButton() {
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

}
