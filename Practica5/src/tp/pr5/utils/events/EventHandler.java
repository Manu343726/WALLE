/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils.events;

/**
 * This interface represents a generic event handler. 
 * Any event handler has a update() function that is "automagically" executed when the corresponding event is raised.
 * 
 * @author
 * Manuel Sánchez Pérez
 * 
 * @tparam ARGS Type of event argumments.
 * 
 * @remarks The update function (The trully event handler, thanks to the non-function-pointer-java-world...) has two parameters:
 *           - sender: A reference to the object that raises the event. 
 *           - ARGS args: Event argumments (User-defined event information). 
 *          
 * @remarks This generic implementation provides any different types of event handlers (Well, this is not true at runtime....), based on
 *          the args parameter type, specified by the user. 
 */
public interface EventHandler<ARGS> {
    /**
     * Event handler. This function will be "automagically" called when the corresponding event is raised.
     * Note that to use this function as event handler, the instance of this interface must be registered 
     * as event handler first ( See Event::AddHandler() ).
     * @param sender Reference to the event sender.
     * @param args Event argumments (User-defined event information). 
     */
    public void update(EventSender sender , ARGS args);
}
