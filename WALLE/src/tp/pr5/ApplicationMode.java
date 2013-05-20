/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.pr5;

/**
 * Represents the application front-end mode
 * @author
 * Laura María de Castro Saturio , Manuel Sánchez Pérez
 */
public enum ApplicationMode {
    CONSOLE,       ///< App uses standard output to output messages.
    GUI,           ///< App uses a dialog box to output messages.
    BOTH,          ///< App uses standard output and dialog boxes to display messages,
    UNKNOWN,       ///< Unknown value.
    NOT_SPECIFIED; ///< App front-end mode not speficied.
    
    public static final String PARSE_STRING_CONSOLE = "console";
    public static final String PARSE_STRING_GUI     = "swing";
    public static final String PARSE_STRING_BOTH    = "both";
    
    /**
     * Parses a string to a ApplicationMode enum value.
     * @param str
     * @return UNKNOWN if the string specified is not recogniced. GUI or CONSOLE in other case.
     */
    public static ApplicationMode parse(String str)
    {
        if( str != null )
            if( str.equalsIgnoreCase( PARSE_STRING_CONSOLE ) )
                return ApplicationMode.CONSOLE;
            else if( str.equalsIgnoreCase( PARSE_STRING_GUI ) )
                return ApplicationMode.GUI;
            else if( str.equalsIgnoreCase( PARSE_STRING_BOTH ))
                return ApplicationMode.BOTH;
            else
                return ApplicationMode.UNKNOWN;
        else
            return ApplicationMode.NOT_SPECIFIED;
    }
    
    /***
     * Checks if the app front-end mode is a valid mode.
     * @return Trye if its a valid front-end mode, false in other case.
     */
    public boolean isValid() {return this != UNKNOWN && this != NOT_SPECIFIED; } 
}
