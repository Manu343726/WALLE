/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.utils;

/**
 * Base interface for boolean predicates. Based on Guava sintax
 * @author
 * Manu
 */
public interface Predicate<T> {
    /**
     * Applies the predicate to a given value
     * @param value
     * @return The value of the predicate when applied to "value"
     */
    public abstract boolean apply(T value);
}
