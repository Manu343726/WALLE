package tp.pr4;


import java.util.Observable;
import java.util.Scanner;

import tp.pr4.gui.*;
import tp.pr4.gui.MainWindow;
import tp.pr4.gui.RobotDriver;
import tp.pr4.gui.RobotPanel;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.items.ItemContainer;

/***CLASS NAVIGATION MODULE***/
/***
 * Represents the simulation engine. It process and execute user instructions.
 */
public class RobotEngine extends Observable{
    private int              _recycledMaterial;
    private int              _fuelAmount;
    private NavigationModule _navigation;
    private ItemContainer    _items;
    private boolean _quit;

    private MainWindow _mainWindow;
    private RobotPanel _robotPanel;
    private NavigationPanel _navigationPanel;
    private InstructionsPanel _instPanel;

    public static final int INITVALUES_FUELAMOUNT       = 100;
    public static final int INITVALUES_RECICLEDMATERIAL = 0;

    public static final Direction INITIAL_DIRECTION = Direction.NORTH;

    /***************************************************************************************************\
     * PARAMETROS DE LA FUNCION "PRINTROBOTSTATE":  													*
     * 																									*	
     * La función tiene tres parámetros:																*
     *  - PrintIsMoving: Indica si se muestra hacia donde se mueve WALL-E								*
     *  - PrintJump: Indica si se muestra una línea entre "WALL-E is moving..." y "My power is..."		*
     *  - PringLookingAt: Indica si se muestra hacia donde mira WALL-E									*
     * 																									*
     * Éstos tres parámetros son codificados en los tres bits menos significativos de un entero.		*
     * Puede parecer muy enrevesado, pero lo hago por el simple hecho de que de ésta manera las			*
     * llamadas a la función quedan mucho más claras, comparadas con "printRobotState(true,true,true)"	*
     * por ejemplo. 																					*	
     \**************************************************************************************************/
    public static final int PRINT_ONLYPOWERANDMATERIAL = 0x0; //000
    public static final int PRINT_ISMOVING             = 0x4; //100
    public static final int PRINT_JUMP 	               = 0x2; //010
    public static final int PRINT_LOOKINGAT            = 0x1; //001	
    private static final int LSBMASK = 0x1;
    private static final int TRUE  = 1;




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

    public int getFuel(){
            return _fuelAmount;
    }

    public int getRecycledMaterial(){
            return _recycledMaterial;
    }

    public Street getHeadingStreet(){
            return _navigation.getHeadingStreet();
    }

    public boolean quit(){
            return _quit;
    }

    /**
     * Prints the state of the robot
     */
    public void printRobotState(){
            printRobotState(PRINT_LOOKINGAT);
    }

    /**
     * Prints the state of the robot
     * @param params
     */
    public void printRobotState(int params)
    { 
            if((params & LSBMASK) == TRUE) WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.ISLOOKINGAT + _navigation.getCurrentHeading().toString());	

            if(((params >> 2) & LSBMASK) == TRUE)
            {
            WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.ISMOVING + _navigation.getCurrentHeading().toString());
                WallEsMessages.messagesProvider().WriteInfo(_navigation.getCurrentPlace().toString());
            }

            if(((params >> 1) & LSBMASK) == TRUE) System.out.println();

            WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.MYPOWERIS + _fuelAmount);
            WallEsMessages.messagesProvider().WriteInfo(WallEsMessages.MYRECYCLEDMATERIALIS + _recycledMaterial);
    }

    /**
     * Starts the robot engine. Gets user instructions, processes the instructions and makes the necessary changes
     */
    public void startEngine(){
        if(WallEsMessages.getAppMode() == ApplicationMode.GUI)
        {
            _instPanel = new InstructionsPanel();
            _robotPanel = new RobotPanel(_items);
            _navigationPanel = new NavigationPanel(_navigation);
            RobotDriver driver = new RobotDriver(this, _navigation, _navigationPanel, _instPanel);

            _mainWindow = new MainWindow(this, _robotPanel, _navigationPanel, _instPanel,  driver);
            this.addObserver(_mainWindow);
            this._items.addObserver(_robotPanel);
            this._navigation.addObserver(_navigationPanel);

            _navigationPanel.update(_navigation, true);
            _mainWindow.setVisible(true);
        }
        else
        {
            Scanner sc = new Scanner(System.in);

            System.out.println(_navigation.getCurrentPlace().toString());

            printRobotState(PRINT_LOOKINGAT);

            do{
                System.out.print(WallEsMessages.HEADER);

                comunicateRobot(Interpreter.generateInstruction(sc.nextLine()));
            }while(!_quit && !_navigation.atSpaceship() && _fuelAmount > 0);

            if(_quit)
                System.out.print(WallEsMessages.ENDAPPLICATION);
            else if(_fuelAmount == 0)
                System.out.print(WallEsMessages.NOFUEL);
            else
                System.out.print(WallEsMessages.SHIPFINDED);

            sc.close();
        }
    }
    
    /**
    * Adds an amount of fuel to the robot (it can be negative)
    * @param fuel Amount of fuel added to the robot
    */
    public void addFuel(int fuel){
        _fuelAmount += fuel;

        if(_fuelAmount < 0)
            _fuelAmount = 0;

        reportObservers();
    }

    /**
    * Increases the amount of recycled material of the robot
    * @param weight Amount of recycled material
    */
    public void addRecycledMaterial(int weight){
        _recycledMaterial += weight;
        reportObservers();
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
        _quit = true;
        reportObservers();
    }

    private void reportObservers(){
        setChanged();
        notifyObservers();
    }

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


    public void setGUIWindow(MainWindow mainWindow){
        _mainWindow = mainWindow;
    }

    public void setRobotPanel(RobotPanel robotPanel){
        _robotPanel = robotPanel;
    }

    public void NavigationPanel(NavigationPanel navigationPanel){
        _navigationPanel = navigationPanel;
    }
}
