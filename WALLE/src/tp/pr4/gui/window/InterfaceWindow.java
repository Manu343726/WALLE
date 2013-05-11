package tp.pr4.gui.window;

import java.util.EventListener;
import java.util.Observer;

/**
 * Generic Interface used cause the driver does not have to know how
 * specifics works the view. You just need to say it to refresh the information
 * passing the new information.
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 *
 */
public interface InterfaceWindow extends Observer{

	/**
	 * Public method that is responsible for setting the controller on the elements
	 *  of the view.
	 * @param driver contains the driver in charge of the view.
	 */
	public void setDriver(EventListener driver);
}
