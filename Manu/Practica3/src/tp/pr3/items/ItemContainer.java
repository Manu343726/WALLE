package tp.pr3.items;

import tp.pr3.Interpreter;
import tp.pr3.List;

public class ItemContainer {
	private List<Item> _itemCollection;//Usamos nuestra implementación de ArrayList (PARTE OPCIONAL)
	
	public ItemContainer(){
		_itemCollection = new List<Item>();
	}
	
	public boolean addItem(Item item){
		
		int pos = this.search(item.getId());
			
		if(pos < 0)//El elemento ya esta en la lista y no lo a�adimos//No estás usando unicode en el editor verdad? Esas eñes....
			return false;
		else{	
			_itemCollection.add(pos, item);
			
			return true;
		}
	}
	
	public boolean containsItem(String id)
	{
		return search(id) < 0;
	}
	
	public Item getItem(String id){

        int pos = this.search(id);
        	
        if(pos < 0)//El elemento se ha encontrado
        	return _itemCollection.get(-pos - 1);
        else
        	return null;

	}
	
	public int numberOfItems(){
		return _itemCollection.size();
	}
	
	public Item pickItem(String id){
		
		int pos = this.search(id);
		
		if(pos < 0)//El elemento esta en la lista
			return _itemCollection.remove(-pos - 1);
		else 
			return null;
	}
	
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
