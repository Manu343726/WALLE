package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

/**
 * This class represents a move instruction
 * @author Manu343726
 *
 */
public class MoveInstruction implements Instruction {
	RobotEngine      _engine;
	NavigationModule _navigation;
	ItemContainer    _items;
	
	private static final String[] VALIDINSTRUCTIONS = {"MOVE","MOVER"};
	public static final int EXECUTIONFUEL = -5;//Igual que en TurnInstruction, ¡nunca hard-coding!
	
	/**
	 * Configures the instruction for the execution
	 * @param engine RobotEngine
	 * @param navigation WALLE's navigation module
	 * @param robotContainer WALLE's inventory
	 */
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		
		_engine = engine;
		_navigation = navigation;
		_items = robotContainer;
	}

	/**
	 * Executes the instruction
	 * @throws InstructionExecutionException Throws an exception with any execution error. The exception contains the error description.
	 */
	public void execute() throws InstructionExecutionException{
		_navigation.move();
		_engine.addFuel(EXECUTIONFUEL);
		_engine.printRobotState(RobotEngine.PRINT_ISMOVING);
	}

	/**
	 * Returns a string containing the instruction help message
	 * @return
	 */
	public String getHelp() {
		String help = "";
		
		for(String instruction : VALIDINSTRUCTIONS)
			help += instruction + " | ";
		
		return help.substring(0,help.length() - 3);//Si java no es eficiente, yo tampoco
	}

	/**
	 * Tries to parse a string to a instruction
	 * @param cad The parsing string
	 * @return The parsed instruction
	 * @throws WrongInstructionFormatException Throws an exception if the string can't be parsed to the Instruction type.
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		for(String instruction : VALIDINSTRUCTIONS)
			if(cad.equalsIgnoreCase(instruction))
				return this; //Ésto le va a encantar a Alberto...
		
		throw new WrongInstructionFormatException();
	}

}
