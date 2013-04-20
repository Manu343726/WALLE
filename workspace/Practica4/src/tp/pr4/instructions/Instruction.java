package tp.pr4.instructions;

import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.items.ItemContainer;

/*Si, usar el preprocesador de C en java es muy friki, pero funciona: http://stackoverflow.com/questions/6525059/can-i-have-macros-in-java-source-files

#define INTERFAZ_QUE_DEBERIA_SER_CLASE_BASE \
	RobotEngine _engine; 					\
	NavigationModule _navigation;		    \
	ItemContainer _items;				    \
											\
	public void configureContext(RobotEngine engine, NavigationModule navigation,ItemContainer robotContainer) \
	{							  \
		_engine = engine;		  \
		_navigation = navigation; \ 
		_items = robotContainer;  \
	} 

*/							   

/**
 * This interface represents the set of methods that any WALLE's instruction must contains.
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public interface Instruction {
	/**
	 * Configures the instruction for the execution
	 * @param engine RobotEngine
	 * @param navigation WALLE's navigation module
	 * @param robotContainer WALLE's inventory
	 */
	public void configureContext(RobotEngine engine, NavigationModule navigation,ItemContainer robotContainer);
	
	/**
	 * Executes the instruction
	 * @throws InstructionExecutionException Throws an exception with any execution error. The exception contains the error description.
	 */
	public void execute() throws InstructionExecutionException;
	
	/**
	 * Returns a string containing the instruction help message
	 * @return
	 */
	public String getHelp();
	
	/**
	 * Tries to parse a string to a instruction
	 * @param cad The parsing string
	 * @return The parsed instruction
	 * @throws WrongInstructionFormatException Throws an exception if the string can't be parsed to the Instruction type.
	 */
	public Instruction parse(String cad) throws WrongInstructionFormatException;
}
