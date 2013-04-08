/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.utils;

/**
 * A logical NOT predicate that represents the opposite of another Predicate
 * @author
 * Manu343726
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
    
    @Override
    public boolean apply(T data)
    {
        return !_original.apply(data);
    }
}
