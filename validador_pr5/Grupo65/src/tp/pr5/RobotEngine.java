package tp.pr5;

import tp.pr5.utils.events.WALLE.RobotEngineChangeEventArgs;
import tp.pr5.utils.events.WALLE.RobotEngineChangeType;
import tp.pr5.utils.events.Event;
import tp.pr5.utils.events.EventHandler;
import tp.pr5.items.ItemContainer;
import tp.pr5.messaging.WallEsMessages;
import tp.pr5.utils.events.WALLE.ItemContainerChangeEventArgs;
import tp.pr5.utils.events.WALLE.NavigationModuleChangedEventArgs;
import java.util.Scanner;

import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;


/***CLASS NAVIGATION MODULE***/
/***
 * Represents the simulation engine. It process and execute user instructions.
 */
public class RobotEngine extends Event<RobotEngineChangeEventArgs>{
    private int              _recycledMaterial;
    private int              _fuelAmount;
    private NavigationModule _navigation;
    private ItemContainer    _items;
    private boolean _quit;

    public static final int INITVALUES_FUELAMOUNT       = 100;
    public static final int INITVALUES_RECICLEDMATERIAL = 0;

    public static final Direction INITIAL_DIRECTION = Direction.NORTH;

    /* CONSTRUCTORS */

    /**
     * Parameterized constructor
     * @param place Initial place of the simulation
     * @param dir Initial direction of the simulation
     * @param city Places graph for the simulation
     */
    public RobotEngine(City city, Place place, Direction direction){
            _navigation = new NavigationModule(city,place);
            _navigation.initHeading(direction);//Para que ponerlo en el constructor....pa que....
            _fuelAmount = INITVALUES_FUELAMOUNT;
            _recycledMaterial = INITVALUES_RECICLEDMATERIAL;
            _quit = false;
            _items = new ItemContainer();
    }

    /* PUBLIC METHODS */

    /**
     * Gets WALLE's fuel ammount.
     * @return 
     */
    public int getFuel(){
            return _fuelAmount;
    }

    /**
     * Gets WALLE's recicled material count.
     * @return 
     */
    public int getRecycledMaterial(){
            return _recycledMaterial;
    }

    /**
     * Gets WALLE's heading street.
     * @return 
     */
    public Street getHeadingStreet(){
            return _navigation.getHeadingStreet();
    }

    /**
     * Request engine exit.
     * @return 
     */
    public boolean quit(){
            return _quit;
    }
    
    /***
     * Adds an observer to the set of observers for the enine items container.
     * @param o Pointer to the observer.
     */
    public void addItemsObserver(EventHandler<ItemContainerChangeEventArgs> handler)
    {
        _items.AddHandler( handler );
    }
    
     /***
     * Adds an observer to the set of observers for the enine navigation module.
     * @param o Pointer to the observer.
     */
    public void addNavigationObserver(EventHandler<NavigationModuleChangedEventArgs> handler)
    {
        _navigation.AddHandler( handler );
    }
    
    /***
     * Forces change event.
     */
    public void forceRefresh()
    {
        this.RaiseEvent( new RobotEngineChangeEventArgs( RobotEngineChangeType.FUEL_CHANGE     , _fuelAmount ) );
        this.RaiseEvent( new RobotEngineChangeEventArgs( RobotEngineChangeType.MATERIAL_CHANGE , _recycledMaterial ) );
        
        _navigation.forceRefresh();
    }
    
    /***
     * Gets a pointer to WALLE's navigation module (HORRRRIBLE)
     * @return 
     */
    public NavigationModule getNavigationModule()//No me gusta nada, pero era ésto o meter todo el setup de la GUI en el constructor del robot engine ( Ver GUILauncher::launch() )
    {
        return _navigation;
    }
    
    /***
     * Gets a pointer to WALLE's items container (HORRRRIBLE)
     * @return 
     */
    public ItemContainer getItemContainer()//No me gusta nada, pero era ésto o meter todo el setup de la GUI en el constructor del robot engine ( Ver GUILauncher::launch() )
    {
        return _items;
    }

    /**
     * Prints the state of the robot
     */
    public void printRobotState(){
            printRobotState(true , false , false);
    }

    /**
     * Prints the state of the robot
     * @param params
     */
    public void printRobotState(boolean printIsLookingAt , boolean printIsMoving , boolean printJump)
    { //Con lo que molaban los bitsets...
            if( printIsLookingAt ) WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.ISLOOKINGAT + _navigation.getCurrentHeading().toString());	

            if( printIsMoving )
            {
                WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.ISMOVING + _navigation.getCurrentHeading().toString());
                WallEsMessages.messagesProvider().WriteInfo(_navigation.getCurrentPlace().toString());
            }

            if( printJump ) WallEsMessages.messagesProvider().WriteInfo("");

            WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.MYPOWERIS + _fuelAmount);
            WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.MYRECYCLEDMATERIALIS + _recycledMaterial);
    }

    /**
     * Starts the robot engine. Gets user instructions, processes the instructions and makes the necessary changes
     */
    @Deprecated
    public void startEngine(){

    }
    
    public void requestStart() {}
    
    /**
    * Adds an amount of fuel to the robot (it can be negative)
    * @param fuel Amount of fuel added to the robot
    */
    public void addFuel(int fuel){
        _fuelAmount += fuel;

        if(_fuelAmount < 0)
            _fuelAmount = 0;

        this.RaiseEvent( new RobotEngineChangeEventArgs(RobotEngineChangeType.FUEL_CHANGE, _fuelAmount) );
    }

    /**
    * Increases the amount of recycled material of the robot
    * @param weight Amount of recycled material
    */
    public void addRecycledMaterial(int weight){
        _recycledMaterial += weight;
        this.RaiseEvent( new RobotEngineChangeEventArgs(RobotEngineChangeType.MATERIAL_CHANGE,_recycledMaterial));
    }

    /**
    * Prints the information about all possible instructions
    */
    public void requestHelp(){
        System.out.println(Interpreter.interpreterHelp());
    }

    /**
    * Requests the end of the simulation
    */
    public void requestQuit(){
        RobotEngineChangeEventArgs args = new RobotEngineChangeEventArgs(RobotEngineChangeType.QUIT_REQUESTED, RobotEngineChangeEventArgs.UNMEANING_VALUE);
        
        _quit = true;
        
        this.RaiseEvent ( args );
        
        _quit = !args.quitAborted();
    }
    
    /**
     * Aborts the close operation.
     */
    public void abortQuit(){
        _quit = false;
    }
    
    /**
     * Checks if the game is over (Quit was requested, WALLE is at spaceship, or WALLE has not enough fuel).
     * @return True if the game is over. False iin other case.
     */
    public boolean isOver() { return !(!_quit && !_navigation.atSpaceship() && _fuelAmount > 0); }

    /**
    * It executes an instruction. 
    * The instruction must be configured with the context before executing it. 
    * It controls the end of the simulation. 
    * If the execution of the instruction throws an exception, then the corresponding message is printed
    * @param instruction The instruction to be executed
    */
    public void comunicateRobot(Instruction instruction){
        if(instruction != null){
            instruction.configureContext(this,_navigation,_items);

            try{
                    instruction.execute();
            }catch(InstructionExecutionException ex){
                    WallEsMessages.messagesProvider().WriteError(ex.getMessage());
            }
        }
        else
            WallEsMessages.messagesProvider().WriteError(WallEsMessages.NOTUNDERSTAND);
    }
}
