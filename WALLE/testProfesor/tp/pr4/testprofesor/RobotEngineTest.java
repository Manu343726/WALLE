package tp.pr4.testprofesor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.City;
import tp.pr4.Direction;
import tp.pr4.Place;
import tp.pr4.RobotEngine;
import tp.pr4.Street;

public class RobotEngineTest {

	private RobotEngine testEngine;
	private Street testStreet;
	
	@Before
	public void setUp() {
		Place source = new MockPlace();
		Place target = new MockPlace();
		testStreet = new Street(source, Direction.NORTH, target);
		Street [] streets = {testStreet};
		testEngine = new RobotEngine(new City(streets), source, Direction.NORTH);
	}
	
	@Test
	public void testRobotEngineInitialConfiguration() {
		assertEquals("ERROR: Robot engine must start with 100 fuel units", 100, testEngine.getFuel());
		assertEquals("ERROR: Robot engine must start with 0 recycled material", 0, testEngine.getRecycledMaterial());
	}
	
	@Test
	public void testAddFuel() {
		int newFuel = 10;
		int currentFuel = testEngine.getFuel();
		testEngine.addFuel(newFuel);
		assertEquals("ERROR: addFuel is not working properly", currentFuel + newFuel, testEngine.getFuel());
		
		newFuel = -10;
		currentFuel = testEngine.getFuel();
		testEngine.addFuel(newFuel);
		assertEquals("ERROR: addFuel is not working properly with negative fuel", currentFuel + newFuel, testEngine.getFuel());		
	}

	@Test
	public void testAddRecycledMaterial() {
		int newMaterial = 10;
		int currentMaterial = testEngine.getRecycledMaterial();
		testEngine.addRecycledMaterial(newMaterial);
		assertEquals("ERROR: addRecycledMaterial is not working properly", currentMaterial + newMaterial, testEngine.getRecycledMaterial());
	}

}
