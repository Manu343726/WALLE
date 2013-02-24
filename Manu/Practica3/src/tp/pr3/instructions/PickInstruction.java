package tp.pr3.instructions;

import tp.pr3.Item;
import tp.pr3.ItemContainer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;

public class PickInstruction implements Instruction {
	RobotEngine _engine;
	NavigationModule _navigation;
	ItemContainer _items;
	String _id;
	
	private static String[] VALIDINSTRUCTIONS = {"PICK","COGER"};
	
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		
		_engine = engine;
		_navigation = navigation;
		_items = robotContainer;
		_id="";
	}

	@Override
	public void execute() throws InstructionExecutionException {
		Item item = _navigation.pickItemFromCurrentPlace(_id);
		
		if(item != null)
			_items.addItem(item);
		else
			throw new InstructionExecutionException();
	}

	@Override
	public String getHelp() {
		String help = "";
		
		for(String instruction : VALIDINSTRUCTIONS)
			help += instruction + " <id> | ";
		
		return help.substring(0,help.length() - 8);//Si java no es eficiente, yo tampoco
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String[] words = cad.split(" ");
		
		if(words.length == 2)
			if(words[0].equals(VALIDINSTRUCTIONS[0]) || words[0].equals(VALIDINSTRUCTIONS[1]))
				_id = words[1];
			else
				throw new WrongInstructionFormatException();
		else
			throw new WrongInstructionFormatException();
		
		return this;
	}
}