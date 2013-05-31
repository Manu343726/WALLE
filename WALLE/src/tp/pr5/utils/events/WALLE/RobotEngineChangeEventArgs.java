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
    private String _message; //Mensaje para los eventos MESSAGE_POSTED
    
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
        _message = null;
    }
    
    /**
     * Generates a new instance of RobotEngineChange event args with the specified data
     * @param change Change type
     * @param message Engine message posted.
     */
    public RobotEngineChangeEventArgs(RobotEngineChangeType change , String message)
    {
        _type = change;
        //Value guarda basura
        _message = message;
    }
    
    //NOTA: Implemento éste último constructor por extensibilidad y comodidad, pero en el engine uso el anterior por claridad y similitud con respecto al resto de llamadas a raiseEvent().
    
    /**
     * Generates a new instance of RobotEngineChange event args for message posted events.
     * @param message Engine message posted.
     */
    public RobotEngineChangeEventArgs(String message) { this(RobotEngineChangeType.MESSAGE_POSTED , message); }
    
    /**
     * Gets the value of the data changed in the robot engine. Its meaning depends on the type of the change ( See getChamgeType() ).
     * @return An int wrapper containing the value (A wrapper is needed beacuse the generic implementation of event handlers).
     * @return If event type is RobotEngineChangeType::MESSAGE_POSTED, getValue() returns garbage (value is not initialized).
     */
    public Integer getValue() { return _value; } //Copy is not needed, the original value is passed by value in the ctor.
    
    /**
     * Gets the type of the event.
     * @return An instance of RobotEngineChangeType with the type of the change (Type of event).
     */
    public RobotEngineChangeType getChangeType() { return _type; }
    
    /**
     * Gets the message posted in that type of engine events.
     * @return A string containing the message if type is RobotEngineChangeType::MESSAGE_POSTED. null otherwise.
     */
    public String getMessage() { return _message; }
}
