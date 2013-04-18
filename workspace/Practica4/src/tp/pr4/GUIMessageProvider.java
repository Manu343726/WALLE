/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4;

import javax.swing.JOptionPane;

/**
 * Represents a message provider that uses a message dialog.
 * @author
 * Manu
 */
public class GUIMessageProvider implements MessageProvider {
    public final String MESSAGEBOXTITLE_INFO  = "WALLE - Information";
    public final String MESSAGEBOXTITLE_ERROR = "WALLE - Error";
    
    @Override
    public void WriteError(String message)
    {
        JOptionPane.showMessageDialog(null, message , MESSAGEBOXTITLE_ERROR , JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void WriteInfo(String message)
    {
        JOptionPane.showMessageDialog(null, message , MESSAGEBOXTITLE_INFO , JOptionPane.INFORMATION_MESSAGE);
    }
}
