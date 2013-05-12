/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.utils.events.WALLE;

/**
 * This class provides information of a RobotEngine change event.
 * @author
 * Manu343726
 */
public class RobotEngineChangeEventArgs {
    private RobotEngineChangeType _type;
    private Integer _value; //Uso el wrapper de int para reducir el número de boxing/unboxing
    
    public static int UNMEANING_VALUE = 0;
    
    /**
     * Generates a new instance of RobotEngineChange event args with the specified data
     * @param change Change type
     * @param value Change value (Value after the change).
     */
    public RobotEngineChangeEventArgs(RobotEngineChangeType change , int value)
    {
        _type = change;
        _value = value;
    }
    
    /**
     * Gets the value of the data changed in the robot engine. Its meaning depends on the type of the change ( See getChamgeType() ).
     * @return An int wrapper containing the value (A wrapper is needed beacuse the generic implementation of event handlers).
     */
    public Integer getValue() { return _value; } //Copy is not needed, the original value is passed by value in the ctor.
    public RobotEngineChangeType getChangeType() { return _type; }
}
