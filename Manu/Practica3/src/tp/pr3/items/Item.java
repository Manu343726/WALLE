

package tp.pr3.items;

import tp.pr3.RobotEngine;
import tp.pr3.NavigationModule;

/**
 * This class is the base type for any WALLE's items 
 * @author Manu343726
 *
 */
public abstract class Item {
	
	private String _id;
	private String _description;
	
	/**
	 * Initializes the item 
	 * @param id Item id
	 * @param description Item description
	 */
	public Item(String id, String description){
		_id  = id;
		_description = description;
	}
	
	/**
	 * Checks if the item can be used by WALLE
	 * @return
	 */
	public abstract boolean canBeUsed();
	
	/**
	 * By calling this function, WALLE uses this item
	 * @param r WALLE's robot engine
	 * @param navigation WALLE's navigation module
	 * @return Returns true only if the use is correct. Returns false otherwise.
	 */
	public abstract boolean use(RobotEngine r, NavigationModule navigation);
	
	/**
	 * Gets the item id 
	 * @return
	 */
	public String getId(){
		return _id;
	}
	
	/**
	 * Returns a string representation of this item
	 */
	public String toString(){
		return _id + ": " + _description;
	}
	
	/**
	 * Checks if this item is equivalen to another specified item
	 * @param i Item to be compared
	 * @return
	 */
	public boolean equals(Item i){return _id.equals(i.getId());}
}
