
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
    private static final String COMMANDLINE_ARGS_DESCRIPTION_VIEWMODE = "The type of interface: console, swing or both";
    private static final String COMMANDLINE_ARGS_DESCRIPTION_HELP     = "Shows this help message";
    
    //Options names:
    private static final String COMMANDLINE_ARGS_ARGNAME_MAPFILE  = "mapfile";
    private static final String COMMANDLINE_ARGS_ARGNAME_VIEWMODE = "type";
    private static final String COMMANDLINE_ARGS_ARGNAME_HELP     = "help";
    
    private static class InputArgs
    {
        private boolean _has_help;
        private String _map_file;
        private ApplicationMode _app_mode;
        
        public boolean hasHelp()    { return _has_help; }
        public boolean hasMapFile() { return _map_file != null; }
        public String mapFile()     { return _map_file; }
        public ApplicationMode applicationMode() { return _app_mode; }
        
        public InputArgs(boolean has_help , String map_file , ApplicationMode app_mode)
        {
            _has_help = has_help;
            _map_file = map_file;
            _app_mode = app_mode;
        }
    }
    
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
    private static InputArgs parseArgs(String args[] , Options options) throws ParseException, FileNotFoundException
    {
        CommandLine cmd = new BasicParser().parse( options , args);
        
        int exitCode = 0;
        CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
        
        String mapfile = cmd.getOptionValue( COMMANDLINE_ARGS_LONG_MAPFILE );
        ApplicationMode appMode = ApplicationMode.parse( cmd.getOptionValue( COMMANDLINE_ARGS_LONG_VIEWMODE ) );
        
        return new InputArgs( cmd.hasOption( COMMANDLINE_ARGS_LONG_HELP ) , mapfile , appMode);
    }
    
    /**
     * Creates the city, the engine and finally
     * starts the simulation
     * @param args
     */
    public static void main(String[] args) {
        int exitCode = 0;
        InputArgs input_args = null;
        CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
        Options options = setupInputOptions();
        
        try
        {
            input_args = parseArgs(  args , setupInputOptions() ); 
            
            if( input_args.hasHelp() )
            {
                System.out.println( "Execute this assignment with these parameters:" );
                new HelpFormatter().printHelp( "tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]", options );
            }
            else
            {
                if( input_args.hasMapFile() )
                {
                    if( input_args.applicationMode().isValid() )
                    {
                        WallEsMessages.setAppMode( input_args.applicationMode() );//Sets the application messages output mode.

                        ViewLaunchFactory.getLauncher( input_args.applicationMode() ).launch( new RobotEngine(loader.loadCity(new FileInputStream( input_args.mapFile() )) , loader.getInitialPlace() , RobotEngine.INITIAL_DIRECTION) , 4);   
                    }       
                    else
                    {
                        if( input_args.applicationMode() == ApplicationMode.NOT_SPECIFIED )
                            System.err.println( "Interface not specified" );
                        else
                            System.err.println( "Wrong type of interface" );                

                        exitCode = 1;
                    }
                }
                else
                {
                    System.err.println( "Map file not specified" );

                    exitCode = 1;
                }  
            }
        }
        catch(FileNotFoundException ex1){
            System.err.println("Error reading the map file: " + input_args.mapFile() + " (No existe el fichero o el directorio)");
            exitCode = 2;
        }
        catch(WrongCityFormatException ex2){
            System.err.println("Error reading the map file: " + input_args.mapFile() + "(Formato incorrecto)");
            exitCode = 2;
        }
        catch(ParseException ex3)
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
