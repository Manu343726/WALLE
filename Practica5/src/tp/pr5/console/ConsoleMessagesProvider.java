/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5.console;

import java.io.PrintStream;
import tp.pr5.messaging.MessagesProvider;

/**
 * Represents a message provider that uses the standard output stream
 * @author
 * Manuel Sánchez Pérez
 */
public class ConsoleMessagesProvider extends MessagesProvider {
    private PrintStream _outputStream;
    
    /**
     * Creates a message provider that uses the specified output stream.
     */
    public ConsoleMessagesProvider(PrintStream outputStream)
    {
        _outputStream = outputStream;
    }
    
    /**
     * Creates a message provider that uses the standard output stream.
     */
    public ConsoleMessagesProvider()
    {
        _outputStream = System.out;
    }
    
    /**
     * Writes an error message through the standard output stream.
     * @param message A String containing the message.
     * @param exit If is set to true, application ends after the message.
     */
    @Override
    public void WriteError(String message , boolean exit)
    {
        WriteInfo( message , exit ); //...
    }
    
     /**
     * Writes an info message through the standard output stream.
     * @param message A String containing the message.
     * @param exit If is set to true, application ends after the message.
     */
    @Override
    public void WriteInfo(String message , boolean exit)
    {
        _outputStream.println(message);
                
        if( exit )
            System.exit( 0 );
    }
}
