package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controller.PermissionsTest;
import controller.SHCTest;
import controller.SHPTest;
import controller.SimulationButtonTest;

@RunWith(Suite.class)
@SuiteClasses({ LogInTest.class, ManageUserTest.class, MoveLoggedUser.class, PlacePeopleTest.class,
		ProfileFileTest.class, ReadingJsonFileTest.class, TemperatureTest.class, TimeSpeedTest.class, TimeTest.class,
		WindowsTest.class, PermissionsTest.class, SHCTest.class, SHPTest.class, SimulationButtonTest.class })
public class AllTests {

}
