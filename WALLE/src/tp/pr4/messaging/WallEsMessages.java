package tp.pr4.messaging;

import tp.pr4.ApplicationMode;

/***CLASS WALLES MESSAGES***/
/**
 * This class represent the various messages that WALLE can say
 */
public abstract class WallEsMessages {
    
    /* List of walle's messages */
    
    public final static String WALL_E = "WALL·E";
    public final static String HEADER = WALL_E + "> ";
    public final static String ISLOOKINGAT = WALL_E + " is looking at direction ";
    public final static String WALLESAYS = WALL_E + " says: ";
    public final static String NOTUNDERSTAND = WALLESAYS +  "I do not understand. Please repeat";
    public final static String ISMOVING = WALLESAYS + "Moving in direction ";
    public final static String NOSTREET = WALLESAYS + "There is no street in this direction";
    public final static String SHIPFINDED = WALLESAYS + "I am at my spaceship. Bye bye";
    public final static String ENDAPPLICATION = WALLESAYS + "I have communication problems. Bye Bye";

    public final static String STREETCLOSED = WALLESAYS + "Arrggg, there is a street but it is closed!";
    public final static String MYPOWERIS = "      * My power is ";
    public final static String MYRECYCLEDMATERIALIS = "      * My reclycled material is ";//OJO, no es un error, es una errata del validador

    public final static String ALREADYHAVEOBJECT = WALLESAYS + "I am stupid! I had already the object ";
    public final static String HASNOTOBJECT = "You do not have any ";
    public final static String NOWIHAVE = WALLESAYS + "I am happy! Now I have ";

    public final static String IAMCARRYING = WALLESAYS + "I am carrying the following items";
    public final static String INVENTORYEMPTY = WALLESAYS + "My inventory is empty";
    public final static String IHAVENOT = WALLESAYS + "I have not such object";

    public final static String IHAVEPROBLEMS = WALLESAYS + "I have problems using the object ";
    public final static String WHATAPITY1 = WALLESAYS + "What a pity! I have no more ";
    public final static String WHATAPITY2 = " in my inventory";

    public static final String NOFUEL = WALLESAYS + "I run out of fuel. I cannot move. Shutting down...";

    public static final String IHAVEDROPPED = "Great! I have dropped ";
    
    
    /* Esto no me convence, igual debería haber hecho un singleton en una clase aparte */ 
    private static ApplicationMode  _appMode;
    private static MessagesProvider _messagesProvider; 
    
    /**
     * Returns the application front-end mode
     */
    public static ApplicationMode getAppMode() {return _appMode;}
    
    /**
     * Sets the applicacion front-end mode.
     */
    public static void setAppMode(ApplicationMode appMode)
    {
        _appMode = appMode;
        
        if(_appMode == ApplicationMode.CONSOLE)
            _messagesProvider = new StandardMessagesProvider();
        else
            _messagesProvider = new GUIMessagesProvider( false );
    }
    
    /**
     * Returns the application messages provider.
     * @return A pointer to the application messages provider. 
     * @throws NoApplicationModeException If application mode is not configured (No application messages provider created).
     */
    public static MessagesProvider messagesProvider() throws NoApplicationModeException
    {
        if( _messagesProvider == null)
            setAppMode( ApplicationMode.CONSOLE ); //Putos tests...
        
        return _messagesProvider;
    }
}