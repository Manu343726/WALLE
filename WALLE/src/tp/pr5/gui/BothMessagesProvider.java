/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.gui;

import tp.pr5.messaging.*;
import tp.pr5.console.*;

/**
 * This class provides a message provider that executes both modes, GUI and console.
 * @author
 * Manu343726
 */
public class BothMessagesProvider extends MessagesProvider { 
    private GUIMessagesProvider _gui_provider;
    private ConsoleMessagesProvider _console_provider;
    
    /**
     * Ctor
     * @param showInfoMessages if is set to false, info messages are not prompted in graphical messages provider.
     */
    public BothMessagesProvider(boolean showInfoMessages)
    {
        _gui_provider = new GUIMessagesProvider(showInfoMessages);
        _console_provider = new ConsoleMessagesProvider();
    }
    
    /**
     * Outputs an error by a JOptionPen.
     * @param message Error message
     * @param exit If is set to true, app is closed after the message.
     */
    @Override
    public void WriteError(String message , boolean exit)
    {
        _console_provider.WriteError(message); //Exit a false, se encarga el GUI.
        _gui_provider.WriteError(message, exit);
    }
    
     /**
     * Outputs an info by a JOptionPen.
     * @param message info message
     * @param exit If is set to true, app is closed after the message.
     */
    @Override
    public void WriteInfo(String message , boolean exit)
    {
        _console_provider.WriteInfo(message); //Exit a false, se encarga el GUI.
        _gui_provider.WriteInfo(message, exit);
    }
}
