package tp.pr1;

/***
 * Represents an instruction that the user enters to interact with the simulation
 */

public class Instruction {
	
	private Action action;
	private Rotation rotation;
		
	/* CONSTRUCTORS */
	
	/**
	 * Default constructor
	 */
	public Instruction(){
		this.action = Action.UNKNOWN;
		this.rotation = Rotation.UNKNOWN;
	}
	
	/**
	 * Parameterized constructor, for no rotation instructions
	 * @param action Action of the instruction
	 */
	public Instruction(Action action){
		this.action = action;
		this.rotation = Rotation.UNKNOWN;
	}
	
	/**
	 * Parameterized constructor, for any instructions
	 * @param action Action of the instruction
	 * @param rotation Rotation value (For rotation instructions. It must be Rotation.UNKNOWN in other case)
	 */
	public Instruction(Action action, Rotation rotation){
		this.action = action;
		this.rotation = rotation;
	}
	
	
	/* PUBLIC METHODS */
	/**
	 * Check if a given instruction is valid
	 * @return True if the instruction is valid (MOVE, QUIT, HELP, TURN RIGHT, TURN LEFT). False in other case.
	 */
	public boolean isValid(){
		if(this.action == Action.UNKNOWN)
			return false;
		else if(this.action == Action.TURN && this.rotation == Rotation.UNKNOWN)
			return false;
		else 
			return true;
	}
	
	/**
	 * Gets the action stored in the instruction
	 * @return the action stored
	 */
	
	public Action getAction(){
		return this.action;
	}
	
	/**
	 * Gets the rotation stored in the instruction
	 * @return the rotation stored
	 */
	public Rotation getRotation(){
		return this.rotation;
	}
}
