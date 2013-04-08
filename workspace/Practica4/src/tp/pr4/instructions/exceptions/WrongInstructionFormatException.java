package tp.pr4.instructions.exceptions;

import tp.pr4.WallEsMessages;

@SuppressWarnings("serial")
/**
 * This class represents an instruction parsing error
 * @author Manu343726
 *
 */
public class WrongInstructionFormatException extends Exception
{
	public WrongInstructionFormatException(){super(WallEsMessages.NOTUNDERSTAND);}
	public WrongInstructionFormatException(String message){super(message);}
}
