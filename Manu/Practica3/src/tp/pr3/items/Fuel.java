package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;

public class Fuel extends Item{
	
	private int _power;
	private int _times;
	
	public Fuel(String id, String description, int power, int times){
		super(id, description);
		_power = power;
		_times = times;
	}
	
	public boolean canBeUsed(){
		return (_times > 0);
	}
	
	public String toString(){
		return super.toString() + "// power = " +  _power + ", times = "+ _times;
	}
	
	public boolean use(RobotEngine r, NavigationModule navigation){
		if(this.canBeUsed()){
		    r.addFuel(_power);
		    _times--;
		    
		    return true;
		}
		else
			return false;
	}

}
