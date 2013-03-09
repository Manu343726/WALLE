package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;

public class Garbage extends Item{
	
	private int _recycledMaterial;
	
	public Garbage(String id, String description, int recycledMaterial){
		super(id, description);
		_recycledMaterial = recycledMaterial;
	}
	
	public boolean canBeUsed(){
		return (_recycledMaterial > 0);
	}
	
	public String toString(){
		return super.toString() + "// recycled material = " + _recycledMaterial;
	}
	
	public boolean use(RobotEngine r, NavigationModule navigation){
		if(this.canBeUsed()){
		    r.addRecycledMaterial(_recycledMaterial);
		    _recycledMaterial = 0;
		    
		    return true;
		}
		return false;
		
	}
}
