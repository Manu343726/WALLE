package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.WallEsMessages;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

/**
 * This class represents a pick instruction
 * @author Manu343726
 *
 */
public class PickInstruction implements Instruction {
	RobotEngine      _engine;
	NavigationModule _navigation;
	ItemContainer    _items;
	String           _id;
	
	private static String[] VALIDINSTRUCTIONS = {"PICK","COGER"};
	
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
		Item item = _navigation.pickItemFromCurrentPlace(_id);
		
		if(item != null)
			if(_items.getItem(_id) == null)
			{
				_items.addItem(item);
				System.out.println(WallEsMessages.NOWIHAVE + item.getId());
			}
			else
			{
				_navigation.dropItemAtCurrentPlace(item);
				throw new InstructionExecutionException(WallEsMessages.ALREADYHAVEOBJECT + item.getId());
			}
		else
			throw new InstructionExecutionException(WallEsMessages.HASNOTOBJECT + _id + ".");//El punto final es para el validador
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
		
		if((words.length == 2) &&
                   (words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[0]) || words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[1])))
                {
                    _id = words[1];
                    return this;
                }
                else
                    throw new WrongInstructionFormatException();
	}
}