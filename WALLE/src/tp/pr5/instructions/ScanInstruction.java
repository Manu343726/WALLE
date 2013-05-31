package tp.pr5.instructions;

import tp.pr5.Interpreter;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.messaging.WallEsMessages;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * This class represents a scan instruction
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public class ScanInstruction implements Instruction {
	RobotEngine      _engine;
	NavigationModule _navigation;
	ItemContainer    _items;
	String           _id;
	
	private static String[] VALIDINSTRUCTIONS = {"SCAN","ESCANEAR"};
	
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
		Item item = null;
		
		if(_id.equals(""))
		{
			if(_items.numberOfItems() > 0)
				_engine.saySomeInfo(WallEsMessages.IAMCARRYING + Interpreter.LINE_SEPARATOR + _items.toString());
			else
				throw new InstructionExecutionException(WallEsMessages.INVENTORYEMPTY);
		}
		else
		{
			item = _items.getItem(_id);
			
			if(item != null)
				WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.WALLESAYS + item.toString());
			else
				throw new InstructionExecutionException(WallEsMessages.IHAVENOT);
		}
	}

        @Override
	/**
	 * Returns a string containing the instruction help message
	 * @return
	 */
	public String getHelp() {
		String help = "";
		
		for(String instruction : VALIDINSTRUCTIONS)
			help += instruction + " [ <id> ] | ";
		
		return help.substring(0,help.length() - 12);//Si java no es eficiente, yo tampoco
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
		
		switch(words.length)
		{
		case 1:
			if(words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[0]) || words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[1]))
				_id="";
			else
				throw new WrongInstructionFormatException();
			
			break;
		case 2:
			if(words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[0]) || words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[1]))
				_id=words[1];
			else
				throw new WrongInstructionFormatException();
			
			break;
		default: 
			throw new WrongInstructionFormatException();
		}
		
		return this;
	}
}