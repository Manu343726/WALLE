package tp.pr3.testprofesor;

import tp.pr3.City;
import tp.pr3.Direction;
import tp.pr3.Interpreter;
import tp.pr3.Place;
import tp.pr3.RobotEngine;
import tp.pr3.Street;

public class MockRobotEngine extends RobotEngine {
	
	private Street headingStreet;
	private boolean quit;
	private boolean help;

	public MockRobotEngine(City city, Place initialPlace, Direction dir) {
		super(city, initialPlace, dir);
		setQuit(false);
		setHelp(true);
	}
	 
	public MockRobotEngine(Street head) {
		super(new MockCity(), new MockPlace(), Direction.NORTH);
		headingStreet = head;
	}
	
	public Street getHeadingStreet() {
		return headingStreet;
	}
	

	public void requestQuit() {
		setQuit(true);
	}	
	
	public void requestHelp(){
		setHelp(true);
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	public boolean isQuit() {
		return quit;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}

	public boolean isHelp() {
		return help;
	}

}
