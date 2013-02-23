package tp.pr3;

public class ItemContainer {
	private List<Item> ItemCollection;//Usamos nuestra implementación de ArrayList (PARTE OPCIONAL)
	
	public ItemContainer(){
		this.ItemCollection = new List<Item>();
	}
	
	public boolean addItem(Item item){
		
		int pos = this.search(item.getId());
			
		if(pos < 0)//El elemento ya esta en la lista y no lo a�adimos//No estás usando unicode en el editor verdad? Esas eñes....
			return false;
		else{	
			this.ItemCollection.add(pos, item);
			return true;
		}
	}
	
	public Item getItem(String id){

        int pos = this.search(id);
        	
        if(pos < 0)//El elemento se ha encontrado
        	return this.ItemCollection.get(-pos - 1);
        else
        	return null;

	}
	
	public int numberOfItems(){
		return this.ItemCollection.size();
	}
	
	public Item pickItem(String id){
		
		int pos = this.search(id);
		
		if(pos < 0)//El elemento esta en la lista
			return this.ItemCollection.remove(-pos - 1);
		else 
			return null;
	}
	
	public String toString(){
		String words = "";
		
		for(int i = 0; i < this.ItemCollection.size(); i++)//Lo he modificado para que encaje con el validador (Hay un salto de línea al final)
			words = words + "   " +  this.ItemCollection.get(i).getId() + Interpreter.LINE_SEPARATOR;
		
		return words;
	}
	
	private int search(String id){
		int pos = 0;
		int comp;
		boolean encontrado = false;
		
		while(pos < this.ItemCollection.size() && !encontrado){
			comp = this.ItemCollection.get(pos).getId().compareToIgnoreCase(id);
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
