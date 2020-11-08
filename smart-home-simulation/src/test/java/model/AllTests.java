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
		ReadingJsonFileTest.class, SimulationButtonTest.class, TemperatureTest.class, TimeTest.class,
		WindowsTest.class,SHCTest.class,SHPTest.class, PermissionsTest.class })
public class AllTests {

}
