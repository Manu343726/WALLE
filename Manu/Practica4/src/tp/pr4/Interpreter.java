

package tp.pr4;

import java.util.*;

import tp.pr4.instructions.*;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.utils.*;

/***CLASS INTERPRETER***/

/**
 * Represents an interpreter which is responsible for translating user input into instructions for the simulation
 */
public class Interpreter {
	
	public static final String  LINE_SEPARATOR = System.getProperty("line.separator");
	
	private static final Collection<Instruction> _instructions = Arrays.asList(new MoveInstruction(),
                                                                                   new TurnInstruction(),
                                                                                   new PickInstruction(),
                                                                                   new DropInstruction(),
                                                                                   new ScanInstruction(),
                                                                                   new RadarInstruction(),
                                                                                   new OperateInstruction(),
                                                                                   new HelpInstruction(),
                                                                                   new QuitInstruction());
	
	/* PUBLIC METHODS */
	
	/**
	 * Generates a new instruction from a given line
	 * @param line: the user input string 
	 * @return a new instruction from the given line. If the line contains wrong syntax, returns a not valid instruction
	 */
	public static Instruction generateInstruction(final String line){
		Instruction instruction = null;
		Filter<Instruction> filter = new Filter<>(_instructions.iterator(), new Predicate<Instruction>()
                {
                    @Override
                    public boolean apply(Instruction instruction)
                    {
                        try
                        {
                            instruction.parse(line);
                            return true;
                        }
                        catch(WrongInstructionFormatException ex)
                        {
                            return false;
                        }
                    }
                });
                
                if(filter.hasNext())
                    instruction = filter.next();
                else
                    instruction = null;
		
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
