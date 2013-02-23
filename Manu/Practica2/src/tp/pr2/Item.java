//OK//

package tp.pr2;

public abstract class Item {
	
	protected String id;
	protected String description;
	
	public Item(String id, String description){
		this.id  = id;
		this.description = description;
	}
	
	public abstract boolean canBeUsed();
	public abstract boolean use(RobotEngine r, Place p);
	
	public String getId(){
		return this.id;
	}
	
	public String toString(){
		return this.id + ": " + this.description;
	}
	
	public boolean equals(Item i){return this.id.equals(i.getId());}
}
