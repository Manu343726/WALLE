//OK//

package tp.pr1;

/**
 * Represents an interpreter wich is responsible for translating user input into instructions for the simulation
 */

public class Interpreter {
	
	public static final String  LINE_SEPARATOR = System.getProperty("line.separator");
	
	
	/* PUBLIC METHODS */
	/**
	 * Generates a new instruction from a given line
	 * @param line: the user input string 
	 * @return a new instruction from the given line. If the line contains wrong syntax, returns a not valid instruction
	 */
	public Instruction generateInstruction(String line){
		String words[] = line.split(" ");
		
		switch(words.length){
		case 1: //MOVE, QUIT, HELP or UNKNOWN
			if (words[0].equalsIgnoreCase("QUIT"))
				return new Instruction(Action.QUIT);
			else if (words[0].equalsIgnoreCase("HELP"))
				return new Instruction(Action.HELP);
			else if (words[0].equalsIgnoreCase("MOVE"))
				return new Instruction(Action.MOVE);
			else
				return new Instruction(Action.UNKNOWN);
			
		case 2: //TURN <RIGHT|LEFT|UNKNOWN> or UNKNOWN
			if(words[0].equalsIgnoreCase("TURN"))
				if(words[1].equalsIgnoreCase("RIGHT"))
					return new Instruction(Action.TURN, Rotation.RIGHT);
				else if(words[1].equalsIgnoreCase("LEFT"))
					return new Instruction(Action.TURN, Rotation.LEFT);
				else
					return new Instruction(Action.TURN, Rotation.UNKNOWN);
			else
				return new Instruction(Action.UNKNOWN);
			
		default: //UNKNOWN
			return new Instruction(Action.UNKNOWN);
		}


	}
	/**
	 * Returns information of all valid instructions
	 * @return a string with that information
	 */
	public static String interpreterHelp(){
		return ("The valid instruction for WALL·E are:" 
	            + LINE_SEPARATOR + "  MOVE" 
				+ LINE_SEPARATOR + "  TURN <LEFT|RIGHT>" 
	            + LINE_SEPARATOR + "  HELP" 
				+ LINE_SEPARATOR + "  QUIT");
	}
}
