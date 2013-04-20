/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4;

import java.io.PrintStream;

/**
 * Represents a message provider that uses the standard output stream
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public class StandardMessagesProvider extends MessagesProvider {
    private PrintStream _outputStream;
    
    /*
     * Creates a message provider that uses the specified output stream.
     */
    public StandardMessagesProvider(PrintStream outputStream)
    {
        _outputStream = outputStream;
    }
    
    /*
     * Creates a message provider that uses the standard output stream.
     */
    public StandardMessagesProvider()
    {
        _outputStream = System.out;
    }
    
    @Override
    public void WriteError(String message , boolean exit)
    {
        WriteInfo( message , exit ); //...
    }
    
    @Override
    public void WriteInfo(String message , boolean exit)
    {
        _outputStream.println(message);
                
        if( exit )
            System.exit( 0 );
    }
}
