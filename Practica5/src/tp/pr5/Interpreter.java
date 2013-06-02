

package tp.pr5;

import tp.pr5.utils.Predicate;
import tp.pr5.utils.Filter;
import tp.pr5.instructions.DropInstruction;
import tp.pr5.instructions.ScanInstruction;
import tp.pr5.instructions.QuitInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.HelpInstruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.RadarInstruction;
import java.util.*;

import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/***CLASS INTERPRETER***/

/**
 * Represents an interpreter which is responsible for translating user input into instructions for the simulation
 * 
 * @authro Manuel Sánchez Pérez
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
		Instruction instruction;
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
