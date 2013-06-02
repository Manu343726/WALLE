/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils;

/**
 * Base class for boolean predicates.
 * @author
 * Manuel Sánchez Pérez
 */
public abstract class Predicate<T> {
    /**
     * Applies the predicate to a given value
     * @param value
     * @return The value of the predicate when applied to "value"
     */
    public abstract boolean apply(T value);
    
    /**
     * Returns the opposite predicate (NOT logical operation)
     * @return 
     */
    public final Predicate<T> not()
    {
        return new NotPredicate<>(this);
    }
    
    /**
     * Returns the predicate that represents this predicate AND a second predicate (AND logical operation)
     * @param second
     * @return 
     */
    public final Predicate<T> and(Predicate<T> second)
    {
        return new AndPredicate<>(this,second);
    }
    
        /**
     * Returns the predicate that represents this predicate OR a second predicate (OR logical operation)
     * @param second
     * @return 
     */
    public final Predicate<T> or(Predicate<T> second)
    {
        return new OrPredicate<>(this,second);
    } 
}
