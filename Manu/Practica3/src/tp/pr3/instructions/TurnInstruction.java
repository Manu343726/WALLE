package tp.pr3.instructions;

import tp.pr3.ItemContainer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Rotation;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;

public class TurnInstruction implements Instruction {
	RobotEngine _engine;
	NavigationModule _navigation;
	ItemContainer _items;
	Rotation _rotation;
	
	private static final String[] VALIDINSTRUCTIONS = {"TURN","GIRAR"};
	public static final int EXECUTIONFUEL = -1;//No me gusta "hardcodear"
	
	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		
		_engine = engine;
		_navigation = navigation;
		_items = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		_navigation.rotate(_rotation);
		_engine.addFuel(EXECUTIONFUEL);
		_engine.printRobotState();
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
			{
				_rotation = Rotation.parse(words[1]);
				
				if(_rotation == Rotation.UNKNOWN)
					throw new WrongInstructionFormatException();
			}
			else
				throw new WrongInstructionFormatException();
		else
			throw new WrongInstructionFormatException();
		
		return this;
	}
}