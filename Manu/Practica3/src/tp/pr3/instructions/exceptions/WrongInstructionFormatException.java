package tp.pr3.instructions.exceptions;

import tp.pr3.WallEsMessages;

@SuppressWarnings("serial")
public class WrongInstructionFormatException extends Exception
{
	public WrongInstructionFormatException(){super(WallEsMessages.NOTUNDERSTAND);}
	public WrongInstructionFormatException(String message){super(message);}
}
