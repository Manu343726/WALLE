/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4.messaging;

/**
 * Operations that needs app mode configured throws this exception.
 * @author Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class NoApplicationModeException extends RuntimeException {
    /**
     * Creates a NoApplicationModeException with the specified message
     * @param message A String containing the exception message.
     */
    public NoApplicationModeException(String message) { super(message); }
    
        /**
     * Creates a NoApplicationModeException with a default exception message.
     * @param message A String containing the exception message.
     */
    public NoApplicationModeException() { super("Application mode is not configured"); }
}
