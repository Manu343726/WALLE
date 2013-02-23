package tp.pr3.instructions;

import tp.pr3.ItemContainer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;

public class MoveInstruction implements Instruction {
	RobotEngine _engine;
	NavigationModule _navigation;
	ItemContainer _items;
	
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		
		_engine = engine;
		_navigation = navigation;
		_items = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException{
		_navigation.move();
	}

	@Override
	public String getHelp() {
		return "MOVE";
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if(cad.compareToIgnoreCase(getHelp()) == 0)
			return this;
		else
			return null;
	}

}
