package tp.pr1;

import java.util.Scanner;

/***
 * Represents the simulation engine. It process and execute user instructions.
 */
public class RobotEngine {
	private Place currentPlace;
	private Direction direction;
	private Street cityMap[];
	private Interpreter interpreter;
	
	
	//WALL-E'S MESSAGES
	private final static String WALL_E = "WALL·E";
	private final static String HEADER = WALL_E + " > ";
	private final static String ISLOOKINGAT = WALL_E + " is looking at direction ";
	private final static String WALLESAYS = WALL_E + " says: ";
	private final static String NOTUNDERSTAND = WALLESAYS +  "I do not understand. Please repeat";
	private final static String ISMOVING = WALLESAYS + "Moving in direction ";
	private final static String NOSTREET = WALLESAYS + "There is no street in this direction";
	private final static String SHIPFINDED = WALLESAYS + "I am at my spaceship. Shutting down... Bye bye";
	private final static String ENDAPPLICATION = WALLESAYS + "I have communication problems. Bye bye";
	
	
	/* CONSTRUCTORS */
	/**
	 * Parameterized constructor
	 * @param place Initial place of the simulation
	 * @param dir Initial direction of the simulation
	 * @param city Places graph for the simulation
	 */
	public RobotEngine(Place place, Direction dir, Street[] city){
		this.currentPlace = place;
		this.direction = dir;
		this.cityMap = city;
		this.interpreter = new Interpreter();
	}
	
	
	/* PUBLIC METHODS */
	/**
	 * Starts the robot engine. Gets user instructions, processes the instructions and makes the necessary changes
	 */
	public void startEngine(){
		
		boolean quit = false;
		Scanner sc = new Scanner(System.in);
		Instruction instruction = null;
		Street street = null;
		
		System.out.println(this.currentPlace.toString());
		System.out.println(ISLOOKINGAT + this.direction);
		
		do{
			System.out.print(Interpreter.LINE_SEPARATOR + HEADER);
			instruction = this.interpreter.generateInstruction(sc.nextLine());
			
			if(!instruction.isValid())
				System.out.println(NOTUNDERSTAND);			
			else{
				switch(instruction.getAction()){
				case HELP: 
					System.out.println(Interpreter.interpreterHelp()); break;
				case QUIT: 
					quit = true; break;
				case TURN:
					this.direction = this.direction.rotate(instruction.getRotation());
					System.out.println(ISLOOKINGAT + this.direction.toString());
					break;
				case MOVE:
					street = searchStreet(this.currentPlace, this.direction);
					
					if(street == null)
						System.out.println(NOSTREET);
					else{
						this.currentPlace = street.nextPlace(this.currentPlace);
						
					    System.out.println(ISMOVING + this.direction.toString());
						System.out.println(this.currentPlace.toString());
						System.out.println(ISLOOKINGAT + this.direction.toString());
					}
					break;
				}
			}	
		}while(!quit && !this.currentPlace.isSpaceship());
		
		if(quit)
			System.out.print(ENDAPPLICATION);
		else
			System.out.print(SHIPFINDED);
		
		sc.close();
	}
	
	
	/* PRIVATE METHODS */
	
	/**
	 * Returns the street that comes out from a given place on a given direction
	 * @param place: place where WALL·E is
	 * @param direction: direction which you want to move
	 * @return if there is a street from the given place on that direction. If there no street returns null
	 */
	private Street searchStreet(Place place, Direction direction){
		int i = 0;
		Street street = null;
		
		boolean encontrada = false;
		while( i < this.cityMap.length && !encontrada){
			if(cityMap[i].comeOutFrom(place, direction)){
				encontrada = true;
				street = cityMap[i];
			}
			else 
				i++;
		}
		return street;
	}
	
	
}
