package tp.pr1;

public class Interpreter {
	public Instruction generateInstruction(String line){
		String splittedLine[] = line.toUpperCase().split(" ");
		
		switch(splittedLine.length)
		{
		case 1:
			switch(splittedLine[0])
			{
			case "MOVE":
				return new Instruction(Action.MOVE);
			case "HELP":
				return new Instruction(Action.HELP);
			case "QUIT":
				return new Instruction(Action.QUIT);
			default:
				return new Instruction(Action.UNKNOWN);
			}
		case 2:
			if(splittedLine[0].equals("TURN"))
				switch(splittedLine[1])
				{
				case "RIGHT":
					return new Instruction(Action.TURN,Rotation.RIGHT);
				case "LEFT":
					return new Instruction(Action.TURN,Rotation.LEFT);
				default:
					return new Instruction(Action.TURN,Rotation.UNKNOWN);
				}
			else
				return new Instruction(Action.UNKNOWN);
		default:
			return new Instruction(Action.UNKNOWN);
		}
	}
	
	public String interpreterHelp(){
		return "The valid instructions for WALL-E are: \n" +
			   " MOVE" + "\n" +
			   " TURN <LEFT | RIGHT>" + "\n" +
			   " HELP" + "\n" +
			   " QUIT";   
	}
}
