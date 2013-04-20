package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

/**
 * This class represents a quit instruction
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public class QuitInstruction implements Instruction {
	RobotEngine      _engine;
	NavigationModule _navigation;
	ItemContainer    _items;
	
	private static String[] VALIDINSTRUCTIONS = {"QUIT","SALIR"};
	
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
		_engine.requestQuit();
	}

        @Override
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

        @Override
	/**
	 * Tries to parse a string to a instruction
	 * @param cad The parsing string
	 * @return The parsed instruction
	 * @throws WrongInstructionFormatException Throws an exception if the string can't be parsed to the Instruction type.
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		for(String instruction : VALIDINSTRUCTIONS)
			if(cad.equalsIgnoreCase(instruction))
				return this;
		
		throw new WrongInstructionFormatException();
	}
}