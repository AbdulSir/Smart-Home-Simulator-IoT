package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimulationButtonTest {
	//Use Case ID 5
	@Test
	public void test() {
		SimulationButton simulate = new SimulationButton();
		assertEquals(false, simulate.isSimulatorState());
		simulate.setSimulatorState(true);
		assertEquals(true, simulate.isSimulatorState());
		
	}

}
