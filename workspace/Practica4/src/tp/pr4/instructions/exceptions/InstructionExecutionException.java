package tp.pr4.instructions.exceptions;

@SuppressWarnings("serial")//No usamos serialización, no?
/**
 * This class represents an instruction execution error 
 * @author Manu343726
 *
 */
public class InstructionExecutionException extends Exception {
	public InstructionExecutionException(){}
	public InstructionExecutionException(String message){super(message);}
}
