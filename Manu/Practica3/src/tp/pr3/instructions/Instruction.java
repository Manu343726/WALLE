package tp.pr3.instructions;

import tp.pr3.*;

public interface Instruction {
	public void configureContext(RobotEngine engine, NavigationModule navigation,ItemContainer robotContainer);
	public void execute();
	public String getHelp();
	public Instruction parse(String cad);
}
