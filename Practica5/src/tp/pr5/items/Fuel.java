package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.Place;
import tp.pr5.RobotEngine;

public class Fuel extends Item{
	
	private int _power;
	private int _times;
	
	/**
	 * Initialices the item
	 * @param id Item id
	 * @param description Item description
	 * @param power Fuel power
	 * @param times Item uses count
	 */
	public Fuel(String id, String description, int power, int times){
		super(id, description);
		_power = power;
		_times = times;
	}
	
        @Override
	/**
	 * Checks if the item can be used by WALLE
	 * @return
	 */
	public boolean canBeUsed(){
		return (_times > 0);
	}
	
        @Override
	/**
	 * Returns a string representation of this item
	 */
	public String toString(){
		return super.toString() + "// power = " +  _power + ", times = "+ _times;
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
                r.addFuel(_power);
                _times--;

                result = true;
            }
            else
                result = false;
            
            return result;
	}

}
