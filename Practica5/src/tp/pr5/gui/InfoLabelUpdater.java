/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.gui;

import tp.pr5.utils.events.Event;

/**
 * This class manages messages from the message provider to update the information label of the GUI view.
 * @author
 * Manuel Sánchez Pérez
 */
@Deprecated //Information label refresh is implemented by handling the MESSAGE_POSTED robot engine event. This singleton (aka global variable) is not needed.

            //The previous implementation of the label refresh uses this singleton to raise an event from the application message provider (All application messages
            //were pipelined to this InfoLabelUpdater). In my personal opinion, that dessign has a lot of coupling between the GUI implementation and the application
            //messages provider. I think that event-driven dessign (model to view events, active MVC) has less coupling. 
public class InfoLabelUpdater extends Event<String> {
    private static InfoLabelUpdater _instance;
    
    /**
     * Creates an instance of InfoLabelUpdater.
     */
    private InfoLabelUpdater(){}
    
    /**
     * Returns the singleton instance.
     */
    public static InfoLabelUpdater getInstance()
    {
        if( _instance == null )
            _instance = new InfoLabelUpdater();
        
        return _instance;
    }
    
    /**
     * Updates InfoLabel message.
     * @param message New InfoLabel message.
     */
    public void updateInfo( String message )
    {
        this.RaiseEvent(message);
    }
}
