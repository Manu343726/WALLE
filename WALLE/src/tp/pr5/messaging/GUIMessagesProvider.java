/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.messaging;

import javax.swing.JOptionPane;

/**
 * Represents a message provider that uses a message dialog.
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class GUIMessagesProvider extends MessagesProvider {
    public final String MESSAGEBOXTITLE_INFO  = "WALLE - Information";
    public final String MESSAGEBOXTITLE_ERROR = "WALLE - Error";
    
    private final boolean SHOWINFOMESSAGES;
    
    /**
     * Ctor
     * @param showInfoMessages if is set to false, info messages are not prompted.
     */
    public GUIMessagesProvider(boolean showInfoMessages)
    {
        SHOWINFOMESSAGES = showInfoMessages;
    }
    
    /**
     * Outputs an error by a JOptionPen.
     * @param message Error message
     * @param exit If is set to true, app is closed after the message.
     */
    @Override
    public void WriteError(String message , boolean exit)
    {
        JOptionPane.showMessageDialog(null, message , MESSAGEBOXTITLE_ERROR , JOptionPane.ERROR_MESSAGE);
        
        if( exit )
            System.exit( 0 );
    }
    
     /**
     * Outputs an info by a JOptionPen.
     * @param message info message
     * @param exit If is set to true, app is closed after the message.
     */
    @Override
    public void WriteInfo(String message , boolean exit)
    {
        if(SHOWINFOMESSAGES || exit) //Un pequeño apaño... 
        {
            JOptionPane.showMessageDialog(null, message , MESSAGEBOXTITLE_INFO , JOptionPane.INFORMATION_MESSAGE);
        
            if( exit )
                System.exit( 0 );
        }
    }
}
