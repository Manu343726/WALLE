
package tp.pr4;

import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

/***CLASS PLACE***/
/***
 * Represents a place, with its name and description.  
 */
public class Place {
	
	private String        _name;
	private String        _description;
	private boolean       _isSpaceShip;
	private ItemContainer _itemContainer;
	private final static String PLACECONTAINS = "The place contains these objects:";
	private final static String PLACEEMPTY = "The place is empty. There are no objects to pick";
	
	
	
	
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
        @Override
	public String toString(){
		if(_itemContainer.numberOfItems() == 0)
			return (_name + Interpreter.LINE_SEPARATOR +  _description + Interpreter.LINE_SEPARATOR + PLACEEMPTY);
		else
		    return (_name + Interpreter.LINE_SEPARATOR +  _description + Interpreter.LINE_SEPARATOR + PLACECONTAINS + Interpreter.LINE_SEPARATOR +  _itemContainer.toString());
	}
	
	/**
	 * Tries to add an item to the place. 
	 * The operation can fail (if the place already contains an item with the same name)
	 * @param it The item to be added
	 * @return true if and only if the item can be added to the place, i.e., the place does not contain an item with the same name
	 */
	public boolean addItem(Item it){
		return _itemContainer.addItem(it);
	}
	
	/**
	 * Tries to pick an item characterized by a given identifier from the place. 
	 * If the action was completed the item must be removed from the place.
	 * @param id The identifier of the item
	 * @return The item of identifier id if it exists in the place. Otherwise the method returns null
	 */
	public Item pickItem(String id){
		return _itemContainer.pickItem(id);
	}
	
	/**
	 * Drop an item in this place. The operation can fail, returning false
	 * @param it The name of the item to be dropped.
	 * @return true if and only if the item is dropped in the place, i.e., an item with the same identifier does not exists in the place
	 */
	public boolean dropItem(Item it)
	{
		return _itemContainer.addItem(it);
	}
	
	/**
	 * Checks if an item is in this place
	 * @param id Identifier of an item
	 * @return true if and only if the place contains the item identified by id
	 */
	public boolean existItem(String id)
	{
		return _itemContainer.getItem(id) != null;
	}
	
        /**
         * Gets the item name
         * @return String instance containing the item name.
         */
	public String getName(){
		return this._name;
	}

}
