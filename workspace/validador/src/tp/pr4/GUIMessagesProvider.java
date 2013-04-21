/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4;

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
    
    public GUIMessagesProvider(boolean showInfoMessages)
    {
        SHOWINFOMESSAGES = showInfoMessages;
    }
    
    @Override
    public void WriteError(String message , boolean exit)
    {
        JOptionPane.showMessageDialog(null, message , MESSAGEBOXTITLE_ERROR , JOptionPane.ERROR_MESSAGE);
        
        if( exit )
            System.exit( 0 );
    }
    
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
