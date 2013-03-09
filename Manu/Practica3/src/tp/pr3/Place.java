//OK//

package tp.pr3;

import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;


/***
 * Represents a place, with its name and description.  
 */
public class Place {
	
	private String _name;
	private String _description;
	private boolean _isSpaceShip;
	private ItemContainer _itemContainer;
	private final static String PLACECONTAINS = "The place contains these objects:";//Joder, si no es por kdiff no lo veo XD
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
		_name = name;
		_description = description;
		_isSpaceShip = isSpaceShip;
		_itemContainer = new ItemContainer();
	}
	
	
	/* PUBLIC METHODS */
	/**
	 * Check if the spaceship is at this place
	 * @return True if the spaceship is at this place. False in other case.
	 */
	public boolean isSpaceship(){
		return _isSpaceShip;
	}

	/**
	 * Returns the place name and the place description
	 */
	public String toString(){
		if(_itemContainer.numberOfItems() == 0)
			return (_name + Interpreter.LINE_SEPARATOR +  _description + Interpreter.LINE_SEPARATOR + PLACEEMPTY);
		else
		    return (_name + Interpreter.LINE_SEPARATOR +  _description + Interpreter.LINE_SEPARATOR + PLACECONTAINS + Interpreter.LINE_SEPARATOR +  _itemContainer.toString());
	}
	
	public boolean addItem(Item it){
		return _itemContainer.addItem(it);
	}
	
	public Item pickItem(String id){
		return _itemContainer.pickItem(id);
	}
	
	public boolean dropItem(Item it)
	{
		return _itemContainer.addItem(it);
	}
	
	public boolean existItem(String id)
	{
		return _itemContainer.getItem(id) != null;
	}

}
