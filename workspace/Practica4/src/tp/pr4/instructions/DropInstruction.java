package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.WallEsMessages;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

/**
 * This class represents a Drop instruction
 * @author Manu343726
 *
 */
public class DropInstruction implements Instruction {
	RobotEngine      _engine;
	NavigationModule _navigation;
	ItemContainer    _items;
	String           _id;
	
	private static  String[] VALIDINSTRUCTIONS = {"DROP","SOLTAR"};
	
        @Override
	/**
	 * Configures the instruction for the execution
	 * @param engine RobotEngine
	 * @param navigation WALLE's navigation module
	 * @param robotContainer WALLE's inventory
	 */
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer) {
		
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
		Item item = _items.pickItem(_id);
		
		if(item != null)
			if(!_navigation.findItemInCurrentPlace(_id))
			{
				_navigation.dropItemAtCurrentPlace(item);
				WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.IHAVEDROPPED + _id);
			}
			else
				throw new InstructionExecutionException();
		else
			throw new InstructionExecutionException(WallEsMessages.HASNOTOBJECT + _id + ".");
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
		
		return help.substring(0,help.length() - 8);//Si java no es eficiente, yo tampoco
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
		
		if(words.length == 2)
			if(words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[0]) || words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[1]))
				_id = words[1];
			else
				throw new WrongInstructionFormatException();
		else
			throw new WrongInstructionFormatException();
		
		return this;
	}
}