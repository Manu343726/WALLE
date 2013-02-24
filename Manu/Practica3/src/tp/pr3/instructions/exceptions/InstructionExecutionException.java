package tp.pr3.instructions.exceptions;

@SuppressWarnings("serial")//No usamos serialización, no?
public class InstructionExecutionException extends Exception {
	public InstructionExecutionException(){}
	public InstructionExecutionException(String message){super(message);}
}
