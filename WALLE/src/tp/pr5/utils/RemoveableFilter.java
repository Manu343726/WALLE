/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils;

import java.util.Iterator;

/**
 * This class represents a collection filter based on a specified Predicate. The item pointed by the filter can be removed from the collection.
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class RemoveableFilter<T> extends Filter<T> {
    
    /**
     * Initializes a RemoveableFilter with the specified Predicate and a collection iterator.
     * @param begin Iterator pointing ti the begin of the filtering sequence.
     * @param predicate Boolean predicate used to perform the filtering.
     */
    public RemoveableFilter(Iterator<T> begin , Predicate<T> predicate)
    {
        super(begin , predicate);
    }
    
    /**
     * Removes the pointed item from the collection.
     */
    @Override
    public void remove()
    {
        super._underlyingIterator.remove();
        super.next();
    }
}
