/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.utils;

import java.util.*;

/**
 * This class represents a collection filter based on a specified Predicate
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class Filter<T> implements Iterator<T> {
    private Predicate<T> _predicate;
    protected Iterator<T> _underlyingIterator;
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
        T value = _cachedValue;
        
        if(hasNext())
        {
            _cachedValue = null;
            return value;
        }
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
    
    /**
     * Return collection of all values that passes this filter. After the call, this Filter iterator is not valid.
     * @return 
     */
    public Collection<T> filtered()
    {
        Collection<T> ret = new ArrayList<>();
        
        while(this.hasNext())
            ret.add(this.next());
        
        return ret;
    }
    
    /**
     * Returns a new filter that performs the opposite filtering (Filtered items by this will be not filtered and vice versa)
     * @return 
     */
    public Filter<T> opposite()
    {
        return new Filter<>(_underlyingIterator,new NotPredicate(_predicate));
    }
    
    /**
     * Returns the predicate that preforms the filtering
     * @return 
     */
    public Predicate<T> getPredicate() {return _predicate;}
}
