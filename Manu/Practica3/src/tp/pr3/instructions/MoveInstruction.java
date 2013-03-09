package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

public class MoveInstruction implements Instruction {
	RobotEngine      _engine;
	NavigationModule _navigation;
	ItemContainer    _items;
	
	private static final String[] VALIDINSTRUCTIONS = {"MOVE","MOVER"};
	public static final int EXECUTIONFUEL = -5;//Igual que en TurnInstruction, ¡nunca hard-coding!
	
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
		_engine.addFuel(EXECUTIONFUEL);
		_engine.printRobotState(RobotEngine.PRINT_ISMOVING);
	}

	@Override
	public String getHelp() {
		String help = "";
		
		for(String instruction : VALIDINSTRUCTIONS)
			help += instruction + " | ";
		
		return help.substring(0,help.length() - 3);//Si java no es eficiente, yo tampoco
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		for(String instruction : VALIDINSTRUCTIONS)
			if(cad.equalsIgnoreCase(instruction))
				return this; //Ésto le va a encantar a Alberto...
		
		throw new WrongInstructionFormatException();
	}

}
