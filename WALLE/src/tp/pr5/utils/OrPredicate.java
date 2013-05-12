/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils;

/**
 * A Predicate class that represents the OR operation of two predicates
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class OrPredicate<T> extends Predicate<T> {
        private Predicate<T> _firstOperand;
    private Predicate<T> _secondOperand;
    
    /**
     * Creates a logical OR predicate from two predicates
     * @param firstOperand First operand of the logical and
     * @param secondOperand Second operand of the logical and
     */
    public OrPredicate(Predicate<T> firstOperand , Predicate<T> secondOperand)
    {
        _firstOperand = firstOperand;
        _secondOperand = secondOperand;
    }
    
    /**
     * Applies the predicate toa given data
     * @param data
     * @return Predicate value for the data.
     */
    @Override
    public boolean apply(T data)
    {
        return _firstOperand.apply(data) || _secondOperand.apply(data);
    }
}
