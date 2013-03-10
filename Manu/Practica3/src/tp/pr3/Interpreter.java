

package tp.pr3;

import java.util.Arrays;
import java.util.Iterator;

import tp.pr3.instructions.*;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;


/***CLASS INTERPRETER***/

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
	
	private static final Instruction[] _instructions = {new MoveInstruction(),
														new TurnInstruction(),
														new PickInstruction(),
														new DropInstruction(),
														new ScanInstruction(),
														new RadarInstruction(),
														new OperateInstruction(),
														new HelpInstruction(),
														new QuitInstruction()};
	
	
	
	
	/* PUBLIC METHODS */
	
	/**
	 * Generates a new instruction from a given line
	 * @param line: the user input string 
	 * @return a new instruction from the given line. If the line contains wrong syntax, returns a not valid instruction
	 */
	public static Instruction generateInstruction(String line){
		Instruction instruction = null;
		Iterator<Instruction> it = Arrays.asList(_instructions).iterator(); 
		boolean parsed = false;
		
		while(!parsed && it.hasNext()){
			try{
				instruction = it.next().parse(line);
				parsed = true; 
			}
			catch(WrongInstructionFormatException ex) {}
		}
		
		return instruction;
	}
	
	/**
	 * Returns information of all valid instructions
	 * @return a string with that information
	 */
	public static String interpreterHelp(){
		String message = "The valid instructions for WALL-E are:";
		
		for(Instruction instruction : _instructions)
			message += LINE_SEPARATOR + "     " + instruction.getHelp();
	       
		return message;
	}
}
