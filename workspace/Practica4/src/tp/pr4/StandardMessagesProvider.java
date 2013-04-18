/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr4;

import java.io.PrintStream;

/**
 * Represents a message provider that uses the standard output stream
 * @author
 * Manu343726
 */
public class StandardMessagesProvider implements MessagesProvider {
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
    public void WriteError(String message)
    {
        _outputStream.println(message);
    }
    
    @Override
    public void WriteInfo(String message)
    {
        _outputStream.println(message);
    }
}
