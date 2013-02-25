package tp.pr3.items;

import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;

public class Fuel extends Item{
	
	private int power;
	private int times;
	
	public Fuel(String id, String description, int power, int times){
		super(id, description);
		this.power = power;
		this.times = times;
	}
	
	public boolean canBeUsed(){
		return (this.times > 0);
	}
	
	public String toString(){
		return super.toString() + "// power = " +  this.power + ", times = "+ this.times;
	}
	
	public boolean use(RobotEngine r, NavigationModule navigation){
		if(this.canBeUsed()){
		    r.addFuel(this.power);
		    this.times--;
		    return true;
		}
		else
			return false;
	}

}
