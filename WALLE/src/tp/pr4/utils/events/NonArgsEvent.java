/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.utils.events;

import java.util.ArrayList;
import java.util.List;
import tp.pr4.utils.Filter;
import tp.pr4.utils.Predicate;
import tp.pr4.utils.RemoveableFilter;

/**
 * This class provides a generic implementation of a event in java-API-style (Event implementation bassed on inheritance and observable-observer pattern).
 * Java has not language-built-in events, its events are API-based. The cons of this aproach is that classes only can have (Raise) one event.
 * Event diffrenetation is based in event args data.
 * 
 * @author
 * Manu343726
 * 
 * @remarks This class is a specialitation of Event class for non-args events. Java generics has not "template specialitation"-like feature (Java generics are
 * basically syntactic sugar). This makes that this class is a copy-paste based class... 
 */
public class NonArgsEvent implements EventSender{
     private List<NonArgsEventHandler> _handlers;
    
    public NonArgsEvent() 
    {
        _handlers = new ArrayList<>();
    }
    
    /**
     * Registers a event handler as a handler of this event.
     * @param handler Event handler to be registered.
     * @return True if the handler has been registered successfully. False if the handler has been registered yet.
     */
    public boolean AddHandler(final NonArgsEventHandler handler )
    {
        Filter<NonArgsEventHandler> filter = new Filter<>(_handlers.iterator() , new Predicate<NonArgsEventHandler>()
        {
            @Override
            public boolean apply(NonArgsEventHandler event_handler)
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
    public boolean RemoveHandler(final NonArgsEventHandler handler )
    {
        RemoveableFilter<NonArgsEventHandler> filter = new RemoveableFilter<>(_handlers.iterator() , new Predicate<NonArgsEventHandler>()
        {
            @Override
            public boolean apply(NonArgsEventHandler event_handler)
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
    public void RaiseEvent()
    {
        for(NonArgsEventHandler handler : _handlers)
            handler.update(this);
    }
}
