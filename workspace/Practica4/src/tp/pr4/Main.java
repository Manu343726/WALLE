
package tp.pr4;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.cityLoader.cityLoaderExceptions.WrongCityFormatException;

import org.apache.commons.cli.*;
import static tp.pr4.RobotEngine.INITIAL_DIRECTION;
import tp.pr4.gui.GUILauncher;
import tp.pr4.gui.InstructionsPanel;
import tp.pr4.gui.MainWindow;
import tp.pr4.gui.NavigationPanel;
import tp.pr4.gui.RobotDriver;
import tp.pr4.gui.RobotPanel;

public class Main {
    /* Command-line args config: */
    
    //Args (Short version):
    private static final String COMMANDLINE_ARGS_MAPFILE  = "m";
    private static final String COMMANDLINE_ARGS_VIEWMODE = "i";
    private static final String COMMANDLINE_ARGS_HELP     = "h";
    
    //Args (Long version):
    private static final String COMMANDLINE_ARGS_LONG_MAPFILE     = "mapfile";
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
    public static int initializeApplication(CommandLine cmd)
    {
        int exitCode = 0;
        City city;
        CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
        RobotEngine engine;
        
        String mapfile = cmd.getOptionValue( COMMANDLINE_ARGS_ARGNAME_MAPFILE );
        ApplicationMode appMode = ApplicationMode.parse( cmd.getOptionValue( COMMANDLINE_ARGS_ARGNAME_VIEWMODE ) );

        if( appMode != ApplicationMode.UNKNOWN )
            if( mapfile != null )
                try{ 
                    city = loader.loadCity(new FileInputStream( mapfile )); 
                    WallEsMessages.setAppMode(appMode);//Sets the application messages output mode.
                    
                    engine = new RobotEngine(city , loader.getInitialPlace() , RobotEngine.INITIAL_DIRECTION); //Sets up the engine
                    
                    if(appMode == ApplicationMode.GUI)
                        GUILauncher.launch( engine );
                    else
                        engine.startEngine();
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
                /* TODO */
            }
        else
        {
            System.err.println("Bad params.");
            System.err.println("Usage: java tp.pr3.Main <mapfile>");
            System.err.println();
            System.err.println("<mapfile> : file with the description of the city.");

            exitCode = 1;
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
        CommandLineParser parser = new BasicParser();
        
        try{ exitCode = initializeApplication( new BasicParser().parse( setupInputOptions() , args) ); }
        catch(ParseException ex)
        {
            System.err.println("Bad params");
            System.err.println("Usage: java tp.pr3.Main <mapfile>");
            System.err.println();
            System.err.println("<mapfile> : file with the description of the city.");
            
            exitCode = 1;
        }
        
        if(exitCode != 0)
            System.exit( exitCode );
    }
}
