package tp.pr5.items;

import tp.pr5.utils.events.Event;
import tp.pr5.utils.events.WALLE.ItemContainerChangeEventArgs;
import tp.pr5.utils.events.WALLE.ItemContainerChangeType;
import tp.pr5.Interpreter;
import java.util.*;

/**
 * This class represents a WALLE's item container
 * @author Manuel Sánchez Pérez
 *
 */
public class ItemContainer extends Event<ItemContainerChangeEventArgs>{
	private TreeMap<String,Item> _itemCollection;
	
	/**
	 * Initializes the container
	 */
	public ItemContainer(){
		_itemCollection = new TreeMap<>();  
	}
	
	/**
	 * Add an item to the container (The container is sorted by items id)
	 * @param item Item to be added
	 * @return Returns true if the insertion its correct. Returns false if the container already contains the item
	 */
	public boolean addItem(Item item){
            boolean result = true;
            
            if(_itemCollection.containsKey(item.getId()))
                result = false;
            else
                _itemCollection.put(item.getId(),item);
            
            if(result)
                this.RaiseEvent( new ItemContainerChangeEventArgs(ItemContainerChangeType.ITEM_ADDED, item , indexOf( item.getId() )) );
            
            return result;
	}
	
	/**
	 * Checks if the container contains a specified item
	 * @param id Id of the item
	 * @return True if the container contains the item. False in other case.
	 */
	public boolean containsItem(String id)
	{
            if( id != null )
		return _itemCollection.containsKey(id);
            else
                return false;
	}
	
	/**
	 * Gets a item from the container
	 * @param id Item id
	 * @return True only if the container contains the ithem. False in other case.
	 */
	public Item getItem(String id){
            return _itemCollection.get(id);
	}
        
        /**
	 * Gets a item from the container
	 * @param index item index
	 * @return True only if the container contains the ithem. False in other case.
	 */
        public Item getItem( int index ) throws NoSuchElementException
        {
            return (Item)_itemCollection.values().toArray()[index];
        }
        
        
        private int indexOf(String id)
        {
            return Arrays.asList( _itemCollection.keySet().toArray() ).indexOf(id);
        }
	
	/**
	 * Returns the container size
	 * @return
	 */
	public int numberOfItems(){
		return _itemCollection.size();
	}
	
	/**
	 * Gets an item and removes it from the container
	 * @param id Item id
	 * @return An Item instance. Retuns null if the container not contains any item with these id.
	 */
	public Item pickItem(String id){
            int index = indexOf( id );
            Item result = _itemCollection.remove(id);
            
            if(result != null)
                this.RaiseEvent( new ItemContainerChangeEventArgs(ItemContainerChangeType.ITEM_DELETED, result , index ) );
            
            return result;
	}
	
        @Override
	/**
	 * Returns a string representation of the container
	 */
	public String toString(){
		String words = "";
                Collection<Item> items = _itemCollection.values();
		
		for(Item item : items)
			words = words + "   " +  item.getId() + Interpreter.LINE_SEPARATOR;
		
		return words;
	}
}
