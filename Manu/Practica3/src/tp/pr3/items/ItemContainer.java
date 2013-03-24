package tp.pr3.items;

import tp.pr3.Interpreter;
import java.util.*;

/**
 * This class represents a WALLE's item container
 * @author Manu343726
 *
 */
public class ItemContainer {
	private Collection<Item> _itemCollection;//Usamos nuestra implementación de ArrayList (PARTE OPCIONAL)
	
	/**
	 * Initializes the container
	 */
	public ItemContainer(){
		_itemCollection = new TreeSet<>();
	}
	
	/**
	 * Add an item to the container (The container is sorted by items id)
	 * @param item Item to be added
	 * @return Returns true if the insertion its correct. Returns false if the container already contains the item
	 */
	public boolean addItem(Item item){
            boolean result = true;
            
            if(_itemCollection.contains(item))
                result = false;
            else
                _itemCollection.add(item);
            
            return result;
	}
	
	/**
	 * Checks if the container contains a specified item
	 * @param id Id of the item
	 * @return True if the container contains the item. False in other case.
	 */
	public boolean containsItem(String id)
	{
		return _itemCollection.contains(Item.getInstance(id));
	}
	
	/**
	 * Gets a item from the container
	 * @param id Item id
	 * @return True only if the container contains the ithem. False in other case.
	 */
	public Item getItem(String id){
            return Collections.
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
            _itemCollection.
	}
	
	/**
	 * Returns a string representation of the container
	 */
	public String toString(){
		String words = "";
		
		for(int i = 0; i < _itemCollection.size(); i++)//Lo he modificado para que encaje con el validador (Hay un salto de línea al final)
			words = words + "   " +  _itemCollection.get(i).getId() + Interpreter.LINE_SEPARATOR;
		
		return words;
	}
	
	private int search(String id){
		int pos = 0;
		int comp;
		boolean encontrado = false;
		
		while(pos < _itemCollection.size() && !encontrado){
			comp = _itemCollection.get(pos).getId().compareToIgnoreCase(id);
			//comp = 0 si son iguales
			//comp < 0 si id es mayor
			//comp > 0 si id es menor // Mira que te rayas eh?
			
			if(comp > 0)//id es menor 
				encontrado = true;
			else if(comp == 0){
				encontrado = true;
				pos = -pos - 1; //Hacemos estos para saber si el elemento ha sido encontado o no
			}
			else 
				pos++;
		}
		return pos;
	}
}
