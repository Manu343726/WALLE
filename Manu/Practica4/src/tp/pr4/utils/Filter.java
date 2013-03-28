/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.utils;

import java.util.*;

/**
 * This class represents a collection filter based on a specified Predicate
 * @author
 * Manu
 */
public class Filter<T> implements Iterator<T> {
    private Predicate<T> _predicate;
    private Iterator<T> _underlyingIterator;
    private T _cachedValue;
    
    /**
     * Initializes a Filter with the specified Predicate and a collection iterator
     * @param predicate Filter predicate
     */
    public Filter(Iterator<T> collectionIterator , Predicate<T> predicate)
    {
        _predicate = predicate;
        _underlyingIterator = collectionIterator;
        _cachedValue = null;
    }
    
    /**
     * Returns the next value in the collection that passes the filter 
     * @return Next value that passes the filter
     * @throws NoSuchElementException Iteration has no more elements (No more values passes filtering)
     */
    @Override
    public T next() throws NoSuchElementException
    {
        if(hasNext())
            return _cachedValue;
        else
            throw new NoSuchElementException();
    }
    
    /**
     * Checks if there are more values that passes the filter in the iteration
     * @return
     */
    @Override
    public boolean hasNext()
    {
        boolean predicatePassed = false;
        
        if(_cachedValue != null)
            return true;
        else
        {
            while(_underlyingIterator.hasNext() && !predicatePassed)
                predicatePassed = _predicate.apply(_cachedValue = _underlyingIterator.next());
            
            if(!predicatePassed)
                _cachedValue = null;
            
            return predicatePassed;
        }
    }
    
    /**
     * Removes the item pointed by the iterator from the underlying collection (This operation is not supported by Filter iterator, see throws)
     * @throws UnsupportedOperationException This operation is not supported by Filter<T>. Every call throws UnsupportedOperationException
     */
    @Override
    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
}
