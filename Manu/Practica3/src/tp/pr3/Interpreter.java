//OK//

package tp.pr3;

import tp.pr3.instructions.*;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;


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
		Instruction instruction;
		try
		{
			instruction = new DropInstruction();
			instruction = instruction.parse(line);
		}
		catch(WrongInstructionFormatException ex1)
		{
			try
			{
				instruction = new HelpInstruction();
				instruction = instruction.parse(line);
			}
			catch(WrongInstructionFormatException ex2)
			{
				try
				{
					instruction = new MoveInstruction();
					instruction = instruction.parse(line);
				}
				catch(WrongInstructionFormatException ex3)
				{
					try
					{
						instruction = new OperateInstruction();
						instruction = instruction.parse(line);
					}
					catch(WrongInstructionFormatException ex4)
					{
						try
						{
							instruction = new PickInstruction();
							instruction = instruction.parse(line);
						}
						catch(WrongInstructionFormatException ex5)
						{
							try
							{
								instruction = new QuitInstruction();
								instruction = instruction.parse(line);
							}
							catch(WrongInstructionFormatException ex6)
							{
								try
								{
									instruction = new RadarInstruction();
									instruction = instruction.parse(line);
								}
								catch(WrongInstructionFormatException ex7)
								{
									try
									{
										instruction = new ScanInstruction();
										instruction = instruction.parse(line);
									}
									catch(WrongInstructionFormatException ex8)
									{
										try
										{
											instruction = new TurnInstruction();
											instruction = instruction.parse(line);
										}
										catch(WrongInstructionFormatException ex9)
										{
											instruction = null;
											System.out.println(ex9.getMessage());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return instruction;
	}
	/**
	 * Returns information of all valid instructions
	 * @return a string with that information
	 */
	public static String interpreterHelp(){
		return ("The valid instructions for WALL-E are:" 
	            + LINE_SEPARATOR + "     " + new MoveInstruction().getHelp() 
				+ LINE_SEPARATOR + "     " + new TurnInstruction().getHelp() 
				+ LINE_SEPARATOR + "     " + new PickInstruction().getHelp()
				+ LINE_SEPARATOR + "     " + new DropInstruction().getHelp()
				+ LINE_SEPARATOR + "     " + new ScanInstruction().getHelp()
				+ LINE_SEPARATOR + "     " + new RadarInstruction().getHelp()
				+ LINE_SEPARATOR + "     " + new OperateInstruction().getHelp()
	            + LINE_SEPARATOR + "     " + new HelpInstruction().getHelp()
				+ LINE_SEPARATOR + "     " + new QuitInstruction().getHelp()
				+ LINE_SEPARATOR);
	}
}
