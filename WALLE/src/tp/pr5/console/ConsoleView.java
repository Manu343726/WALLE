/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.console;

import java.util.EventListener;
import java.util.Observable;
import tp.pr5.RobotEngine;
import tp.pr5.gui.window.InterfaceWindow;
import tp.pr5.messaging.WallEsMessages;
import tp.pr5.utils.events.*;
import tp.pr5.utils.events.WALLE.*;

/**
 * Application view for console-only mode.
 * @author Manuel Sánchez Pérez
 */
public class ConsoleView                    
{
    /**
     * RobotEngineChangedEvent handler class.
     */
    private class _RobotEngineChangedHandler implements EventHandler<RobotEngineChangeEventArgs>
    {
        @Override
        public void update(EventSender sender, RobotEngineChangeEventArgs args) 
        {
            System.err.println( "RobotEngine updated!!!" );
            
            if( args.getChangeType() == RobotEngineChangeType.QUIT_REQUESTED )
                WallEsMessages.messagesProvider().WriteInfo( WallEsMessages.ENDAPPLICATION , true);
            else if( args.getChangeType() == RobotEngineChangeType.FUEL_CHANGE && args.getValue() <= 0 )
                WallEsMessages.messagesProvider().WriteInfo( WallEsMessages.NOFUEL , true);
        }     
    }
    
    /**
     * RobotEngineChangedEvent handler class.
     */
    private class _NavigationModuleChangedHandler implements EventHandler<NavigationModuleChangedEventArgs>
    {
        private boolean _startup_refresh = true;
        
        @Override
        public void update(EventSender sender, NavigationModuleChangedEventArgs args) 
        {
            if( args.getChangeType() == NavigationModuleChangeType.CHANGE_CURRENTPLACE_EXIT )
                WallEsMessages.messagesProvider().WriteInfo( WallEsMessages.SHIPFINDED , true);
        
            if( _startup_refresh && args.getChangeType() == NavigationModuleChangeType.CHANGE_CURRENTPLACE )
            {
                _startup_refresh = false;
                
                WallEsMessages.messagesProvider().WriteInfo( args.getCurrentPlace().toString() );
            }
        }      
    }
    
    /**
     * RobotEngineChangedEvent handler class.
     */
    private class _ItemContainerChangedHandler implements EventHandler<ItemContainerChangeEventArgs>
    {   
        @Override
        public void update(EventSender sender, ItemContainerChangeEventArgs args) 
        {
            System.err.println( "ItemContainer updated!!!" );
        }
    }
    
    public ConsoleView( RobotEngine engine )
    {
        engine.addNavigationObserver( new _NavigationModuleChangedHandler() );
        engine.addItemsObserver( new _ItemContainerChangedHandler() );
        engine.AddHandler( new _RobotEngineChangedHandler() );  
    }
}
