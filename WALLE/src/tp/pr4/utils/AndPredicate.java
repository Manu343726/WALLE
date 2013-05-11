/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.utils;

/**
 * A Predicate class that represents the AND operation of two predicates
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class AndPredicate<T> extends Predicate<T> {
    private Predicate<T> _firstOperand;
    private Predicate<T> _secondOperand;
    
    /**
     * Creates a logical OR predicate from two predicates
     * @param firstOperand First operand of the logical and
     * @param secondOperand Second operand of the logical and
     */
    public AndPredicate(Predicate<T> firstOperand , Predicate<T> secondOperand)
    {
        _firstOperand = firstOperand;
        _secondOperand = secondOperand;
    }
    
    /**
     * Applies the predicate to a given data
     * @param data
     * @return 
     */
    @Override
    public boolean apply(T data)
    {
        return _firstOperand.apply(data) && _secondOperand.apply(data);
    }
}
