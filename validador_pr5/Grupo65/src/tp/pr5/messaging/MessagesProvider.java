/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.messaging;

/**
 * An interface for application messages providers
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public abstract class MessagesProvider {
    /**
     * Outputs an error message
     * @param message A string containing the message
     */
    public final void WriteError(String message)
    {
        WriteError(message , false);
    }
    
     /**
     * Outputs an error message
     * @param message A string containing the message
     * @param exit If true, program will be exit after the message.
     */
    public abstract void WriteError(String message , boolean exit);
    
    /**
     * Outputs an info message
     * @param message A string containing the message
     * @param exit If true, program will be exit after the message.
     */
    public final void WriteInfo(String message)
    {
        WriteInfo(message , false);
    }
    
     /**
     * Outputs an info message
     * @param message A string containing the message
     */
    public abstract void WriteInfo(String message , boolean exit);
}
