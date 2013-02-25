package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.WallEsMessages;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.CodeCard;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class OperateInstruction implements Instruction {
	RobotEngine _engine;
	NavigationModule _navigation;
	ItemContainer _items;
	String _id;
	
	private static final String[] VALIDINSTRUCTIONS = {"OPERATE","OPERAR"};
	
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		
		_engine = engine;
		_navigation = navigation;
		_items = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		Item item = _items.getItem(_id);
		
		if(item != null)
			if(item.use(_engine,_navigation))
			{
				if(item.getClass() != CodeCard.class)//Otra guarrada derivada del maravilloso diseño de la práctica...
					_engine.printRobotState(RobotEngine.PRINT_ONLYPOWERANDMATERIAL);
			
				if(!item.canBeUsed())
				{
					System.out.println(WallEsMessages.WHATAPITY1 + item.getId() + WallEsMessages.WHATAPITY2);
					_items.pickItem(_id);
				}
			}
			else
				throw new InstructionExecutionException(WallEsMessages.IHAVEPROBLEMS + item.getId());
		else
			throw new InstructionExecutionException(WallEsMessages.IHAVENOT);
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
			if(words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[0]) || words[0].equalsIgnoreCase(VALIDINSTRUCTIONS[1]))
				_id = words[1];
			else
				throw new WrongInstructionFormatException();
		else
			throw new WrongInstructionFormatException();
		
		return this;
	}
}