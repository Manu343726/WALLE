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
    UNKNOWN,       ///< Unknown value.
    NOT_SPECIFIED; ///< App front-end mode not speficied.
    
    public static final String PARSE_STRING_CONSOLE = "console";
    public static final String PARSE_STRING_GUI     = "swing";
    
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
            else
                return ApplicationMode.UNKNOWN;
        else
            return ApplicationMode.NOT_SPECIFIED;
    }
    
    /***
     * Checks if the app front-end mode is a valid mode.
     * @return Trye if its a valid front-end mode, false in other case.
     */
    public boolean isValid() {return this == CONSOLE || this == GUI; } 
}
