/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.utils.events;

import java.util.ArrayList;
import java.util.List;

import tp.pr4.utils.*;

/**
 *
 * @author
 * Manu343726
 */
public class Event<ARGS> {
    private List<EventHandler<ARGS>> _handlers;
    
    public Event() 
    {
        _handlers = new ArrayList<>();
    }
    
    /**
     * Registers a event handler as a handler of this event.
     * @param handler Event handler to be registered.
     * @return True if the handler has been registered successfully. False if the handler has been registered yet.
     */
    public boolean AddHandler(final EventHandler<ARGS> handler )
    {
        Filter<EventHandler<ARGS>> filter = new Filter<>(_handlers.iterator() , new Predicate<EventHandler<ARGS>>()
        {
            @Override
            public boolean apply(EventHandler<ARGS> event_handler)
            {
                return handler == event_handler; //Instance comparison, not semantic comparison.
            }
        });
        
        if( !filter.hasNext() )
            _handlers.add( handler );
        
        return !filter.hasNext();
    }
    
    /**
     * Unregisters a event handler as a handler of this event.
     * @param handler Event handler to be unregistered.
     * @return True if the handler has been unregistered successfully. False if the handler was not registered as a handler of this event.
     */
    public boolean RemoveHandler(final EventHandler<ARGS> handler )
    {
        RemoveableFilter<EventHandler<ARGS>> filter = new RemoveableFilter<>(_handlers.iterator() , new Predicate<EventHandler<ARGS>>()
        {
            @Override
            public boolean apply(EventHandler<ARGS> event_handler)
            {
                return handler == event_handler; //Instance comparison, not semantic comparison.
            }
        });
        
        if( filter.hasNext() )
        {
            filter.remove();
            return true;
        }
        else
            return false;
    }
    
    public void RaiseEvent(EventSender sender , ARGS args)
    {
        for(EventHandler<ARGS> handler : _handlers)
            handler.update(sender, args);
    }
}
