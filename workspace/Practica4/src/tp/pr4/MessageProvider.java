/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4;

/**
 * An interface for application messages providers
 * @author
 * Manu
 */
public interface MessageProvider {
    /*
     * Outputs an error message
     * @param message A string containing the message
     */
    public void WriteError(String message);
    
    /*
     * Outputs an info message
     * @param message A string containing the message
     */
    public void WriteInfo(String message);
}
