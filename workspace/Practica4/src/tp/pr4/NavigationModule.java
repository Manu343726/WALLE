package tp.pr4;

import tp.pr4.gui.NavigationPanel;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.items.Item;

/***CLASS NAVIGATION MODULE***/
/**
 * This class is in charge of the robot navigation features.
 *  It contains the city where the robot looks for garbage, the current place where the robot is, 
 *  and the current direction of the robot. It contains methods to handle the different robot movements 
 *  and to pick and drop items at the current place.
 */

public class NavigationModule {
	
	private City      _city;
	private Place     _currentPlace;
	private Direction _currentDirection;
	private NavigationPanel navPanel;
	
	/* CONSTRUCTORS */
	
	public NavigationModule(City city, Place place){
		_city = city;
		_currentPlace = place;
		_currentDirection = Direction.NORTH;
	}
	
	/* PUBLIC METHODS */
	
	/**
	 * Checks if the robot has arrived at a spaceship
	 * @return true if an only if the current place is the spaceship
	 */
	public boolean atSpaceship(){
		return _currentPlace.isSpaceship();
	}
	
	/**
	 * Returns the robot heading
	 * @return The direction where the robot is facing to.
	 */
	public Direction getCurrentHeading(){
		return _currentDirection;
	}
	
	/**
	 * Returns the current place where the robot is
	 * @return The current place
	 */
	public Place getCurrentPlace(){	
		return _currentPlace;
	}
	
	/**
	 * Updates the current direction of the robot according to the rotation
	 * @param rotation  left or right
	 */
	public void rotate(Rotation rotation){
		_currentDirection = _currentDirection.rotate(rotation);
	}
	
	/**
	 * Tries to pick an item characterized by a given identifier from the current place. 
	 * If the action was completed the item is removed from the current place.
	 * @param id The identifier of the item
	 * @return The item of identifier id if it exists in the place. Otherwise the method returns null
	 */
	public Item pickItemFromCurrentPlace(String id){
		return _currentPlace.pickItem(id);
	}
	
	/**
	 * Checks if there is an item with a given id in this place
	 * @param id Identifier of the item we are looking for
	 * @return true if and only if an item with this id is in the current place
	 */
	public boolean findItemInCurrentPlace(String id){
		return _currentPlace.existItem(id);
	}
	
	/**
	 * Drop an item in the current place. It assumes that there is no other item with the same name/id there. 
	 * Otherwise, the behaviour is undefined.
	 * @param it The name of the item to be dropped.
	 * @return true if the action has been completed correctly
	 */
	public boolean dropItemAtCurrentPlace(Item it){
		return _currentPlace.dropItem(it);
	}
	
	/**
	 * Initializes the current heading according to the parameter
	 * @param direction New direction for the robot
	 */
	public void initHeading(Direction direction){
		_currentDirection = direction;
	}
	
	/**
	 * Returns the street opposite the robot
	 * @return The street which the robot is facing, or null, if there is not any street in this direction
	 */
	public Street getHeadingStreet(){
		return _city.lookForStreet(_currentPlace, _currentDirection);
	}
	
	/**
	 * The method tries to move the robot following the current direction. 
	 * If the movement is not possible because there is no street, or there is a street which is closed, then it throws an exception.
	 * Otherwise the current place is updated according to the movement
	 * @throws InstructionExecutionException An exception with a message about the encountered problem
	 */
	public void move() throws InstructionExecutionException{
		Street currentStreet = getHeadingStreet();
		
		if(currentStreet != null){
			if(currentStreet.isOpen())
				_currentPlace = currentStreet.nextPlace(_currentPlace);
			else
				throw new InstructionExecutionException(WallEsMessages.STREETCLOSED);
		}
		else
			throw new InstructionExecutionException(WallEsMessages.NOSTREET);
	}
	
	/**
	 * Prints the information (description + inventory) of the current place
	 */
	public void scanCurrentPlace(){
		System.out.println(_currentPlace.toString());
	}
	
	
	public void setNavigationPanel(NavigationPanel navPanel){
		this.navPanel = navPanel;
	}
}
