package tp.pr3.instructions;

import tp.pr3.Interpreter;
import tp.pr3.ItemContainer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;

public class HelpInstruction implements Instruction {
	RobotEngine _engine;
	NavigationModule _navigation;
	ItemContainer _items;
	
	private static  String[] VALIDINSTRUCTIONS = {"HELP","AYUDA"};
	
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		
		_engine = engine;
		_navigation = navigation;
		_items = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		_engine.requestHelp();
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
			if(cad.compareToIgnoreCase(instruction) == 0)
				return this; //Ésto le va a encantar a Alberto...
		
		throw new WrongInstructionFormatException();
	}
}
