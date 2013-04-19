/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4;

/**
 * Operations that needs app mode configured throws this exception.
 * @author
 * Manu343726
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
