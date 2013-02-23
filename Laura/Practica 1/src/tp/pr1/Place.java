//OK//

package tp.pr1;

/***
 * Represents a place, with its name and description.  
 */
public class Place {
	
	private String name;
	private String description;
	private boolean isSpaceShip;
	
	
	/* CONSTRUCTORS */
	/**
	 * Default constructor
	 */
	public Place(){}
	
	/**
	 * Parameterized constructor
	 * @param name The place name
	 * @param isSpaceShip A value that sets if the spaceship is at this place
	 * @param description The place description
	 */
	public Place(String name, boolean isSpaceShip, String description){
		this.name = name;
		this.description = description;
		this.isSpaceShip = isSpaceShip;
	}
	
	
	/* PUBLIC METHODS */
	/**
	 * Check if the spaceship is at this place
	 * @return True if the spaceship is at this place. False in other case.
	 */
	public boolean isSpaceship(){
		return this.isSpaceShip;
	}

	/**
	 * Returns the place name and the place description
	 */
	public String toString(){
		return (this.name + Interpreter.LINE_SEPARATOR +  this.description);
	}
	

}
