/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.console;

import tp.pr5.RobotEngine;
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
     * NavigationModuleChangedEvent handler class.
     */
    private class _NavigationModuleChangedHandler implements EventHandler<NavigationModuleChangedEventArgs>
    {
        private boolean _startup_refresh = true;
        private RobotEngine _engine;
        
        /**
         * Initialices navigation changes handler with the speficied engine.
         * @param engine Reference to the application model.
         */
        public _NavigationModuleChangedHandler(RobotEngine engine)
        {
            _engine = engine;
        }
        
        @Override
        public void update(EventSender sender, NavigationModuleChangedEventArgs args) 
        {
            System.err.println( "NavigationModule updated!!!" );
            
            if( args.getChangeType() == NavigationModuleChangeType.CHANGE_CURRENTPLACE_EXIT )
            {
                _engine.printRobotState(false, true, false);//HORROR VALIDADOR DE LOS HUEEEEVOS
                //WallEsMessages.messagesProvider().WriteInfo( args.getCurrentPlace().toString() );//Validador de los cojones
                WallEsMessages.messagesProvider().WriteInfo( WallEsMessages.SHIPFINDED , true);
            }
        
            if( _startup_refresh && args.getChangeType() == NavigationModuleChangeType.CHANGE_CURRENTPLACE )
            {
                _startup_refresh = false;
                
                WallEsMessages.messagesProvider().WriteInfo( args.getCurrentPlace().toString() );
            }
        }      
    }
    
    /**
     * ItemContainerChangedEvent handler class.
     */
    private class _ItemContainerChangedHandler implements EventHandler<ItemContainerChangeEventArgs>
    {
        @Override
        public void update(EventSender sender, ItemContainerChangeEventArgs args) 
        {
            System.err.println( "ItemContainer updated!!!" );
        }
    }
    
    /**
     * Initialices a console view with the specified engine as model.
     * @param engine A reference to the application model.
     */
    public ConsoleView( RobotEngine engine )
    {
        engine.addNavigationObserver( new _NavigationModuleChangedHandler( engine ) );
        engine.addItemsObserver( new _ItemContainerChangedHandler() );
        engine.AddHandler( new _RobotEngineChangedHandler() );  
    }
}
