/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils.events;

/**
 * This interface represents a non-args-event handler.
 * Any event handler has a update() function that is "automagically" executed when the corresponding event is raised.
 * 
 * @author
 * Manu343726
 * 
 * @tparam ARGS Type of event argumments.
 * 
 * @remarks The update function (The trully event handler, thanks to the non-function-pointer-java-world...) has two parameters:
 *           - sender: A reference to the object that raises the event. 
 */
public interface NonArgsEventHandler {
    /**
     * Event handler. This function will be "automagically" called when the corresponding event is raised.
     * Note that to use this function as event handler, the instance of this interface must be registered 
     * as event handler first ( See NonArgsEvent::AddHandler() ).
     * @param sender Reference to the event sender.
     */
    public void update(EventSender sender);
}
