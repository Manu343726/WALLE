//OK//

package tp.pr2;


/***
 * Represents a street as its starting place, its final place, and its direction.
 */
public class Street {
	
	private Place source;
	private Place target;
	private Direction direction;
	private boolean isOpen;
	private String code;
	
	/* CONSTRUCTORS */
	/**
	 * Default constructor
	 */
	public Street(){}
	
	/**
	 * Parameterized constructor
	 * @param source The starting place (See class description)
	 * @param direction The street direction (See class description)
	 * @param target The final place (See class description)
	 */	
	public Street(Place source, Direction direction, Place target){
		this.source = source;
		this.target = target;
		this.direction = direction;
		this.isOpen = true;
	}
	
	public Street(Place source, Direction direction, Place target, boolean isOpen, String code){
		this.source = source;
		this.target = target;
		this.direction = direction;
		this.isOpen = isOpen;
		this.code = code;
	}
	
	/* PUBLIC METHODS */
	/**
	 * Checks if this street matches to a certain place and direction
	 * @param place: The starting or final place of the street
	 * @param whichDirection: The street direction
	 * @return True if the values match with this street. False in other case.
	 */
	public boolean comeOutFrom(Place place, Direction whichDirection){
		return (place == this.source && whichDirection == this.direction) || (place == this.target && whichDirection == this.direction.opposite());
	}
	
	/**
	 * Returns the place on the other side of the street
	 * @param whereAmI: The starting or final place of the street
	 * @return the place on the other side of the street from the specified place
	 */
	public Place nextPlace(Place whereAmI){
		if (whereAmI == this.source)
			return this.target;
		else if (whereAmI == this.target)
			return this.source;
		else
			return null;
	}
	
	public boolean isOpen(){
		return this.isOpen;
	}
	
	public boolean open(CodeCard card){
		if(this.code == card.getCode())
			this.isOpen = true;
		return this.isOpen;
	}
	
	public boolean close(CodeCard card){
		if(this.code == card.getCode()){
			this.isOpen = false;
			
			return true;
		}
		else
			return false;
	}
	
	
	
}
