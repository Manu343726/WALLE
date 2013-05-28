package tp.pr5.instructions.exceptions;

@SuppressWarnings("serial")//No usamos serialización, no?
/**
 * This class represents an instruction execution error 
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public class InstructionExecutionException extends Exception {
	public InstructionExecutionException(){}
	public InstructionExecutionException(String message){super(message);}
}
