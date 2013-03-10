

package tp.pr3.items;

import tp.pr3.RobotEngine;
import tp.pr3.NavigationModule;

public abstract class Item {
	
	private String _id;
	private String _description;
	
	public Item(String id, String description){
		_id  = id;
		_description = description;
	}
	
	public abstract boolean canBeUsed();
	public abstract boolean use(RobotEngine r, NavigationModule navigation);
	
	public String getId(){
		return _id;
	}
	
	public String toString(){
		return _id + ": " + _description;
	}
	
	public boolean equals(Item i){return _id.equals(i.getId());}
}
