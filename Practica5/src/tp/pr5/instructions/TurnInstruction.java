package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * This class represents a turn instruction
 * @author Manuel Sánchez Pérez
 *
 */
public class TurnInstruction implements Instruction {
	RobotEngine      _engine;
	NavigationModule _navigation;
	ItemContainer    _items;
	Rotation         _rotation;
	
	private static final String[] VALIDINSTRUCTIONS = {"TURN","GIRAR"};
	public static final int EXECUTIONFUEL = -5;
	
        @Override
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

        @Override
	/**
	 * Executes the instruction
	 * @throws InstructionExecutionException Throws an exception with any execution error. The exception contains the error description.
	 */
	public void execute() throws InstructionExecutionException {
		_navigation.rotate(_rotation);
		_engine.addFuel(EXECUTIONFUEL);
		_engine.printRobotState();
	}

        @Override
	/**
	 * Returns a string containing the instruction help message
	 * @return
	 */
	public String getHelp() {
		String help = "";
		
		for(String instruction : VALIDINSTRUCTIONS)
			help += instruction + " <id> | ";
		
		return help.substring(0,help.length() - 8);
	}

        @Override
	/**
	 * Tries to parse a string to a instruction
	 * @param cad The parsing string
	 * @return The parsed instruction
	 * @throws WrongInstructionFormatException Throws an exception if the string can't be parsed to the Instruction type.
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] words = cad.split(" ");
		
		if((words.length == 2) && 
                   (words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[0]) || words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[1])) && 
                   (_rotation = Rotation.parse(words[1])) != Rotation.UNKNOWN)
                    return this;
                else
                    throw new WrongInstructionFormatException();
	}
}