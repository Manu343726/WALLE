package tp.pr3.instructions;

import tp.pr3.*;
import tp.pr3.instructions.exceptions.*;

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

public interface Instruction {
	public void configureContext(RobotEngine engine, NavigationModule navigation,ItemContainer robotContainer);
	public void execute() throws InstructionExecutionException;
	public String getHelp();
	public Instruction parse(String cad) throws WrongInstructionFormatException;
}
