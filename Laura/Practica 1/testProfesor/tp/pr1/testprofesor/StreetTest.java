package tp.pr1.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr1.Direction;
import tp.pr1.Place;
import tp.pr1.Street;


public class StreetTest {
	private Street testStreet;
	private Place targetPlace;
	private Place sourcePlace;

	@Before
	public void setUp() throws Exception {
		
		targetPlace = new Place ("Target Place", false, "Target place desc");
		sourcePlace = new Place ("Source Place", false, "Source place desc");
		testStreet = new Street(sourcePlace, Direction.NORTH, targetPlace);
	}

	@Test
	public void testNextPlace() {
		assertEquals("ERROR: nextPlace(sourcePlace) does not return the correct place", targetPlace, testStreet.nextPlace(sourcePlace));
		assertEquals("ERROR: nextPlace(targetPlace) does not return the correct place", sourcePlace, testStreet.nextPlace(targetPlace));
	}
	
	@Test
	public void testNextPlaceWrongPlace() {
		Place ghostPlace = new Place("", false, "");
		assertNull("ERROR: nextPlace(ghostPlace) does not return null", testStreet.nextPlace(ghostPlace));
	}

	@Test
	public void testComeOutFromWithSourcePlace() {
		assertTrue("ERROR: comeOutFrom(sourcePlace, Direction.NORTH) does not return true with correct direction", testStreet.comeOutFrom(sourcePlace, Direction.NORTH));
		assertFalse("ERROR: comeOutFrom(sourcePlace, Direction.EAST) does not return false with a wrong direction", testStreet.comeOutFrom(sourcePlace, Direction.EAST));
	}
	
	@Test
	public void testComeOutFromWithTargetPlace() {
		assertTrue("ERROR: comeOutFrom(targetPlace, Direction.SOUTH) does not return true with correct direction", testStreet.comeOutFrom(targetPlace, Direction.SOUTH));
		assertFalse("ERROR: comeOutFrom(targetPlace, Direction.NORTH) does not return false with a wrong direction", testStreet.comeOutFrom(targetPlace, Direction.NORTH));
	}
	
	@Test
	public void testComeOutFromWithWrongPlace() {
		Place ghostPlace = new Place("", false, "");
		assertFalse("ERROR: comeOutFrom(ghostPlace, Direction.NORTH) does not return false with a wrong place", testStreet.comeOutFrom(ghostPlace, Direction.NORTH));
		assertFalse("ERROR: comeOutFrom(ghostPlace, Direction.SOUTH) does not return false with a wrong place", testStreet.comeOutFrom(ghostPlace, Direction.SOUTH));
	}
}
