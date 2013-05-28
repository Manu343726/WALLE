/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.gui;

import tp.pr5.utils.events.Event;

/**
 * This class manages messages from the message provider to update 
 * @author
 * Manuel Sánchez Pérez
 */
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
