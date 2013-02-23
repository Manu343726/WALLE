package tp.pr2;


/***
 * Represents an instruction that the user enters to interact with the simulation
 */
public class Instruction {
	
	private Action action;
	private Rotation rotation;
	private String id;
		
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
		this.id = "";//Por si las moscas, no pasa el test de isValid()
		//Tenía entendido que como java trata al string como un tipo basico, lo inicializa a "". Pero ya no estoy tan seguro....
		//A, mierda, estás usando String, no string. Ojo XD. Eso lo inicializa a null, no a "".
	}
	
	/**
	 * Parameterized constructor, for any instructions
	 * @param action Action of the instruction
	 * @param rotation Rotation value (For rotation instructions. It must be Rotation.UNKNOWN in other case)
	 */
	public Instruction(Action action, Rotation rotation){
		this.action = action;
		this.rotation = rotation;
		this.id = "";//Por si las moscas, no pasa el test de isValid()
	}
	
	public Instruction(Action action, String id){ //OPERATE; SCAN; PICK;
		this.action = action;
		this.rotation = Rotation.UNKNOWN;
		this.id = id;
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
		else if(this.action == Action.PICK  && this.id == "")
			return false;
		else if(this.action == Action.OPERATE  && this.id == "")
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
	
	public String getId(){
		return this.id;
	}
}
