/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.utils;

/**
 * A logical NOT predicate that represents the opposite of another Predicate
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class NotPredicate<T> extends Predicate<T> {
    private Predicate<T> _original;
    
    /**
     * Creates a logical NOT predicate from a given predicate
     * @param original The predicate that will be opposited
     */
    public NotPredicate(Predicate <T> original)
    {
        _original = original;
    }
    
    /**
     * Applies the predicate to a given data.
     * @param data Data to be applied with.
     * @return 
     */
    @Override
    public boolean apply(T data)
    {
        return !_original.apply(data);
    }
}
