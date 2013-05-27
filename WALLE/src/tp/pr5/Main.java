
package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;

import org.apache.commons.cli.*;
import tp.pr5.messaging.WallEsMessages;

/**
 * 
 * @author Laura María de Castro & Manuel Sánchez Pérez
 *
 */
public class Main {
    /* Command-line args config: */
    
    //Args (Short version):
    private static final String COMMANDLINE_ARGS_MAPFILE  = "m";
    private static final String COMMANDLINE_ARGS_VIEWMODE = "i";
    private static final String COMMANDLINE_ARGS_HELP     = "h";
    
    //Args (Long version):
    private static final String COMMANDLINE_ARGS_LONG_MAPFILE     = "map";
    private static final String COMMANDLINE_ARGS_LONG_VIEWMODE    = "interface";
    private static final String COMMANDLINE_ARGS_LONG_HELP        = "help";
    
    //Args description:
    private static final String COMMANDLINE_ARGS_DESCRIPTION_MAPFILE  = "File with the description of the city";
    private static final String COMMANDLINE_ARGS_DESCRIPTION_VIEWMODE = "The type of the interface: console or swing";
    private static final String COMMANDLINE_ARGS_DESCRIPTION_HELP     = "Shows this help message";
    
    //Options names:
    private static final String COMMANDLINE_ARGS_ARGNAME_MAPFILE  = COMMANDLINE_ARGS_MAPFILE;
    private static final String COMMANDLINE_ARGS_ARGNAME_VIEWMODE = COMMANDLINE_ARGS_VIEWMODE;
    private static final String COMMANDLINE_ARGS_ARGNAME_HELP     = COMMANDLINE_ARGS_HELP;
    
    /**
     * Sets up the input options set.
     * @return An instance of the Options class containing the configured command-line options. 
     */
    public static Options setupInputOptions()
    {
        Options options = new Options();
        
        Option mapArg = new Option(COMMANDLINE_ARGS_MAPFILE , COMMANDLINE_ARGS_LONG_MAPFILE , true , COMMANDLINE_ARGS_DESCRIPTION_MAPFILE);
        mapArg.setArgName( COMMANDLINE_ARGS_ARGNAME_MAPFILE );
        
        Option viewModeArg = new Option(COMMANDLINE_ARGS_VIEWMODE , COMMANDLINE_ARGS_LONG_VIEWMODE , true , COMMANDLINE_ARGS_DESCRIPTION_VIEWMODE);
        viewModeArg.setArgName( COMMANDLINE_ARGS_ARGNAME_VIEWMODE );
        
        Option helpArg = new Option(COMMANDLINE_ARGS_HELP , COMMANDLINE_ARGS_LONG_HELP , false , COMMANDLINE_ARGS_DESCRIPTION_HELP);
        helpArg.setArgName( COMMANDLINE_ARGS_ARGNAME_HELP );
        
        options.addOption(mapArg);
        options.addOption(viewModeArg);
        options.addOption(helpArg);
        
        return options;
    } 
    
    /**
     * Sets up the application
     * @param cmd Command line input. 
     */
    public static int initializeApplication(String args[] , Options options) throws ParseException, FileNotFoundException
    {
        CommandLine cmd = new BasicParser().parse( options , args);
        
        int exitCode = 0;
        CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
        
        String mapfile = cmd.getOptionValue( COMMANDLINE_ARGS_ARGNAME_MAPFILE );
        ApplicationMode appMode = ApplicationMode.parse( cmd.getOptionValue( COMMANDLINE_ARGS_ARGNAME_VIEWMODE ) );

        if( cmd.hasOption( COMMANDLINE_ARGS_ARGNAME_HELP ) )
        {
            System.out.println( "Execute this assignment with these parameters:" );
            new HelpFormatter().printHelp( "tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]", options );
        }
        else
        {
            if( mapfile != null )
                if( appMode.isValid() )
                    try{ 
                        WallEsMessages.setAppMode(appMode);//Sets the application messages output mode.

                        ViewLaunchFactory.getLauncher(appMode).launch( new RobotEngine(loader.loadCity(new FileInputStream( mapfile )) , loader.getInitialPlace() , RobotEngine.INITIAL_DIRECTION) , 4);   
                    }
                    catch(FileNotFoundException ex1){
                            System.err.println("Error reading the map file: " + mapfile + " (No existe el fichero o el directorio)");
                            exitCode = 2;
                    }
                    catch(WrongCityFormatException ex2){
                            System.err.println("Error reading the map file: " + mapfile + "(Formato incorrecto)");
                            exitCode = 2;
                    }
                else
                {
                    if( appMode == ApplicationMode.NOT_SPECIFIED )
                        System.err.println( "Interface not specified" );
                    else
                        System.err.println( "Wrong type of interface" );                

                    exitCode = 1;
                }
            else
            {
                System.err.println( "Map file not specified" );

                exitCode = 1;
            }
        }
        
        return exitCode;
    }
    
    /**
     * Creates the city, the engine and finally
     * starts the simulation
     * @param args
     */
    public static void main(String[] args) {
        int exitCode = 0;
        
        try{ exitCode = initializeApplication(  args , setupInputOptions() ); }
        catch(ParseException ex)
        {
            showBadParams();
            exitCode = 1;
        }
        
        if(exitCode != 0)
            System.exit( exitCode );
    }
    
    /**
     * Prints bad params error at the standard error stream.
     */
    private static void showBadParams()
    {
        System.err.println("Bad params");
        System.err.println("Usage: java tp.pr3.Main <mapfile>");
        System.err.println();
        System.err.println("<mapfile> : file with the description of the city.");
    }
}
