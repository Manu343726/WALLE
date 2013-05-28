/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils.events;

import tp.pr5.utils.Predicate;
import tp.pr5.utils.Filter;
import tp.pr5.utils.RemoveableFilter;
import java.util.ArrayList;
import java.util.List;


/**
 * This class provides a generic implementation of a event in java-API-style (Event implementation bassed on inheritance and observable-observer pattern).
 * Java has not language-built-in events, its events are API-based. The cons of this aproach is that classes only can have (Raise) one event.
 * Event diffrenetation is based in event args data.
 * 
 * @author
 * Manu343726
 * 
 * Based on cpp_lib32 high-level events implementation: https://github.com/Manu343726/cpp_lib32/blob/BigRefactoring/code/headers/dl32Events.h
 */
public class Event<ARGS> implements EventSender{
    private List<EventHandler<ARGS>> _handlers;
    
    /**
     * Initializes the event.
     */
    protected Event() 
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
        {
            _handlers.add( handler );
            return true;
        }
        else
            return false;
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
    
    /**
     * Raises the event with the specified data.
     * @param sender Reference to the object that raises the event.
     * @param args Event argumments.
     */
    protected void RaiseEvent(ARGS args)
    {
        for(EventHandler<ARGS> handler : _handlers)
            handler.update(this, args);
    }
}
