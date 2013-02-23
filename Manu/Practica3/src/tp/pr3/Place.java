//OK//

package tp.pr3;


/***
 * Represents a place, with its name and description.  
 */
public class Place {
	
	private String name;
	private String description;
	private boolean isSpaceShip;
	private ItemContainer itemContainer;
	private final static String PLACECONTAINS = "The place contains these objects: ";//Joder, si no es por kdiff no lo veo XD
	private final static String PLACEEMPTY = "The place is empty. There are no objects to pick";//Lo mismo que la anterior!
	
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
		this.itemContainer = new ItemContainer();
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
		if(this.itemContainer.numberOfItems() == 0)
			return (this.name + Interpreter.LINE_SEPARATOR +  this.description + Interpreter.LINE_SEPARATOR + PLACEEMPTY);
		else
		    return (this.name + Interpreter.LINE_SEPARATOR +  this.description + Interpreter.LINE_SEPARATOR + PLACECONTAINS + Interpreter.LINE_SEPARATOR +  this.itemContainer.toString());
	}
	
	public boolean addItem(Item it){
		return this.itemContainer.addItem(it);
	}
	
	public Item pickItem(String id){
		return this.itemContainer.pickItem(id);
	}
	
	public void dropItem(Item it)
	{
		this.itemContainer.addItem(it);
	}
	
	public boolean existItem(String id)
	{
		return itemContainer.getItem(id) != null;
	}

}
