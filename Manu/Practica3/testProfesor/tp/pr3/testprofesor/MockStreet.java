package tp.pr3.testprofesor;

import tp.pr3.Direction;
import tp.pr3.Place;
import tp.pr3.Street;

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
