package tp.pr4.gui;

import java.util.EventListener;
import java.util.Observer;

/**
 * 
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public interface InterfaceWindow extends Observer{

	
	public void setDriver(EventListener driver);
}
