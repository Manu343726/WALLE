package tp.pr4.testprofesor;

import tp.pr4.Direction;
import tp.pr4.Place;
import tp.pr4.Street;

public class MockStreet extends Street {

	private boolean mockOpen;
	
	public MockStreet(Place source, Direction direction, Place target) {
		super(source, direction, target);
		mockOpen = true;
	}
	
	public boolean isOpen(){
		return mockOpen;
	}
	
	public void toggleOpen() {
		mockOpen = !mockOpen;
	}

}
