package controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LogInTest.class, ManageUserTest.class, MoveLoggedUser.class, PlacePeopleTest.class,
		ReadingJsonFileTest.class, SimulationButtonTest.class, TemperatureTest.class, TimeTest.class,
		WindowsTest.class })
public class AllTests {

}
