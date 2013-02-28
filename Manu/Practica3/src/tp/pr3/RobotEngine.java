package tp.pr3;


import java.util.Scanner;

import tp.pr3.instructions.Instruction;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.items.ItemContainer;

/***
 * Represents the simulation engine. It process and execute user instructions.
 */
public class RobotEngine {
	private int recycledMaterial;
	private int fuelAmount;
	private NavigationModule _navigation;
	private ItemContainer _items;
	
	private boolean _quit;
	
	/***************************************************************************************************\
	 * PARÁMETROS DE LA FUNCIÓN "PRINTROBOTSTATE":  													*
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
	private static final int FALSE = 0;
	
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
		this.fuelAmount = 100;
		this.recycledMaterial = 0;
		_quit = false;
		_items = new ItemContainer();
	}
	
	
	/* PUBLIC METHODS */
	public void printRobotState()
	{
		printRobotState(PRINT_LOOKINGAT);
	}
	
	public void printRobotState(int params)
	{//Y por que #!x¿?¿#¡ no hay casting de entero a bool? 
		if((params & LSBMASK) == TRUE) System.out.println(WallEsMessages.ISLOOKINGAT + _navigation.getCurrentHeading().toString());	
		
		if(((params >> 2) & LSBMASK) == TRUE)
		{
	        System.out.println(WallEsMessages.ISMOVING + _navigation.getCurrentHeading().toString());
		    System.out.println(_navigation.getCurrentPlace().toString());
		}
		
		if(((params >> 1) & LSBMASK) == TRUE) System.out.println();
		
		System.out.println(WallEsMessages.MYPOWERIS + this.fuelAmount);
		System.out.println(WallEsMessages.MYRECYCLEDMATERIALIS + this.recycledMaterial);
	}
	
	/**
	 * Starts the robot engine. Gets user instructions, processes the instructions and makes the necessary changes
	 */
	public void startEngine(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println(_navigation.getCurrentPlace().toString());
		
		printRobotState(PRINT_LOOKINGAT);
		
		do{
			System.out.print(WallEsMessages.HEADER);
			
			comunicateRobot(Interpreter.generateInstruction(sc.nextLine()));
		}while(!_quit && !_navigation.atSpaceship() && this.fuelAmount > 0);
		
		if(_quit)
			System.out.print(WallEsMessages.ENDAPPLICATION);
		else if(this.fuelAmount == 0)
			System.out.print(WallEsMessages.NOFUEL);
		else
			System.out.print(WallEsMessages.SHIPFINDED);
		
		sc.close();
	}
	
	public void addFuel(int fuel){
		this.fuelAmount = this.fuelAmount + fuel;
		if(this.fuelAmount < 0)
		    this.fuelAmount = 0;
	}
	
	public void addRecycledMaterial(int weight){
		this.recycledMaterial =this.recycledMaterial + weight;
	}
	
	public int getFuel(){
		return this.fuelAmount;
	}
	
	public int getRecycledMaterial(){
		return this.recycledMaterial;
	}
	
	public Street getHeadingStreet(){
		return _navigation.getHeadingStreet();
	}
	
	public void requestHelp()
	{
		System.out.println(Interpreter.interpreterHelp());
	}

	public void requestQuit()
	{
		_quit=true;
	}
	
	public void comunicateRobot(Instruction instruction)
	{
		if(instruction != null)
		{
			instruction.configureContext(this,_navigation,_items);
			
			try
			{
				instruction.execute();
			}catch(InstructionExecutionException ex){
				System.out.println(ex.getMessage());
			}
		}
		else
			System.out.println(WallEsMessages.NOTUNDERSTAND);
	}
}
