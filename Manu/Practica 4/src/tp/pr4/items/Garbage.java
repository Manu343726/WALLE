package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;

public class Garbage extends Item{
	
	private int _recycledMaterial;
	
	/**
	 * Initializes the item  
	 * @param id Item id
	 * @param description Item description
	 * @param recycledMaterial Item recicling points
	 */
	public Garbage(String id, String description, int recycledMaterial){
		super(id, description);
		_recycledMaterial = recycledMaterial;
	}
	
        @Override
	/**
	 * Checks if the item can be used by WALLE
	 * @return
	 */
	public boolean canBeUsed(){
		return (_recycledMaterial > 0);
	}
	
        @Override
	/**
	 * Returns a string representation of this item
	 */
	public String toString(){
	return super.toString() + "// recycled material = " + _recycledMaterial;
	}
	
        @Override
	/**
	 * By calling this function, WALLE uses this item
	 * @param r WALLE's robot engine
	 * @param navigation WALLE's navigation module
	 * @return Returns true only if the use is correct. Returns false otherwise.
	 */
	public boolean use(RobotEngine r, NavigationModule navigation){
            boolean result;
            
            if(this.canBeUsed()){
                r.addRecycledMaterial(_recycledMaterial);
                _recycledMaterial = 0;

                result = true;
            }
            else
                result = false;

            return result;
	}
}
