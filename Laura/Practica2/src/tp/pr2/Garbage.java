package tp.pr2;

public class Garbage extends Item{
	
	private int recycledMaterial;
	
	public Garbage(String id, String description, int recycledMaterial){
		super(id, description);
		this.recycledMaterial = recycledMaterial;
	}
	
	public boolean canBeUsed(){
		return (this.recycledMaterial > 0);
	}
	
	public String toString(){
		return super.toString() + "// recycled material = " + this.recycledMaterial;
	}
	
	public boolean use(RobotEngine r, Place p){
		if(this.canBeUsed()){
		    r.addRecycledMaterial(this.recycledMaterial);
		    this.recycledMaterial = 0;
		    return true;
		}
		return false;
		
	}
}
