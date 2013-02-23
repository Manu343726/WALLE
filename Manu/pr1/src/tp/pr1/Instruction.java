package tp.pr1;

public class Instruction {
	private Action action;
	private Rotation rotation;
	
	public Instruction(){action=Action.UNKNOWN;rotation=Rotation.UNKNOWN;}
	public Instruction(Action action){
		this.action=action;
		this.rotation=Rotation.UNKNOWN;
	}
	public Instruction(Action action, Rotation rotation){
		this.action=action;
		this.rotation=rotation;
	}
	
	public Action getAction(){return action;}
	public Rotation getRotation(){return rotation;}
	
	public boolean isValid(){	
		if(action != Action.UNKNOWN)
			if(action == Action.TURN)
				return rotation != Rotation.UNKNOWN;
			else
				return true;
		else
			return false;
	}
}
