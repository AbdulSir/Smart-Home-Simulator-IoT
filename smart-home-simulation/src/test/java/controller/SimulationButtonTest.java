package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimulationButtonTest {
	//Use Case ID 5
	@Test
	public void test() {
		SimulationButton simulate = SimulationButton.getSimulatorButton();
		assertEquals(false, simulate.isSimulatorState());
		simulate.setSimulatorState(true);
		assertEquals(true, simulate.isSimulatorState());
		
	}

}
