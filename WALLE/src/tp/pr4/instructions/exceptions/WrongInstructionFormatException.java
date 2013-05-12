package tp.pr4.instructions.exceptions;

import tp.pr4.messaging.WallEsMessages;

@SuppressWarnings("serial")
/**
 * This class represents an instruction parsing error
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public class WrongInstructionFormatException extends Exception
{
	public WrongInstructionFormatException(){super(WallEsMessages.NOTUNDERSTAND);}
	public WrongInstructionFormatException(String message){super(message);}
}
