//OK//

package tp.pr3;


/**
 * Represents an interpreter which is responsible for translating user input into instructions for the simulation
 * The valid instructions are:
 *   -MOVE
 *   -TURN <LEFT|RIGHT>
 *   -PICK <id>
 *   -SCAN <id>
 *   -OPERATE <id>
 *   -HELP
 *   -QUIT
 */
public class Interpreter {
	
	public static final String  LINE_SEPARATOR = System.getProperty("line.separator");
	
	
	/* PUBLIC METHODS */
	/**
	 * Generates a new instruction from a given line
	 * @param line: the user input string 
	 * @return a new instruction from the given line. If the line contains wrong syntax, returns a not valid instruction
	 */
	public static Instruction generateInstruction(String line){
		String words[] = line.split(" ");
		
		switch(words.length){
		case 1: //MOVE, QUIT, HELP, SCAN, PICK, OPERATE or UNKNOWN
			if (words[0].equalsIgnoreCase("QUIT"))
				return new Instruction(Action.QUIT);
			else if (words[0].equalsIgnoreCase("HELP"))
				return new Instruction(Action.HELP);
			else if (words[0].equalsIgnoreCase("MOVE"))
				return new Instruction(Action.MOVE);
			else if (words[0].equalsIgnoreCase("SCAN"))
				return new Instruction(Action.SCAN, "");
			else if (words[0].equalsIgnoreCase("PICK"))
				return new Instruction(Action.PICK, "");
			else if (words[0].equalsIgnoreCase("OPERATE"))
				return new Instruction(Action.OPERATE, "");
			else
				return new Instruction(Action.UNKNOWN);
			
		case 2: //TURN <RIGHT|LEFT|UNKNOWN>, PICK <id>, SCAN <id>, OPERATE <id> or UNKNOWN
			if(words[0].equalsIgnoreCase("TURN"))
				if(words[1].equalsIgnoreCase("RIGHT"))
					return new Instruction(Action.TURN, Rotation.RIGHT);
			
				else if(words[1].equalsIgnoreCase("LEFT"))
					return new Instruction(Action.TURN, Rotation.LEFT);
			
				else
					return new Instruction(Action.TURN, Rotation.UNKNOWN);
			
			else if(words[0].equalsIgnoreCase("PICK"))
				return new Instruction(Action.PICK, words[1]);
			
			else if(words[0].equalsIgnoreCase("SCAN"))
				return new Instruction(Action.SCAN, words[1]);
			
			else if(words[0].equalsIgnoreCase("OPERATE"))
				return new Instruction(Action.OPERATE, words[1]);
			
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
		return ("The valid instructions for WALL-E are:" 
	            + LINE_SEPARATOR + "     MOVE" 
				+ LINE_SEPARATOR + "     TURN <LEFT | RIGHT>" 
				+ LINE_SEPARATOR + "     PICK <id>"
				+ LINE_SEPARATOR + "     SCAN [ <id> ]"
				+ LINE_SEPARATOR + "     OPERATE <id>"
	            + LINE_SEPARATOR + "     HELP"
				+ LINE_SEPARATOR + "     QUIT"
				+ LINE_SEPARATOR);
	}
}
