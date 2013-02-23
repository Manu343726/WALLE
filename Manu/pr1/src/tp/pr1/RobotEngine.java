package tp.pr1;

import java.util.Scanner;

public class RobotEngine {
	private Interpreter interpreter;
	private Place currentPlace;
	private Direction currentDirection;
	private Street cityMap[];
	
	private final static String MESSAGE_ERROR = "ERROR";
	private final static String MESSAGE_HEADER = "WALL-E> ";
	private final static String MESSAGE_ISLOOKINGAT = "WALL-E is looking at direction ";
	private final static String MESSAGE_WALLESAYS = "WALL-E says: ";
	private final static String MESSAGE_NOTUNDERSTAND = MESSAGE_WALLESAYS + "I do not understand. Please repeat";
	private final static String MESSAGE_ISMOVING = "Moving in direction ";
	private final static String MESSAGE_NOSTREET = MESSAGE_WALLESAYS + "There is no street in this direction";
	private final static String MESSAGE_ENDSIMULATION_SHIPFINDED = MESSAGE_WALLESAYS + "I am at my spaceship. Shutting down... Bye bye";
	private final static String MESSAGE_ENDSIMULATION_USERQUIT = MESSAGE_WALLESAYS + "The application has finished";
	
	
	public RobotEngine(Place initialPlace, Direction direction, Street[] cityMap){
		this.currentPlace = initialPlace;
		this.currentDirection = direction;
		this.cityMap = cityMap;
		this.interpreter = new Interpreter();
	}
	
	public void startEngine(){
		boolean quit = false;
		Scanner sc = new Scanner(System.in);
		Instruction instruction = null;
		Street street = null;
		
		System.out.println(this.currentPlace.toString());
		System.out.println(MESSAGE_ISLOOKINGAT + currentDirection.toString());
		
		do{
			System.out.print(MESSAGE_HEADER);
			
			instruction = this.interpreter.generateInstruction(sc.nextLine());
			
			if(!instruction.isValid())
				System.out.println(MESSAGE_NOTUNDERSTAND);		
			else{
				switch(instruction.getAction()){
				case HELP: 
					System.out.println(this.interpreter.interpreterHelp()); break;
				case QUIT: 
					quit = true; break;
				case TURN:
					this.currentDirection = rotate(this.currentDirection, instruction.getRotation()); break;
				case MOVE:
					street = searchStreet(this.currentPlace, this.currentDirection);
					if(street == null)
						System.out.println(MESSAGE_NOSTREET);
					else
						this.currentPlace = street.nextPlace(this.currentPlace);
					
					break;
				default:
					System.out.println(MESSAGE_ERROR);
					quit=true;
					break;
				}
			}	
		}while(!quit && !this.currentPlace.isSpaceship());
		
		if(quit)
			System.out.print(MESSAGE_ENDSIMULATION_USERQUIT);
		else
			System.out.print(MESSAGE_ENDSIMULATION_SHIPFINDED);
		
		sc.close();
	}
	
	private Direction rotate(Direction initialDirection, Rotation rotation){
		switch(initialDirection){
		case NORTH: 
			if(rotation == Rotation.RIGHT)
				return Direction.EAST;
			else
				return Direction.WEST; 
		case SOUTH:
			if(rotation == Rotation.RIGHT)
				return Direction.WEST;
			else
				return Direction.EAST;
		case EAST:
			if(rotation == Rotation.RIGHT)
				return Direction.SOUTH;
			else
				return Direction.NORTH;
		case WEST:
			if(rotation == Rotation.RIGHT)
				return Direction.NORTH;
			else
				return Direction.SOUTH;
		default:
			return Direction.UNKNOWN;
		}
	}
	
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
