package tp.pr1.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr1.Place;

public class PlaceTest {

	Place placeTest;
	private String placeName;
	private String placeDescription;

	@Before
	public void setUp() throws Exception {
		placeName = "place name";
		placeDescription = "place description";
		placeTest = new Place(placeName, false, placeDescription);   
	}

	@Test
	public void testIsSpaceship() {
		assertFalse("ERROR: We have created a place that does not represent a spaceship but isSpaceship returns true",placeTest.isSpaceship());
		// Change the room
		placeTest = new Place("", true, "");
		assertTrue("ERROR: We have created a place that represents a spaceship but isSpaceship returns false",placeTest.isSpaceship());
	}
	
	@Test
	public void testToString() {
		assertTrue("ERROR: the place name does not appear in the string", placeTest.toString().contains(placeName));
		assertTrue("ERROR: the place description does not appear in the string", placeTest.toString().contains(placeDescription));
	}
}
