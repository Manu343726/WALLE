package tp.pr3;


import java.util.Scanner;

/***
 * Represents the simulation engine. It process and execute user instructions.
 */
public class RobotEngine {

	private Place currentPlace;
	private Direction direction;
	private City cityMap;
	private int recycledMaterial;
	private int fuelAmount;
	private ItemContainer itemContainer;
	
	
	//WALL-E'S MESSAGES
	private final static String WALL_E = "WALL·E";
	private final static String HEADER = WALL_E + " > ";
	private final static String ISLOOKINGAT = WALL_E + " is looking at direction ";
	private final static String WALLESAYS = WALL_E + " says: ";
	private final static String NOTUNDERSTAND = WALLESAYS +  "I do not understand. Please repeat";
	private final static String ISMOVING = WALLESAYS + "Moving in direction ";
	private final static String NOSTREET = WALLESAYS + "There is no street in this direction";
	private final static String SHIPFINDED = WALLESAYS + "I am at my space ship. Bye Bye";
	private final static String ENDAPPLICATION = WALLESAYS + "I have communication problems. Bye Bye";//Putos anormales, cambian los mensajes de una practica a otra sin avisar
	
	private final static String STREETCLOSED = WALLESAYS + "Arrggg, there is a street but it is closed!";
	private final static String MYPOWERIS = "   * My power is ";
	private final static String MYRECYCLEDMATERIALIS = "   * My recycled material is: ";
	
	private final static String ALREADYHAVEOBJECT = WALLESAYS + "I am stupid! I had already the object ";
	private final static String HASNOTOBJECT = WALLESAYS + "Ooops, this place has not the object ";
	private final static String NOWIHAVE = WALLESAYS + "I am happy! Now I have  ";
	
	private final static String IAMCARRYING = WALLESAYS + "I am carrying the following items";
	private final static String INVENTORYEMPTY = WALLESAYS + "My inventory is empty";
	private final static String IHAVENOT = WALLESAYS + "I have not such object";
	
	private final static String IHAVEPROBLEMS = WALLESAYS + "I have problems using the object ";
	private final static String WHATAPITY1 = WALLESAYS + "What a pity! I have no more ";
	private final static String WHATAPITY2 = " in my inventory";
	
	private static final String NOFUEL = WALLESAYS + "I run out of fuel. I cannot move. Shutting down...";
	
	/* CONSTRUCTORS */
	/**
	 * Parameterized constructor
	 * @param place Initial place of the simulation
	 * @param dir Initial direction of the simulation
	 * @param city Places graph for the simulation
	 */
	public RobotEngine(City city, Place place, Direction direction){
		this.currentPlace = place;
		this.direction = direction;
		this.cityMap = city;
		this.fuelAmount = 50;
		this.recycledMaterial = 0;
		this.itemContainer = new ItemContainer();
	}
	
	
	/* PUBLIC METHODS */
	/**
	 * Starts the robot engine. Gets user instructions, processes the instructions and makes the necessary changes
	 */
	@SuppressWarnings("incomplete-switch")
	public void startEngine(){
		
		boolean quit = false;
		Scanner sc = new Scanner(System.in);
		Instruction instruction = null;
		Street street = null;
		Item item = null;
		
		System.out.println(this.currentPlace.toString());
		
		System.out.println(MYPOWERIS + this.fuelAmount);
	    System.out.println(MYRECYCLEDMATERIALIS + this.recycledMaterial);
		System.out.println(ISLOOKINGAT + this.direction);
		
		do{
			System.out.print(HEADER);
			instruction = Interpreter.generateInstruction(sc.nextLine());
			
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
					this.fuelAmount = this.fuelAmount - 1;
					
					System.out.println(MYPOWERIS + this.fuelAmount);
					System.out.println(MYRECYCLEDMATERIALIS + this.recycledMaterial);
					System.out.println(ISLOOKINGAT + this.direction.toString());
					break;
				case MOVE:
					street = this.cityMap.lookForStreet(this.currentPlace, this.direction);
					
					if(street == null)
						System.out.println(NOSTREET);
					else{
						if(street.isOpen()){
						    this.currentPlace = street.nextPlace(this.currentPlace);
						    this.fuelAmount = this.fuelAmount - 5;
						    
					        System.out.println(ISMOVING + this.direction.toString());
						    System.out.println(this.currentPlace.toString());
						    
						    System.out.println(Interpreter.LINE_SEPARATOR + MYPOWERIS + this.fuelAmount);
						    System.out.println(MYRECYCLEDMATERIALIS + this.recycledMaterial);
						    System.out.println(ISLOOKINGAT + this.direction.toString());
						}
						else{
							System.out.println(STREETCLOSED);
						}
					}
					break;
				case PICK:
					item = this.currentPlace.pickItem(instruction.getId());
					if(item == null)
						//No esta ese objeto en ese lugar (Laura)
						System.out.println(HASNOTOBJECT + instruction.getId());//Ojo, habias puesto item (NullPointerException)
					else{
						if(this.itemContainer.getItem(item.getId()) != null){
							//Walle ya tiene el objeto (Laura)
							System.out.println(ALREADYHAVEOBJECT + instruction.getId());//Para que encaje con el validador (la id que has metido en la instruccion es en minusculas)
							//Lo volvemos a poner en el lugar (Laura)
							this.currentPlace.addItem(item);//No es un poco absurdo sacarlo y volverlo a meter?....
						}
						else{
							//Lo a�adimos al contenedor de Walle (Laura)
						    this.itemContainer.addItem(item);
						    System.out.println(NOWIHAVE + instruction.getId());
						}
					}
					break;
				case SCAN:
					if(instruction.getId() == ""){//Se deben mostrar todos los elementos
						if(this.itemContainer.numberOfItems() == 0)//el contenedor esta vacio
							System.out.println(INVENTORYEMPTY);
						else{
							System.out.println(IAMCARRYING);
							System.out.println(this.itemContainer.toString());
						}
					}
					else{
						item = this.itemContainer.getItem(instruction.getId());
						if(item == null)//WALLE no tiene ese objeto
							System.out.println(IHAVENOT);
						else 
						    System.out.println(WALLESAYS + item.toString());
					}	
					break;
				case OPERATE:
					item = this.itemContainer.getItem(instruction.getId());
					if(item == null)
						System.out.println(IHAVENOT);
					else{
						
						if(item.use(this, this.currentPlace)){
							if(item.getClass() != CodeCard.class){
						        System.out.println(MYPOWERIS + this.fuelAmount);
						        System.out.println(MYRECYCLEDMATERIALIS + this.recycledMaterial);
							}
						    if(!item.canBeUsed()){
							    System.out.println(WHATAPITY1 + instruction.getId() + WHATAPITY2);
							    this.itemContainer.pickItem(item.getId());
						    }
						}
						else
							System.out.println(IHAVEPROBLEMS + instruction.getId());
					}
					break;
				}
			}	
		}while(!quit && !this.currentPlace.isSpaceship() && this.fuelAmount > 0);
		
		if(quit)
			System.out.print(ENDAPPLICATION);
		else if(this.fuelAmount == 0)
			System.out.print(NOFUEL);
		else
			System.out.print(SHIPFINDED);
		
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
		return this.cityMap.lookForStreet(this.currentPlace, this.direction);
	}
	


	
	
}
